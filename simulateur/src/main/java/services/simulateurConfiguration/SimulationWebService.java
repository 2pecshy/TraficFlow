package services.simulateurConfiguration;

import engine.SimulateurManager;
import engine.TraficFlowModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.handler.annotation.SendTo;
import sample.SimulationWebConfiguration;
import sample.SimulatorData;
import utils.Map.Map;
import utils.Map.Osm.osmLoader;

import java.util.Observable;
import java.util.Observer;
/**
 * Created by Matthieu on 18/01/2018.
 */
@SpringBootApplication
@EnableBinding(CustomProcessorSimulateur.class)
public class SimulationWebService extends SpringBootServletInitializer implements Observer {
    int step = -1;
    int id = -1;
    SimulatorData data = new SimulatorData();

    public static void main(String[] args) {
        SpringApplication.run(SimulationWebService.class, args);
    }


    @Autowired
    CustomProcessorSimulateur processor;

    @Bean
    public AlwaysSampler defaultSampler() {
        return new AlwaysSampler();
    }

    @StreamListener(CustomProcessorSimulateur.INPUT_FACADE)
    public void lauchSimu(SimulationWebConfiguration config) throws  Exception{
        int pid;
        System.out.println("on lance la simulation avec : " + config);
        id = -1;
        MapDownloader downloader = new MapDownloader();
        String mapName = "";
        try {
            mapName = downloader.downloadFile(config.getMapLink());
        }
        catch(Exception e ){
            System.out.println("Mauvaise URL");
            System.out.println(e);
            processor.ouputFacadeError().send(MessageBuilder.withPayload("Mauvaise URL").build());
            return;
        }
        try {
            Map map = osmLoader.load(mapName);
            System.out.println("Download done!");
            TraficFlowModel model = new TraficFlowModel(map, config);
            model.setMap(map);
            model.addObserver(this);
            try{
                SimulateurManager.getInstance();
            }
            catch(NullPointerException e){
                SimulateurManager.INIT_Simulateur();
            }
            SimulateurManager simu = SimulateurManager.getInstance();
            pid = simu.addAndRunSimulation(model);
            processor.outputDatabaseId().send(MessageBuilder.withPayload("askID").build());
        }
        catch(NullPointerException e){
            System.out.println("Mauvais format de fichier !");
            processor.ouputFacadeError().send(MessageBuilder.withPayload("Mauvais format de fichier !").build());
            return;
        }
    }

    @StreamListener(CustomProcessorSimulateur.INPUT_DATABASE)
    public void getId(String id){
        System.out.println("Mon id est attibuée : " + id);
        if(this.id == -1){
            this.id = Integer.valueOf(id);
        }
    }

    @StreamListener(CustomProcessorSimulateur.INPUT_OBSERVER)
    @SendTo(CustomProcessorSimulateur.OUTPUT_OBSERVER)
    public int answerObserver(String msg){
        System.out.println("je recois de l'observeur");
        return step;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SimulationWebService.class);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof SimulateurObserver){
            if(step != ((SimulateurObserver) o).getStep() ){
                step = ((SimulateurObserver) o).getStep();
            }
            else {
                data = ((SimulateurObserver) o).getData();
                data.setIdSimulation(id);
                processor.outputDatabase().send(MessageBuilder.withPayload(data).build());
            }
            if(((SimulateurObserver) o).isFinish()){
                processor.outputFacade().send(MessageBuilder.withPayload(true).build());
                step = 0;
            }
        }
    }
}
