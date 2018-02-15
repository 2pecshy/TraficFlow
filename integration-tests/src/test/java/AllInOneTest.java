import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import sample.SimulationWebConfiguration;
import service.*;
import services.configuration.WebConfigurationService;
import services.simulateurConfiguration.CustomProcessorSimulateur;
import services.simulateurConfiguration.SimulationWebService;

import java.util.concurrent.BlockingQueue;

import static org.junit.Assert.assertEquals;

/**
 * Created by Matthieu on 14/02/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes =  {FacadeApp.class, WebConfigurationService.class, SimulationWebService.class, ObserveurApp.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@EnableBinding({CustomProcessorFacade.class, CustomProcessorSimulateur.class, CustomProcessorObserveur.class})
public class AllInOneTest {

    private String localURL = "http://localhost:";
    private String resURL = "/config";

    private String jsonBody = "{" +
            " \"simulationLength\" : 10," +
            " \"simulationStart\" : 3, " +
            "\"HOVLanes\" : \"true\", " +
            "\"migrationPendulaire\" : \"False\", " +
            "\"mapLink\" : \"https://api.openstreetmap.org/api/0.6/map?bbox=11.54,48.14,11.543,48.145\" " +
            "}";


    private JSONObject jsonObject = new JSONObject(jsonBody);
    private SimulationWebConfiguration goodWebConfig = new SimulationWebConfiguration(jsonObject);

    @LocalServerPort
    private int port;

    @Autowired
    private CustomProcessorFacade channelsFacade;

    @Autowired
    private CustomProcessorSimulateur channelsSimu;

    @Autowired
    private CustomProcessorObserveur channelsObs;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MessageCollector collector;

    public AllInOneTest() throws JSONException {
    }


//    @Test
//    public void receptionFromConfig() throws InterruptedException {
//        SimulationWebConfiguration input = new SimulationWebConfiguration();
//        this.channels.inputConfig().send(MessageBuilder.withPayload(input).build());
//        BlockingQueue<Message<?>> messages = collector.forChannel(channels.outputSimulateur());
//        //assertThat(messages, receivesPayloadThat(is(input)));
//        assertEquals(messages.take().getPayload(), input);
//    }



    @Test
    public void allInOneTest() throws InterruptedException {
        SimulationWebConfiguration test = (SimulationWebConfiguration) this.restTemplate.postForObject(
                localURL+port+resURL,
                goodWebConfig,
                SimulationWebConfiguration.class);
        assertEquals(test.getSimulationLenght(), goodWebConfig.getSimulationLenght());
        this.channelsFacade.inputConfig().send(MessageBuilder.withPayload(goodWebConfig).build());
        BlockingQueue<Message<?>> messages = collector.forChannel(channelsFacade.outputSimulateur());
        assertEquals(((SimulationWebConfiguration)messages.take().getPayload()).getSimulationLenght(), goodWebConfig.getSimulationLenght());
        this.channelsSimu.input().send(MessageBuilder.withPayload(test).build());

        BlockingQueue<Message<?>> messages2 = collector.forChannel(channelsSimu.outputFacade());
        assertEquals((Boolean)messages2.take().getPayload(), true);

        this.restTemplate.getForObject(
                localURL+port+"/observeur",
                String.class);

        BlockingQueue<Message<?>> messages3 = collector.forChannel(channelsObs.outputSimuObs());
        assertEquals((String)messages3.take().getPayload(), "state");

        this.channelsSimu.inputObserver().send(MessageBuilder.withPayload("state").build());

        BlockingQueue<Message<?>> messages4 = collector.forChannel(channelsSimu.outputObserver());
        assertEquals((Integer)messages4.take().getPayload(), Integer.valueOf(0));



    }

}
