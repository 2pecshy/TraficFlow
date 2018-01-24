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

        TraficFlowModel simulateur = new TraficFlowModel(map);
        simulateur.setMap(map);

        System.out.println("run model on a thread");
        pid = manager.addAndRunSimulation(simulateur);
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


        return;
    }


}
