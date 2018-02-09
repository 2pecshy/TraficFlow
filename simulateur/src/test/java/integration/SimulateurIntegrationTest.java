package integration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import sample.SimulationWebConfiguration;
import services.simulateurConfiguration.CustomProcessor;
import services.simulateurConfiguration.SimulationWebService;

import java.util.concurrent.BlockingQueue;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.springframework.cloud.stream.test.matcher.MessageQueueMatcher.receivesPayloadThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimulationWebService.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext
@EnableBinding(CustomProcessor.class)
public class SimulateurIntegrationTest {

    @Autowired
    private CustomProcessor channels;

    @Autowired
    private MessageCollector collector;

    @Test
    public void receptionFromFacadeError() throws InterruptedException {
        SimulationWebConfiguration input = new SimulationWebConfiguration();
        input.setMapLink("http://totovaalaplageavecsesjouetspourfairedesjolischateauxdesableonaimetoustotomdrlolxd.com/totofaiDchato.test");
        this.channels.input().send(MessageBuilder.withPayload(input).build());
        BlockingQueue<Message<?>> messages = collector.forChannel(channels.ouputFacadeError());
        //assertThat(messages, receivesPayloadThat(is(input)));
        assertEquals(messages.take().getPayload(), "Mauvais format de fichier !");
    }

    @Test
    public void receptionFromObserver() throws InterruptedException {
        String msg = "state";
        this.channels.inputObserver().send(MessageBuilder.withPayload(msg).build());
        BlockingQueue<Message<?>> messages = collector.forChannel(channels.outputObserver());
        assertEquals(messages.take().getPayload(), -1);
    }

    /*@Test
    public void envoieAuSimulateur(){
        SimulationWebConfiguration input = new SimulationWebConfiguration();
    }*/

}