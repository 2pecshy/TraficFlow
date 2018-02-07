import engine.TraficFlowModel;
import engine.SimulateurManager;
import utils.Map.Map;
import utils.Map.Osm.osmLoader;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String [] args){

        int pid;
        Map map = osmLoader.load("map.osm");
        Map map2 = osmLoader.load("map11.osm");

        TraficFlowModel model = new TraficFlowModel(map);
        model.setNo_UI(false);
        TraficFlowModel model2 = new TraficFlowModel(map2);

        //model.setMap(map);
        //model2.setMap(map2);

        SimulateurManager.INIT_Simulateur();
        SimulateurManager manager = SimulateurManager.getInstance();


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

        //System.out.println("Run a second simu in //");
        //manager.addAndRunSimulation(model2);

        return;
    }


}
