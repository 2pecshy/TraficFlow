package services.simulateurConfiguration;

import engine.Model;
import engine.SimulateurManager;
import engine.TraficFlowModel;
import org.apache.catalina.filters.RemoteAddrFilter;
import org.json.JSONObject;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import sample.SimulationWebConfiguration;
import utils.Map.Map;
import utils.Map.Osm.osmLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Matthieu on 18/01/2018.
 */
@SpringBootApplication
@EnableBinding(CustomProcessor.class)
public class SimulationWebService extends SpringBootServletInitializer implements Observer {
    int step = -1;

    public static void main(String[] args) {
        SpringApplication.run(SimulationWebService.class, args);
    }

    @Bean
    public AlwaysSampler defaultSampler() {
        return new AlwaysSampler();
    }

    @StreamListener(CustomProcessor.INPUT_FACADE)
    public void lauchSimu(SimulationWebConfiguration config) throws  Exception{
        int pid;
        System.out.println("on lance la simulation avec : " + config);
        MapDownloader downloader = new MapDownloader();
        String mapName = downloader.downloadFile(config.getMapLink());
        try{
            SimulateurManager.getInstance();
        }
        catch(NullPointerException e){
            SimulateurManager.INIT_Simulateur();
        }
        SimulateurManager simu = SimulateurManager.getInstance();
        Map map = osmLoader.load(mapName);
        TraficFlowModel model = new TraficFlowModel(map);
        model.setMap(map);
        model.getObserver().addObserver(this);
        pid = simu.addAndRunSimulation(model);
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
            step = ((SimulateurObserver) o).getStep();
        }
    }
}
