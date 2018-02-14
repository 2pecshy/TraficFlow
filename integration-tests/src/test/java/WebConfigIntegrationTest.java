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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import sample.SimulationWebConfiguration;
import services.configuration.WebConfigurationService;
import static org.junit.Assert.assertEquals;

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


    private String localURL = "http://localhost:";
    private String resURL = "/config";

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
                localURL+port+resURL,
                goodWebConfig,
                SimulationWebConfiguration.class);
        assertEquals(test.getSimulationLenght(), goodWebConfig.getSimulationLenght());

    }

    @Test
    public void postMethodShouldReturn200OK(){
        HttpEntity<SimulationWebConfiguration> request = new HttpEntity<>(goodWebConfig);
        ResponseEntity<SimulationWebConfiguration> response = restTemplate
                .exchange(localURL+port+resURL, HttpMethod.POST, request, SimulationWebConfiguration.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);

        /*
        SimulationWebConfiguration foo = response.getBody();
        assertEquals(foo.getMigrationPendulaire(), true);
        */
    }


}