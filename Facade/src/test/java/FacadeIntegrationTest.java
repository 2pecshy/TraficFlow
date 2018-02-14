import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import sample.SimulationWebConfiguration;
import service.CustomProcessorFacade;
import service.FacadeApp;

import java.util.concurrent.BlockingQueue;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = FacadeApp.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext
@EnableBinding(CustomProcessorFacade.class)
public class FacadeIntegrationTest {

    @Autowired
    private CustomProcessorFacade channels;

    @Autowired
    private MessageCollector collector;

    @Test
    public void receptionFromConfig() throws InterruptedException {
        SimulationWebConfiguration input = new SimulationWebConfiguration();
        this.channels.inputConfig().send(MessageBuilder.withPayload(input).build());
        BlockingQueue<Message<?>> messages = collector.forChannel(channels.outputSimulateur());
        //assertThat(messages, receivesPayloadThat(is(input)));
        assertEquals(messages.take().getPayload(), input);
    }

    /*@Test
    public void envoieAuSimulateur(){
        SimulationWebConfiguration input = new SimulationWebConfiguration();
    }*/

}
