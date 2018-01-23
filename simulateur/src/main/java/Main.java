import engine.Simulateur;
import utils.Map.Map;
import utils.Map.Osm.osmLoader;
import utils.Map.Ui_graph;

import java.util.concurrent.TimeUnit;

import static utils.Map.Osm.osmLoader.load;

public class Main {

    public static void main(String [] args){

        Map map = osmLoader.load("map.osm");

        Simulateur.INIT_Simulateur();
        Simulateur simulateur = Simulateur.getInstance();

        simulateur.setMap(map);
        simulateur.startSimulation();
        /*simulateur.pauseSimulation();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        simulateur.start();
        simulateur.resumeSimulation();*/

        return;
    }


}
