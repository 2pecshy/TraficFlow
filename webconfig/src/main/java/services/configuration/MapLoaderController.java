package services.configuration;

/**
 * Created by Matthieu on 18/01/2018.
 */

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import services.configuration.SimulationWebConfiguration;

import java.net.*;
import java.io.*;
@RestController
public class MapLoaderController {

    @RequestMapping("/maplink")
    public String downloaMap(@RequestBody String url) throws Exception {

        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        HttpEntity<String> request = new HttpEntity<String>(url, headers);
        return restTemplate.postForObject("http://localhost:8090/download", request, String.class);
    }
}
