package services;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Matthieu on 18/01/2018.
 */

@RestController
public class SimulateurWebController {

    @ResponseBody @RequestMapping("/simulateur")
    public String receiveConfig(@RequestBody SimulationWebConfiguration input){
        return "reçu !";
    }
}
