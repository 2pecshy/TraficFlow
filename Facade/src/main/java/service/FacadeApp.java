package service;

import org.apache.catalina.filters.RemoteAddrFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import sample.SimulationWebConfiguration;

import javax.sound.midi.Receiver;
import java.io.File;

/**
 * Created by Jeremy on 18/01/2018.
 */

@SpringBootApplication
@EnableBinding(CustomProcessor.class)
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

    @StreamListener(CustomProcessor.INPUT_CONFIG)
    @SendTo(CustomProcessor.OUTPUT_SIMULATEUR)
    public SimulationWebConfiguration sendToSimulateur(SimulationWebConfiguration config) {
        System.out.println("j'ai recu " + config.toString());
        return config;
    }

    @StreamListener(CustomProcessor.INPUT_ERR_SIMULATEUR)
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
