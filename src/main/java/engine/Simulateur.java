package engine;

import org.jgrapht.DirectedGraph;
import org.jgrapht.EdgeFactory;
import org.jgrapht.alg.flow.EdmondsKarpMFImpl;
import org.jgrapht.alg.interfaces.MaximumFlowAlgorithm;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import utils.Map.Cost.GraphWeight;
import utils.Map.Cost.Voie;
import utils.Map.Map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class Simulateur {

    private static Simulateur instance = null;

    private Map map;
    private SimpleDirectedWeightedGraph<Integer, GraphWeight> generatedGraph;
    private EdmondsKarpMFImpl<Integer, GraphWeight> flow;
    private Integer S_lastSimu,D_lastSimu;

    public static Simulateur getInstance() throws Exception {

        if(instance == null)
            throw new java.lang.RuntimeException("Null floating point on engine.Simulateur!");
        return instance;
    }

    private Simulateur() {
        S_lastSimu = null;
        D_lastSimu = null;
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

    public DirectedGraph<Integer, GraphWeight> getGeneratedGraph() {
        if(generatedGraph == null)
            generatedGraph = map.build(Voie.class);
        return generatedGraph;
    }

    public MaximumFlowAlgorithm.MaximumFlow<GraphWeight> getMaxFlow(Integer v1, Integer v2){

        S_lastSimu = v1;
        D_lastSimu = v2;
        generatedGraph = map.build(Voie.class);
        flow = new EdmondsKarpMFImpl<Integer, GraphWeight>(generatedGraph);
        flow.calculateMaximumFlow(v1,v2);
        return flow.getMaximumFlow(v1,v2);
    }

    public ArrayList<GraphWeight> getCarrefoursSaturees(){

        ArrayList<GraphWeight> res = new ArrayList<GraphWeight>();

        if(flow != null){

            java.util.Map<GraphWeight, Double> carrefoursSaturee = flow.getFlowMap();

            Set<GraphWeight> routes = flow.getCutEdges();

            Iterator<GraphWeight> iter = routes.iterator();

            while (iter.hasNext()){

                GraphWeight route = iter.next();
                if (carrefoursSaturee.get(route) ==
                        generatedGraph.getEdgeWeight(route))
                    res.add(route);
            }
        }
        return res;
    }

    public double ameliorerFlow(Integer v1,Integer v2){

        SimpleDirectedWeightedGraph<Integer, GraphWeight> map_tmp = map.build(Voie.class);
        EdmondsKarpMFImpl<Integer, GraphWeight> flow_tmp;

        GraphWeight tmp_edge = new GraphWeight(Integer.MAX_VALUE,v1,v2);
        map_tmp.removeEdge(v1,v2);
        map_tmp.addEdge(v1,v2,tmp_edge);

        flow_tmp = new EdmondsKarpMFImpl<Integer, GraphWeight>(map_tmp);
        flow_tmp.calculateMaximumFlow(S_lastSimu,D_lastSimu);

        return flow_tmp.getMaximumFlow(S_lastSimu,D_lastSimu).getValue() - flow.getMaximumFlow(S_lastSimu,D_lastSimu).getValue();
    }
}
