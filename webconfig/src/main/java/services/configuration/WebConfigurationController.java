package services.configuration;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import config.SimulationWebConfiguration.SimulationWebConfiguration;

/**
 * Created by Matthieu on 14/01/2018.
 */

@Component
@RestController
public class WebConfigurationController {

    private final RabbitTemplate rabbitTemplate;

    private final Exchange exchange;

    public WebConfigurationController(RabbitTemplate rabbitTemplate, Exchange exchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }

    String demo = "Nothing";
    @RequestMapping(value = "/config", method = RequestMethod.GET)
    public String posted(){
        return demo;
    }



    @ResponseBody @RequestMapping("/config")
    public void process(@RequestBody SimulationWebConfiguration input) {
        //return input.toString();
        /*this.demo = "Request accepted";
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        HttpEntity<SimulationWebConfiguration> request = new HttpEntity<SimulationWebConfiguration>(input, headers);*/
        rabbitTemplate.convertAndSend("config-queue", input.toString());
        //return restTemplate.postForObject("http://localhost:8091/facade", request, String.class);
    }


}
