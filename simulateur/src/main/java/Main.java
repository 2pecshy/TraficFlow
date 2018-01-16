import utils.Map.Osm.osmLoader;
import utils.Map.Ui_graph;

public class Main {

    public static void main(String [] args){

        //test MapLoader
//        MapLoader loader = new MapLoader();
//        loader.uploadMap();

       /* Simulateur simulateur0 = null;
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


        mapEditor = new MapEditor(m);
        mapEditor.editMap()
        simulateur0.setMap(m);
        flow0 = simulateur0.getMaxFlow(3,1);
        ArrayList<Route> route_saturee = simulateur0.getRoutesSaturees();

        Stat statManager = new Stat("carrefour 3", "carrefour 1",flow0.getValue(),route_saturee,simulateur0.ameliorerFlow(0,1));
        statManager.printStats();
        Simulateur.KILL_Simulateur();

        dm.saveStat("file","./src/main/java/stats1.txt", statManager.getAll());*/

        Ui_graph ui_test1 = new Ui_graph();
        ui_test1.show_G();
        osmLoader.load("map.osm");

        return;
    }


}
