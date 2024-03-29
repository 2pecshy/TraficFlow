package service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by Matthieu on 28/01/2018.
 */
public interface CustomProcessorFacade {
    String INPUT_CONFIG = "inputConfig";
    String OUTPUT_SIMULATEUR = "outputSimulateur";
    String INPUT_ERR_SIMULATEUR = "inputSimulateurError";
    String INPUT_SIMULATEUR = "inputSimulateur";

    @Input("inputConfig")
    SubscribableChannel inputConfig();

    @Input("inputSimulateur")
    SubscribableChannel inputSimulateur();

    @Input("inputSimulateurError")
    SubscribableChannel inputSimulateurError();

    @Output("outputSimulateur")
    MessageChannel outputSimulateur();

}
