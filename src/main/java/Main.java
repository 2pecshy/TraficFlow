import com.sun.org.apache.xpath.internal.SourceTree;
import engine.Simulateur;
import org.jgrapht.alg.interfaces.MaximumFlowAlgorithm;
import utils.Map.Cost.Route;
import utils.Map.Map;
import utils.Map.MapEditor;
import utils.Map.MapManagerI;
import utils.Stat.Stat;
import utils.Stat.StatManagerI;
import utils.StorageManager.DataManager;
import utils.StorageManager.IDataManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Main {

    public static void main(String [] args){

        Simulateur simulateur0 = null;
        MaximumFlowAlgorithm.MaximumFlow<Route> flow0;
        MapEditor mapEditor;

        Simulateur.INIT_Simulateur();

        IDataManager dm = new DataManager();
        //File f = new File(".\\src\\main\\java\\carte1.txt");
        Map m = dm.loadMap("file","./src/main/java/carte1.txt");
        System.out.println("LOAD TERMINE--------------");
        //Map m = Map.getDefaultMap();
        MapEditor editor = new MapEditor(m);
        m = editor.editMap();
        System.out.println("Carte avant simulation :");
        m.afficherMap();
        DataManager manager = new DataManager();
        manager.saveMap("file", "/src/main/java/", m);
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

        dm.saveStat("file","./src/main/java/stats1.txt", statManager.getAll());

        return;
    }


}
