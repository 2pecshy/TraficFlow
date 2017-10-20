package engine;

import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.flow.EdmondsKarpMFImpl;
import org.jgrapht.alg.interfaces.MaximumFlowAlgorithm;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import utils.Map.Cost.EnumCriter;
import utils.Map.Cost.Route;
import utils.Map.Map;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class Simulateur {

    private static Simulateur instance = null;

    private EnumCriter criter = EnumCriter.VOIES;

    private Map map;
    private SimpleDirectedWeightedGraph<Integer, Route> generatedGraph;
    private EdmondsKarpMFImpl<Integer, Route> flow;
    private Integer S_lastSimu,D_lastSimu;

    public static Simulateur getInstance(){

        if(instance == null)
            throw new NullPointerException();
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

    public DirectedGraph<Integer, Route> getGeneratedGraph() {
        if(generatedGraph == null)
            generatedGraph = map.build();
        return generatedGraph;
    }

    public MaximumFlowAlgorithm.MaximumFlow<Route> getMaxFlow(Integer v1, Integer v2){

        S_lastSimu = v1;
        D_lastSimu = v2;
        generatedGraph = map.build();
        flow = new EdmondsKarpMFImpl<Integer, Route>(generatedGraph);
        flow.calculateMaximumFlow(v1,v2);
        return flow.getMaximumFlow(v1,v2);
    }

    public ArrayList<Route> getCarrefoursSaturees(){

        ArrayList<Route> res = new ArrayList<Route>();

        if(flow != null){

            java.util.Map<Route, Double> carrefoursSaturee = flow.getFlowMap();

            Set<Route> routes = flow.getCutEdges();

            Iterator<Route> iter = routes.iterator();

            while (iter.hasNext()){

                Route route = iter.next();
                if (carrefoursSaturee.get(route) ==
                        generatedGraph.getEdgeWeight(route))
                    res.add(route);
            }
        }
        return res;
    }

    public double ameliorerFlow(Integer v1,Integer v2){

        SimpleDirectedWeightedGraph<Integer, Route> map_tmp = map.build();
        EdmondsKarpMFImpl<Integer, Route> flow_tmp;

        Route tmp_route = new Route(v1,v2,Integer.MAX_VALUE);
        map_tmp.removeEdge(v1,v2);
        map_tmp.addEdge(v1,v2,tmp_route);

        flow_tmp = new EdmondsKarpMFImpl<Integer, Route>(map_tmp);
        flow_tmp.calculateMaximumFlow(S_lastSimu,D_lastSimu);

        return flow_tmp.getMaximumFlow(S_lastSimu,D_lastSimu).getValue() - flow.getMaximumFlow(S_lastSimu,D_lastSimu).getValue();
    }

    public EnumCriter getCriter() {
        return criter;
    }

    public void setCriter(EnumCriter criter) {
        this.criter = criter;
    }
}
