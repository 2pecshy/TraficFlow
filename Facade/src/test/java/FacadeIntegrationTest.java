import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import sample.SimulationWebConfiguration;
import service.CustomProcessor;
import service.FacadeApp;

import java.util.concurrent.BlockingQueue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FacadeApp.class)
@WebAppConfiguration
@DirtiesContext
public class FacadeIntegrationTest {


    //@SpyBean
    //FacadeApp app;

    @Autowired
    private CustomProcessor channels;

    @Autowired
    private MessageCollector collector;

    @Test
    public void receptionFromConfig() throws InterruptedException {
        SimulationWebConfiguration input = new SimulationWebConfiguration();
        this.channels.inputConfig().send(MessageBuilder.withPayload(input).build());
        BlockingQueue<Message<?>> messages = collector.forChannel(channels.outputSimulateur());
        assertEquals(messages.take().getPayload(), input);
    }

    /*@Test
    public void envoieAuSimulateur(){
        SimulationWebConfiguration input = new SimulationWebConfiguration();
    }*/

}
