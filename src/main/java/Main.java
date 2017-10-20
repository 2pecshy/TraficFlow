import engine.Simulateur;
import org.jgrapht.alg.interfaces.MaximumFlowAlgorithm;
import utils.Map.Cost.Route;
import utils.Map.Map;

import java.util.ArrayList;

public class Main {

    /**
     * @param args
     * */
    public static void main(String [] args) {

        Simulateur simulateur0 = null;
        Map map0 = new Map();
        MaximumFlowAlgorithm.MaximumFlow<Route> flow0;

        Simulateur.INIT_Simulateur();

        try {
            simulateur0 = Simulateur.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        simulateur0.setMap(map0);
        flow0 = simulateur0.getMaxFlow(3,1);
        ArrayList<Route> route_saturee = simulateur0.getCarrefoursSaturees();

        System.out.println("(départ: carrefours 3, arrivée: carrefours 1)flow max avant saturation des routes: " + flow0.getValue() /*+ flow0.toString()*/);
        System.out.println("route saturées: " + route_saturee.toString());
        System.out.println("route(0->1): amélioration max,  " + simulateur0.ameliorerFlow(0,1)  + " voies");

        Simulateur.KILL_Simulateur();

        return;
    }


}
