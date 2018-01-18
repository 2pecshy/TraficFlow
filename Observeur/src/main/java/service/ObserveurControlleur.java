package service;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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
public class ObserveurControlleur {

    private static AtomicLong compteur = new AtomicLong();
    private static String urlSimu = "http://localhost:8090/simulateur";

    public void setCompteur(AtomicLong compteur) {
        this.compteur = compteur;
    }

    @RequestMapping("/observeur")
    public AtomicLong atom(@RequestParam(value="hey") int n){
        compteur.set(n);
        return new AtomicLong(compteur.getAndIncrement());
    }

    @RequestMapping(value = "/observeur", method = RequestMethod.GET)
    public String printInfo(){
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        ResponseEntity<Integer> response = restTemplate.getForEntity(urlSimu,Integer.class);
        return "Simulateur went to " + response.getBody().toString() + " steps !";
    }
}
