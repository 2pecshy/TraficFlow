package service;

import org.apache.catalina.filters.RemoteAddrFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Jeremy on 18/01/2018.
 */

@RestController
@SpringBootApplication
@EnableBinding(Processor.class)
public class ObserveurApp extends SpringBootServletInitializer {
    private int step = 0;

    @Autowired
    Processor processor;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ObserveurApp.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ObserveurApp.class, args);
    }

    private static AtomicLong compteur = new AtomicLong();
    private static String urlSimu = "http://localhost:8090/simulateur";

    public void setCompteur(AtomicLong compteur) {
        this.compteur = compteur;
    }

    @StreamListener(Processor.INPUT)
    public void getSimAnswer(int step){
        this.step = step;
    }

    @RequestMapping("/observeur")
    public AtomicLong atom(@RequestParam(value="hey") int n){
        compteur.set(n);
        return new AtomicLong(compteur.getAndIncrement());
    }

    @RequestMapping(value = "/observeur", method = RequestMethod.GET)
    public String printInfo(){
        /*RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        ResponseEntity<Integer> response = restTemplate.getForEntity(urlSimu,Integer.class);*/
        processor.output().send(MessageBuilder.withPayload("state").build());
        return "Simulateur went to " + step + " steps !";
    }

   /* @Bean
    public FilterRegistrationBean remoteAddressFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        RemoteAddrFilter filter = new RemoteAddrFilter();
        filter.setAllow("127.0.0.1");
        //filter.setAllow("0:0:0:0:0:0:0:1");
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }*/
}
