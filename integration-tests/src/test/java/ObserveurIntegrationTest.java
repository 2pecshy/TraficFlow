import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import service.ObserveurApp;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObserveurApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@EnableBinding(Processor.class)
public class ObserveurIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private Processor processor;

    @Autowired
    private MessageCollector collector;

    @Test
    public void bluxtest(){

    }
}
