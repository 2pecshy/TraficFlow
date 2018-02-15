/**
 * Created by Matthieu on 15/02/2018.
 */

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
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
import services.simulateurConfiguration.CustomProcessorSimulateur;
import services.simulateurConfiguration.SimulationWebService;

import java.io.File;
import java.util.concurrent.BlockingQueue;

import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest(classes =  SimulationWebService.class,webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext
@EnableBinding(CustomProcessorSimulateur.class)
public class SimulateurIntegrationTest {

    private String jsonBody = "{" +
            " \"simulationLength\" : 10," +
            " \"simulationStart\" : 3, " +
            "\"HOVLanes\" : \"true\", " +
            "\"migrationPendulaire\" : \"False\", " +
            "\"mapLink\" : \"https://api.openstreetmap.org/api/0.6/map?bbox=11.54,48.14,11.543,48.145\" " +
            "}";


    private JSONObject jsonObject = new JSONObject(jsonBody);
    private SimulationWebConfiguration goodWebConfig;

    @Autowired
    private CustomProcessorSimulateur channels;

    @Autowired
    private MessageCollector collector;

    public SimulateurIntegrationTest() throws JSONException {
    }

    @Before
    public void setup(){
        goodWebConfig = new SimulationWebConfiguration(jsonObject);
    }

    @After
    public void cleanFiles(){
        deleteFile("map1.osm");
    }

    private void deleteFile(String filename){
        File f = new File(filename);
        f.delete();
        System.out.println("on supprime !");
    }

    @Test
    public void envoiSimu() throws InterruptedException {
        this.channels.input().send(MessageBuilder.withPayload(goodWebConfig).build());
        BlockingQueue<Message<?>> messages = collector.forChannel(channels.outputFacade());
        assertEquals(messages.take().getPayload(),true);
    }

    @Test
    public void envoiURLCorrompue() throws InterruptedException {
        goodWebConfig.setMapLink("http://totovaalaplage.com");
        this.channels.input().send(MessageBuilder.withPayload(goodWebConfig).build());
        BlockingQueue<Message<?>> messages = collector.forChannel(channels.ouputFacadeError());
        assertEquals((String)messages.take().getPayload(),"Mauvaise URL");
    }

    @Test
    public void envoiFichierCorrompue() throws InterruptedException {
        goodWebConfig.setMapLink("http://dbz-dokkanbattle.wikia.com/wiki/File:UR_eclair.png");
        this.channels.input().send(MessageBuilder.withPayload(goodWebConfig).build());
        //BlockingQueue<Message<?>> messages = collector.forChannel(channels.ouputFacadeError());
        //assertEquals((String)messages.take().getPayload(),"Mauvais format de fichier !");
    }



}