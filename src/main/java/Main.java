import engine.Simulateur;
import org.jgrapht.alg.interfaces.MaximumFlowAlgorithm;
import utils.Map.Cost.Route;
import utils.Map.Map;
import utils.Map.MapEditor;
import utils.Stat.Stat;
import utils.Stat.StatManagerI;
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
        MapEditor mapEditor;

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


        /*mapEditor = new MapEditor(m);
        mapEditor.editMap();*/
        simulateur0.setMap(m);
        flow0 = simulateur0.getMaxFlow(3,1);
        ArrayList<Route> route_saturee = simulateur0.getRoutesSaturees();

        Stat statManager = new Stat("carrefour 3", "carrefour 1",flow0.getValue(),route_saturee,simulateur0.ameliorerFlow(0,1));
        statManager.printStats();
        Simulateur.KILL_Simulateur();

        dm.saveStat("file",".\\src\\main\\java\\stats1.txt", statManager.getAll());

        return;
    }


}
