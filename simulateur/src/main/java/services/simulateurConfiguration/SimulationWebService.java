package services.simulateurConfiguration;

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

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Created by Matthieu on 18/01/2018.
 */
@SpringBootApplication
@EnableBinding(CustomProcessor.class)
public class SimulationWebService extends SpringBootServletInitializer {
    int step = -1;

    public static void main(String[] args) {
        SpringApplication.run(SimulationWebService.class, args);
    }

    @Bean
    public AlwaysSampler defaultSampler() {
        return new AlwaysSampler();
    }

    public void doSimu(int duree) {
        for (; step < duree; step++) {
            System.out.println("Step");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        step = 0;
    }

    @StreamListener(CustomProcessor.INPUT_FACADE)
    public void lauchSimu(SimulationWebConfiguration config) throws  Exception{
        System.out.println("on lance la simulation avec : " + config);
        MapDownloader downloader = new MapDownloader();
        downloader.downloadFile(config.getMapLink());
        doSimu(10);
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
}
