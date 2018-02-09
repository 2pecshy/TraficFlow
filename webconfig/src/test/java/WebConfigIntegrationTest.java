import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import services.configuration.ICustomProcessorWebConfig;
import sample.SimulationWebConfiguration;
import services.configuration.WebConfigurationService;

import java.util.concurrent.BlockingQueue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.cloud.stream.test.matcher.MessageQueueMatcher.receivesPayloadThat;
import static org.springframework.integration.support.management.graph.LinkNode.Type.input;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebConfigurationService.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@EnableBinding(Source.class)
public class WebConfigIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private Source channels;

    @Autowired
    private MessageCollector collector;



    private String jsonBody = "{" +
            " \"simulationLength\" : 1," +
            " \"simulationStart\" : 3, " +
            "\"HOVLanes\" : \"true\", " +
            "\"migrationPendulaire\" : \"False\", " +
            "\"mapLink\" : \"https://api.openstreetmap.org/api/0.6/map?bbox=11.54,48.14,11.543,48.145\" " +
            "}";

    private JSONObject jsonObject = new JSONObject(jsonBody);
    private SimulationWebConfiguration goodWebConfig = new SimulationWebConfiguration(jsonObject);

    public WebConfigIntegrationTest() throws JSONException {
    }


    @Test
    public void postMethodShouldReturnGoodWebConfig() throws InterruptedException {

        SimulationWebConfiguration test = (SimulationWebConfiguration) this.restTemplate.postForObject(
                "http://localhost:" + port + "/webconfig",
                goodWebConfig,
                WebConfigurationService.class)
                .process(goodWebConfig);
        //assertThat(messages, receivesPayloadThat(is(goodWebConfig)));
        assertEquals(test.getSimulationLenght(), goodWebConfig.getSimulationLenght());
    }

}
