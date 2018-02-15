package services.simulateurConfiguration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by Matthieu on 28/01/2018.
 */
public interface CustomProcessorSimulateur {
    String INPUT_OBSERVER = "inputObserver";
    String OUTPUT_OBSERVER = "outputObserver";
    String OUTPUT_ERR_FACADE = "outputFacadeError";
    String INPUT_FACADE = "input";
    String OUTPUT_DATABASE = "outputDatabase";
    String OUTPUT_FACADE = "outputFacade";

    @Input("inputObserver")
    SubscribableChannel inputObserver();

    @Input("input")
    SubscribableChannel input();

    @Output("outputObserver")
    MessageChannel outputObserver();

    @Output("outputFacadeError")
    MessageChannel ouputFacadeError();

    @Output
    MessageChannel outputFacade();

    @Output("outputDatabase")
    MessageChannel outputDatabase();
}

