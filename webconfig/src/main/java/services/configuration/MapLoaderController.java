package services.configuration;

/**
 * Created by Matthieu on 18/01/2018.
 */

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

@RestController
public class MapLoaderController {

    @RequestMapping("/maplink")
    public String downloadMap(@RequestBody String url) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        JSONObject req = new JSONObject(url);
        URL website = new URL(req.getString("url"));
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        String array[] = website.getPath().split("/");
        FileOutputStream fos = new FileOutputStream(array[array.length-1]);
        File file = new File(array[array.length-1]);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        HttpEntity<File> fileReq = new HttpEntity<File>(file, headers);
        return restTemplate.postForObject("http://localhost:8090/download", fileReq, String.class);
    }
}
