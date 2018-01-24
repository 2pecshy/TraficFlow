import engine.TraficFlowModel;
import engine.SimulateurManager;
import utils.Map.Map;
import utils.Map.Osm.osmLoader;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String [] args){

        int pid;
        Map map = osmLoader.load("map.osm");
        SimulateurManager.INIT_Simulateur();
        SimulateurManager manager = SimulateurManager.getInstance();

        TraficFlowModel model = new TraficFlowModel(map);
        TraficFlowModel model2 = new TraficFlowModel(map);

        model.setMap(map);
        model2.setMap(map);

        System.out.println("run model on a thread");
        pid = manager.addAndRunSimulation(model);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("pause simu");
        manager.pauseSimulation(pid);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("resume simu");
        manager.resumeSimulation(pid);

        System.out.println("Run a second simu in //");
        manager.addAndRunSimulation(model2);


        return;
    }


}
