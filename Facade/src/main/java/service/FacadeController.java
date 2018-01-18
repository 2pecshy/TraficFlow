package service;

import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Jeremy on 18/01/2018.
 */

@org.springframework.web.bind.annotation.RestController
public class FacadeController {

    private static final String template = "Hello, %s !";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/facade", method = RequestMethod.GET)
    public Facade facade(@RequestParam(value="name",defaultValue = "World") String name){
        return new Facade(String.format(template,name));
    }

    @RequestMapping(value = "/facade", method = RequestMethod.POST)
    public String followToSimulateur(@RequestBody Integer i){

        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        HttpEntity<Integer> request = new HttpEntity<Integer>(i, headers);
        return restTemplate.postForObject("http://localhost:8090/simulateur", request, String.class);
    }
}
