package service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.SendTo;
import sample.SimulationWebConfiguration;

/**
 * Created by Jeremy on 18/01/2018.
 */

@SpringBootApplication
@EnableBinding(CustomProcessorFacade.class)
public class FacadeApp extends SpringBootServletInitializer {
    private String template = "Hello, %s !";
    private String error;
    public String getError() {
        return error;
    }

    public static void main(String[] args) {
        SpringApplication.run(FacadeApp.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FacadeApp.class);
    }

    @StreamListener(CustomProcessorFacade.INPUT_CONFIG)
    @SendTo(CustomProcessorFacade.OUTPUT_SIMULATEUR)
    public SimulationWebConfiguration sendToSimulateur(SimulationWebConfiguration config) {
        System.out.println("j'ai recu " + config.toString());
        return config;
    }

    @StreamListener(CustomProcessorFacade.INPUT_SIMULATEUR)
    public void simulationState(boolean status){
        System.out.println("le simulateur a finit : " + status);
    }

    @StreamListener(CustomProcessorFacade.INPUT_ERR_SIMULATEUR)
    public void errorFromSimulateur (String msg){
        error = msg;
        System.out.println(msg);
        //return  msg;
    }


    @Bean
    public AlwaysSampler defaultSampler() {
        return new AlwaysSampler();
    }

}
