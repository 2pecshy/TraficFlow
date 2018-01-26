package service;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Jeremy on 18/01/2018.
 */

@Component
@org.springframework.web.bind.annotation.RestController
public class FacadeController {
    private Logger logger = LoggerFactory.getLogger(FacadeController.class);
    private String template = "Hello, %s !";

    private final RabbitTemplate rabbitTemplate;

    private final Exchange exchangeConfig, exchangeSimulateur;

    public FacadeController(RabbitTemplate rabbitTemplate, @Qualifier("exchangeConfig") Exchange exchangeConfig, @Qualifier("exchangeSimulateur") Exchange exchangeSimulateur) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchangeConfig = exchangeConfig;
        this.exchangeSimulateur = exchangeSimulateur;
    }

    @RequestMapping(value = "/facade", method = RequestMethod.GET)
    public Facade facade(@RequestParam(value="name",defaultValue = "Hello") String name){
        return new Facade(String.format(template,name));
    }


    @RequestMapping(value = "/facade", method = RequestMethod.POST)
    public String followToSimulateur(@RequestBody SimulationWebConfiguration simulationFromWeb){
        this.template = "%s, message followed!";
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        HttpEntity<SimulationWebConfiguration> request = new HttpEntity<SimulationWebConfiguration>(simulationFromWeb, headers);
        System.out.println("j'envoie !");
        return restTemplate.postForObject("http://localhost:8090/simulateur", request, String.class);
    }

    public void receiveMessageFromConfig(String simulationFromWeb) {
        rabbitTemplate.convertAndSend("facade-to-simu", "simulateur-queue", simulationFromWeb);
        JSONObject json = new JSONObject(simulationFromWeb);
        SimulationWebConfiguration simu = new SimulationWebConfiguration(json);
        logger.info("Received message '{}'", simulationFromWeb);
        System.out.println("j'ouvre la queue" + simu);
    }

    public void receiveMessageFromSimulateur(String toto){
        System.out.println("fdfsdfsdfs");
        toto = toto;
    }


}
