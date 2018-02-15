import database.CustomProcessorDatabase;
import database.Database;
import database.SimulatorDataRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import sample.SimulatorData;

import java.util.List;

import static org.junit.Assert.assertEquals;

//import services.configuration.WebConfigurationService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes =  Database.class,webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext
@EnableBinding(CustomProcessorDatabase.class)
public class DatabaseIntegrationTest {

    @Autowired
    private CustomProcessorDatabase channels;

    @Autowired
    private SimulatorDataRepository repository;

    @Test
    public void receptionFromSimulateur() throws InterruptedException {
        SimulatorData input = new SimulatorData("test", 333, 46, -1);
        this.channels.inputSimulateur().send(MessageBuilder.withPayload(input).build());
        List<SimulatorData> test = repository.findById("test");
        assertEquals(test.get(0).getId(), "test");
        assertEquals(test.get(0).getNbCars(), 333);
        assertEquals(test.get(0).getTick(), 46);
        repository.deleteById("test");
    }

}
