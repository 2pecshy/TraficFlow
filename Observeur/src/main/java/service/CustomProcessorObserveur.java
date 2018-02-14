package service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by Matthieu on 14/02/2018.
 */
public interface CustomProcessorObserveur {
    String INPUT_SIMU_OBS = "inputSimuObs";
    String OUTPUT_SIMU_OBS = "outputSimuObs";

    @Input("inputSimuObs")
    SubscribableChannel  inputSimuObs();


    @Output("outputSimuObs")
    MessageChannel outputSimuObs();

}
