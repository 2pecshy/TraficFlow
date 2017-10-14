import engine.Simulateur;
import org.jgrapht.alg.interfaces.MaximumFlowAlgorithm;
import utils.Map.Map;

public class Main {

    /**
     * @param args
     * */
    public static void main(String [] args) {

        Simulateur simulateur0 = null;
        Map map0 = new Map();
        MaximumFlowAlgorithm.MaximumFlow<Integer> flow0;

        Simulateur.INIT_Simulateur();

        try {
            simulateur0 = Simulateur.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        simulateur0.setMap(map0);
        flow0 = simulateur0.getMaxFlowMax(3,1);

        System.out.println("flow max avant saturation des routes: " + flow0.getValue());

        Simulateur.KILL_Simulateur();

        return;
    }


}
