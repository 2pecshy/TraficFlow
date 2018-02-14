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
@EnableBinding(CustomProcessor.class)
public class SimulationWebService extends SpringBootServletInitializer implements Observer {
    int step = -1;
    SimulatorData data = new SimulatorData();

    public static void main(String[] args) {
        SpringApplication.run(SimulationWebService.class, args);
    }


    @Autowired
    CustomProcessor processor;

    @Bean
    public AlwaysSampler defaultSampler() {
        return new AlwaysSampler();
    }

    @StreamListener(CustomProcessor.INPUT_FACADE)
    public void lauchSimu(SimulationWebConfiguration config) throws  Exception{
        int pid;
        System.out.println("on lance la simulation avec : " + config);
        MapDownloader downloader = new MapDownloader();
        String mapName = "";
        try {
            mapName = downloader.downloadFile(config.getMapLink());
        }
        catch(Exception e ){
            System.out.println("Mauvais format de fichier !");
            processor.ouputFacadeError().send(MessageBuilder.withPayload("Mauvais format de fichier !").build());
        }
        try {
            Map map = osmLoader.load(mapName);
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
        }
        catch(NullPointerException e){
            System.out.println("Mauvais format de fichier !");
            processor.ouputFacadeError().send(MessageBuilder.withPayload("Mauvais format de fichier !").build());
        }
    }

    @StreamListener(CustomProcessor.INPUT_OBSERVER)
    @SendTo(CustomProcessor.OUTPUT_OBSERVER)
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
//            if(data.getNbCars() != ((SimulateurObserver) o).getData().getNbCars()){

            data = ((SimulateurObserver) o).getData();
            processor.outputDatabase().send(MessageBuilder.withPayload(data).build());
//            }
            step = ((SimulateurObserver) o).getStep();
        }
    }
}
