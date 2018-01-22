package engine;

import org.jgrapht.alg.flow.EdmondsKarpMFImpl;
import org.jgrapht.alg.interfaces.MaximumFlowAlgorithm;
import utils.Map.Cost.EnumCriter;
import utils.Map.Cost.GPS_node;
import utils.Map.Cost.Route;
import utils.Map.Map;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class Simulateur {

    private static Simulateur instance = null;

    private EnumCriter criter = EnumCriter.ALL;

    private Map map;
    private EdmondsKarpMFImpl<GPS_node, Route> flow;
    private GPS_node S_lastSimu,D_lastSimu;
    private TraficFlowContext simulateur_context;

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
            instance.map = null;
            instance.flow = null;
            instance.S_lastSimu = null;
            instance.D_lastSimu = null;
            instance = null;
            return true;
        }
        System.out.println("engine.Simulateur Already dead");
        return false;
    }

    /**
     *
     * @return renvoie la map actuellement traitée par le simulateur
     */
    public Map getMap() {
        return map;
    }

    /**
     *
     * @param map_ est la map que le simulateur va traiter
     */
    public void setMap(Map map_) {
        this.map = map_;
        this.flow = null;
    }

    /**
     *
     * @param v1 est le carrefour de départ de toutes les voitures
     * @param v2 est le carrefour d'arrivée de toutes les voitures
     * @return renvoie le nombre de voiture qu'il est possible de faire circuler en même temps
     */
    public MaximumFlowAlgorithm.MaximumFlow<Route> getMaxFlow(GPS_node v1, GPS_node v2){

        S_lastSimu = v1;
        D_lastSimu = v2;
        if(map == null) return null;
        //flow = map.buildGraph();
        flow.calculateMaximumFlow(v1,v2);
        return flow.getMaximumFlow(v1,v2);
    }

    /**
     *
     * @return renvoie la listes des routes qui vont saturée si la circulation dépasse le flow max calculé
     */
    public ArrayList<Route> getRoutesSaturees(){

        ArrayList<Route> res = new ArrayList<Route>();

        if(flow != null){

            java.util.Map<Route, Double> carrefoursSaturee = flow.getFlowMap();

            Set<Route> routes = flow.getCutEdges();

            Iterator<Route> iter = routes.iterator();

            while (iter.hasNext()){

                Route route = iter.next();
                if (carrefoursSaturee.get(route) == map.getCoutRoute(route))
                    res.add(route);
            }
        }
        return res;
    }

    /**
     *
     * @param v1 carrefour de départ de la route à améliorer
     * @param v2 carrefour d'arrivée de la route à améliorer
     * @return renvoie le nombre de voie que l'on peut ajouter sur cette route avant déplacement du bouchon
     */
    public double ameliorerFlow(GPS_node v1,GPS_node v2){

        Map map_tmp = new Map(map);
        EdmondsKarpMFImpl<GPS_node, Route> flow_tmp;

        //map_tmp.getRoad(v1,v2).setNombre_de_voie(Integer.MAX_VALUE);

        flow_tmp = map_tmp.buildGraph();
        flow_tmp.calculateMaximumFlow(S_lastSimu,D_lastSimu);

        return flow_tmp.getMaximumFlow(S_lastSimu,D_lastSimu).getValue() - flow.getMaximumFlow(S_lastSimu,D_lastSimu).getValue();
    }

    /**
     *
     * @return renvoie le critère pris en compte actuelement par le simulateur
     */
    public EnumCriter getCriter() {
        return criter;
    }

    /**
     *
     * @param criter est le nouveau critère qui doit être pris en compte par le simulateur
     */
    public void setCriter(EnumCriter criter) {
        this.criter = criter;
    }
}
