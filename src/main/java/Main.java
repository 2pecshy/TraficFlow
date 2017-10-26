import engine.Simulateur;
import org.jgrapht.alg.interfaces.MaximumFlowAlgorithm;
import utils.Map.Cost.Route;
import utils.Map.Map;
import utils.Map.MapEditor;
import utils.StorageManager.DataManager;

import java.io.File;
import java.util.ArrayList;

public class Main {

    /**
     * @param args
     * */
    public static void main(String [] args) {

        Simulateur simulateur0 = null;
        MaximumFlowAlgorithm.MaximumFlow<Route> flow0;

        Simulateur.INIT_Simulateur();

        DataManager dm = new DataManager();
        File f = new File(".\\src\\main\\java\\carte1.txt");
        Map m = dm.loadMap("file",f);
        System.out.println("LOAD TERMINE--------------");
        //Map m = Map.getDefaultMap();
        MapEditor editor = new MapEditor(m);
        editor.editMap();
        try {
            simulateur0 = Simulateur.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        simulateur0.setMap(m);
        flow0 = simulateur0.getMaxFlow(3,1);
        ArrayList<Route> route_saturee = simulateur0.getRoutesSaturees();

        System.out.println("(départ: carrefours 3, arrivée: carrefours 1)flow max avant saturation des routes: " + flow0.getValue() /*+ flow0.toString()*/);
        System.out.println("route saturées: " + route_saturee.toString());
        System.out.println("route(0->1): amélioration max,  " + simulateur0.ameliorerFlow(0,1)  + " voies");

        Simulateur.KILL_Simulateur();

        return;
    }


}
