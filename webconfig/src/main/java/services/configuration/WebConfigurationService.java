package services.configuration;

/**
 * Created by Matthieu on 16/01/2018.
 */
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import sample.SimulationWebConfiguration;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

@SpringBootApplication
@RestController
@EnableBinding(Source.class)
public class WebConfigurationService extends SpringBootServletInitializer {

    @Autowired
    Source src;

    @RequestMapping("/config")
    public SimulationWebConfiguration process(@RequestBody SimulationWebConfiguration input) {

        src.output().send(MessageBuilder.withPayload(input).build());
        return input;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WebConfigurationService.class);
    }

    /*@RequestMapping("/maplink")
    public void downloadMap(@RequestBody String url) throws Exception {
        JSONObject req = new JSONObject(url);
        URL website = new URL(req.getString("url"));
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        String array[] = website.getPath().split("/");
        FileOutputStream fos = new FileOutputStream(array[array.length-1]);
        File file = new File(array[array.length-1]);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        src.output().send(MessageBuilder.withPayload(file).build());
        //HttpEntity<File> fileReq = new HttpEntity<File>(file, headers);
        //return restTemplate.postForObject("http://localhost:8090/download", fileReq, String.class);
    }*/

    @Bean
    public AlwaysSampler defaultSampler() {
        return new AlwaysSampler();
    }


    public static void main(String[] args) {
        SpringApplication.run(WebConfigurationService.class, args);
    }
}