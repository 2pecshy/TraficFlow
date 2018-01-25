package services.simulateurConfiguration;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Matthieu on 18/01/2018.
 */

@RestController
public class SimulateurWebController {

    int step = -1;

    @ResponseBody @RequestMapping(value = "/simulateur", method = RequestMethod.POST)
    public void receiveConfig(@RequestBody SimulationWebConfiguration simulationWebConfiguration){
        int duree = simulationWebConfiguration.getSimulationLenght();
        step = 0;
        doSimu(duree);
    }

    public void doSimu(int duree) {
        for (; step < duree; step++) {
            System.out.println("Step");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/simulateur", method = RequestMethod.GET)
    public int printInfo(){ return this.step;}

    public void receiveMessageFromFacade(String simulationWebConfiguration){
        JSONObject json = new JSONObject(simulationWebConfiguration);
        SimulationWebConfiguration config = new SimulationWebConfiguration(json);
        int duree = config.getSimulationLenght();
        step = 0;
        doSimu(duree);
    }
}
