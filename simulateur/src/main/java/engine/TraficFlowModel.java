package engine;

import org.jgrapht.alg.flow.EdmondsKarpMFImpl;
import org.jgrapht.alg.interfaces.MaximumFlowAlgorithm;
import utils.Map.Cost.GPS_node;
import utils.Map.Cost.Route;
import utils.Map.Map;
import utils.Map.Ui_graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TraficFlowModel extends Model {

    private Map map;
    private Ui_graph ui_graph;
    private EdmondsKarpMFImpl<GPS_node, Route> flow;
    private GPS_node S_lastSimu,D_lastSimu;
    private TraficFlowContext simulateur_context;

    public TraficFlowModel() {
        S_lastSimu = null;
        D_lastSimu = null;
        map = null;
        ui_graph = null;
        simulateur_context = null;
        isRunning = NOT_RUNNING;
    }

    public TraficFlowModel(Map map_) {
        S_lastSimu = null;
        D_lastSimu = null;
        map = null;
        ui_graph = null;
        simulateur_context = null;
        isRunning = NOT_RUNNING;
        setMap(map_);
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
    //TODO Remettre l'ui
    public void setMap(Map map_) {
        this.map = map_;
        this.flow = null;
        //this.ui_graph = new Ui_graph();
        //ui_graph.setUIGraphFromMap(map);
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

    protected void pauseSimulation(){
        isRunning = PAUSE;
    }

    protected void resumeSimulation(){
        System.out.println("resume simulation");
        if(map == null) {
            isRunning = NOT_RUNNING;
            System.out.println("no simulation to run");
            return;
        }
        isRunning = RUNNING;
    }

    protected void startSimulation(){

        if(map == null) {
            isRunning = NOT_RUNNING;
            System.out.println("no simulation to run");
            return;
        }
        isRunning = RUNNING;
        simulateur_context = new TraficFlowContext();
        simulateur_context.addAgent(new Cars());
        super.start();
    }

    //TODO Remettre l'ui
    @Override
    public void run(){

        if(isRunning != RUNNING){
            System.out.println("do not use run methode!");
            return;
        }
  //      ui_graph.show_G();

        while (isRunning != NOT_RUNNING) {

            if (isRunning == RUNNING) {

                simulateur_context.onTick();
                //TODO when draw not fake, put draw on an other thread
                simulateur_context.onDraw();
            }
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void start(){
        System.out.println("Oops: don't use this methode, use startSimulation()");
    }
}
