package services.simulateurConfiguration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by Matthieu on 28/01/2018.
 */
public interface CustomProcessor {
    String INPUT_OBSERVER = "inputObserver";
    String OUTPUT_OBSERVER = "outputObserver";
    String INPUT_FACADE = "input";

    @Input("inputObserver")
    SubscribableChannel inputObserver();

    @Input("input")
    SubscribableChannel input();

    @Output("outputObserver")
    MessageChannel outputObserver();
}

