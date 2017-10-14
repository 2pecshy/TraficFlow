package engine;

import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.flow.EdmondsKarpMFImpl;
import org.jgrapht.alg.interfaces.MaximumFlowAlgorithm;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import utils.Map.Cost.Voie;
import utils.Map.Map;

public class Simulateur {

    private static Simulateur instance = null;

    private Map map;
    private SimpleDirectedWeightedGraph<Integer, Integer> generatedGraph;
    private EdmondsKarpMFImpl<Integer, Integer> flow;

    public static Simulateur getInstance() throws Exception {

        if(instance == null)
            throw new java.lang.RuntimeException("Null floating point on engine.Simulateur!");
        return instance;
    }

    private Simulateur() {

    }

    public static boolean INIT_Simulateur(){
        if(instance == null) {
            System.out.println("INIT: engine.Simulateur");
            instance = new Simulateur();
            return true;
        }
        System.out.println("engine.Simulateur Already Running");
        return false;
    }

    public static boolean KILL_Simulateur(){
        if(instance != null) {
            System.out.println("Killing: engine.Simulateur");
            instance = null;
            return true;
        }
        System.out.println("engine.Simulateur Already dead");
        return false;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map_) {
        this.map = map_;
        this.flow = null;
        this.generatedGraph = null;
    }

    public DirectedGraph<Integer, Integer> getGeneratedGraph() {
        if(generatedGraph == null)
            generatedGraph = map.build(Voie.class);
        return generatedGraph;
    }

    public MaximumFlowAlgorithm.MaximumFlow<Integer> getMaxFlowMax(Integer v1, Integer v2){

        generatedGraph = map.build(Voie.class);
        flow = new EdmondsKarpMFImpl<Integer, Integer>(generatedGraph);
        flow.calculateMaximumFlow(v1,v2);
        return flow.getMaximumFlow(v1,v2);
    }
}
