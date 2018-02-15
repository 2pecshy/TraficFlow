package database;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CustomProcessorDatabase {
    String INPUT_SIMULATEUR = "inputSimulateur";

    @Input("inputSimulateur")
    SubscribableChannel inputSimulateur();

}
