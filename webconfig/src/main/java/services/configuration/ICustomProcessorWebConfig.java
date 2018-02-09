package services.configuration;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ICustomProcessorWebConfig {
    String OUTPUT_OUTPUT = "output";

    @Output("output")
    MessageChannel output();
}
