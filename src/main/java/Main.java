import engine.Simulateur;
import org.jgrapht.alg.interfaces.MaximumFlowAlgorithm;
import utils.Map.Cost.GraphWeight;
import utils.Map.Map;

import java.util.ArrayList;

public class Main {

    /**
     * @param args
     * */
    public static void main(String [] args) {

        Simulateur simulateur0 = null;
        Map map0 = new Map();
        MaximumFlowAlgorithm.MaximumFlow<GraphWeight> flow0;

        Simulateur.INIT_Simulateur();

        try {
            simulateur0 = Simulateur.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        simulateur0.setMap(map0);
        flow0 = simulateur0.getMaxFlowMax(3,1);
        ArrayList<Integer> route_saturee = simulateur0.getCarrefoursSaturees();

        System.out.println("flow max avant saturation des routes: " + flow0.getValue() /*+ flow0.toString()*/);
        System.out.println("route saturées: " + route_saturee.toString());

        Simulateur.KILL_Simulateur();

        return;
    }


}
