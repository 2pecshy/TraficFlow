package services.simulateurConfiguration;

import org.springframework.web.bind.annotation.*;

/**
 * Created by Matthieu on 18/01/2018.
 */

@RestController
public class SimulateurWebController {

    int i = 0;

    @ResponseBody @RequestMapping(value = "/simulateur", method = RequestMethod.POST)
    public void receiveConfig(@RequestBody Integer input){
        this.i = input;
    }

    @RequestMapping(value = "/simulateur", method = RequestMethod.GET)
    public int printInfo(){ return this.i;}
}
