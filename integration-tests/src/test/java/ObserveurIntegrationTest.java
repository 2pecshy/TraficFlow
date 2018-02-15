import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import sample.SimulationWebConfiguration;
import service.CustomProcessorObserveur;
import service.ObserveurApp;

import java.util.concurrent.BlockingQueue;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObserveurApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@EnableBinding(CustomProcessorObserveur.class)
public class ObserveurIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomProcessorObserveur channels;

    @Autowired
    private MessageCollector collector;

    private String localURL = "http://localhost:";
    private String resURL = "/observeur";

    @Test
    public void getMethodShouldSendaMessageQueue() throws InterruptedException {
        this.restTemplate.getForObject(
                localURL+port+resURL,
                String.class);

        BlockingQueue<Message<?>> messages = collector.forChannel(channels.outputSimuObs());
        assertEquals((String) messages.take().getPayload(), "state");

    }
}
