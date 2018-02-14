package services.configuration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by Matthieu on 14/02/2018.
 */
public interface CustomProcessorConfig {
    String OUTPUT_FACADE = "output";

    @Output("output")
    MessageChannel ouputFacade();

}
