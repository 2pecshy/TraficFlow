package services.configuration;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.WebService;

/**
 * Created by Matthieu on 14/01/2018.
 */

@RestController
public class WebConfigurationController {

    @ResponseBody @RequestMapping("/config")
    public String process(@RequestBody SimulationWebConfiguration input) {
        return input.toString();
    }

}
