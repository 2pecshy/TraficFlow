
import database.CustomProcessorDatabase;
import database.Database;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Matthieu on 11/02/2018.
 */


@RunWith(SpringRunner.class)
@SpringBootTest(classes =  Database.class,webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext
@EnableBinding(CustomProcessorDatabase.class)
public class DatabaseIntegrationTest {
}
