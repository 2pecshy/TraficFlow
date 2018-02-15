package database;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface CustomProcessorDatabase {
    String INPUT_SIMULATEUR = "inputSimulateur";
    String OUTPUT_SIMULATEUR = "outputSimulateur";
    String INPUT_SIMULATEUR_ID = "inputSimulateurId";

    @Input("inputSimulateur")
    SubscribableChannel inputSimulateur();

    @Input("inputSimulateurId")
    SubscribableChannel inputSimulateurId();

    @Output("outputSimulateur")
    MessageChannel outputSimulateur();

}
