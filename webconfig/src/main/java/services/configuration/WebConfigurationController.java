package services.configuration;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.jws.WebService;

/**
 * Created by Matthieu on 14/01/2018.
 */

@RestController
public class WebConfigurationController {

    @ResponseBody @RequestMapping("/config")
    public String process(@RequestBody SimulationWebConfiguration input) {
        //return input.toString();
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        HttpEntity<SimulationWebConfiguration> request = new HttpEntity<SimulationWebConfiguration>(input, headers);
        return restTemplate.postForObject("http://localhost:8090/simulateur", request, String.class);
    }

}
