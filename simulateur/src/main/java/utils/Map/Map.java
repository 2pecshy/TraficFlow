package utils.Map;

import com.mxgraph.view.mxGraph;
import engine.SimulateurManager;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import utils.Map.Cost.GPS_node;
import utils.Map.Cost.Route;
import org.jgrapht.alg.flow.EdmondsKarpMFImpl;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class Map {

    private SimpleDirectedWeightedGraph<GPS_node, Route> carrefours;
    private ArrayList<GPS_node> sources;
    private ArrayList<GPS_node> sinks;
    private DijkstraShortestPath<GPS_node,Route> path;

    public Map() {
        carrefours = new SimpleDirectedWeightedGraph<GPS_node, Route>(Route.class) {
            @Override
            public double getEdgeWeight(Route e) {
                if (e == null) {
                    throw new NullPointerException();
                }
                return e.getCout(SimulateurManager.getInstance().getCriter());
            }
        };
        updateSourcesAndSinks();
        path=null;
    }

    /**
     *
     * Constructeur par recopie
     * @param map est la map a copier
     */
    public Map(Map map){
        this.carrefours = new SimpleDirectedWeightedGraph<GPS_node, Route>(Route.class) {

            @Override
            public double getEdgeWeight(Route e) {
                if (e == null) {
                    throw new NullPointerException();
                }
                return e.getCout(SimulateurManager.getInstance().getCriter());
            }
        };

        Iterator<GPS_node> iter_tmp = map.getCarrefours().iterator();
        while (iter_tmp.hasNext()){
            this.addCarrefours(iter_tmp.next());
        }

        iter_tmp = map.getCarrefours().iterator();
        while ((iter_tmp.hasNext())){
            Iterator<Route> iter_route_tmp = map.getRouteFromCarrefour(iter_tmp.next()).iterator();
            while (iter_route_tmp.hasNext()){
                Route route_to_copy = iter_route_tmp.next();
                this.addRoute(route_to_copy.getV1(),route_to_copy.getV2(),route_to_copy.getNombre_de_voie());
            }
        }
        path = null;
    }

    /**
     *
     * @param nb_carrefours nombre de carrefours de la nouvelle map crée
     */
    public Map(Integer nb_carrefours){
        carrefours = new SimpleDirectedWeightedGraph<GPS_node, Route>(Route.class) {

            @Override
            public double getEdgeWeight(Route e) {
                if (e == null) {
                    throw new NullPointerException();
                }
                return e.getCout(SimulateurManager.getInstance().getCriter());
            }
        };
        /* TODO remove this constructor
        for(int i = 0; i < nb_carrefours; i++){
            this.addCarrefours();
        }
        */
        path = null;
    }

    /**
     *
     * @param v1 carrefour de départ de la route à ajouter
     * @param v2 carrefour d'arrivé de la route à ajouter
     * @param nombre_de_voie nombre de voie de la nouvelle route
     * @return renvoi faux si la route existe déja
     */
    public boolean addRoute(GPS_node v1, GPS_node v2, Integer nombre_de_voie){
        if(carrefours.containsVertex(v1) && carrefours.containsVertex(v2) && nombre_de_voie >= 0){
            if(!carrefours.containsEdge(v1,v2)) {
                carrefours.addEdge(v1, v2, new Route(v1, v2, nombre_de_voie));
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return renvoie la liste des carrefours de la Map
     */
    public Set<GPS_node> getCarrefours(){

        return carrefours.vertexSet();
    }

    public ArrayList<GPS_node> getCarrefours(ArrayList<Double> list_id){
        Set<GPS_node> set_carrefours = getCarrefours();
        Iterator<GPS_node> iter_vertex = set_carrefours.iterator();
        GPS_node current_vertex;
        ArrayList<GPS_node> result = new ArrayList<GPS_node>();

        while (iter_vertex.hasNext()){
            current_vertex = iter_vertex.next();
            if(current_vertex.getId() == list_id.get(list_id.size()-1)) {
                result.add(current_vertex);
                list_id.remove(list_id.size()-1);
            }
        }
        return result;
    }

    public GPS_node[] getCarrefours(double v1, double v2){
        Set<GPS_node> set_carrefours = getCarrefours();
        Iterator<GPS_node> iter_vertex = set_carrefours.iterator();
        GPS_node current_vertex;
        GPS_node result[] = new GPS_node[2];

        while (iter_vertex.hasNext()){

            current_vertex = iter_vertex.next();
            if(current_vertex.getId() == v1) {
                result[0] = current_vertex;
            }
            else if(current_vertex.getId() == v2){
                result[1] = current_vertex;
            }
        }
        return result;
    }

    public GPS_node getCarrefour(double id){

        Set<GPS_node> set_carrefours = getCarrefours();
        Iterator<GPS_node> iter_vertex = set_carrefours.iterator();
        GPS_node current_vertex;

        while (iter_vertex.hasNext()){
            current_vertex = iter_vertex.next();
            if(current_vertex.getId() == id)
                return current_vertex;
        }
        return null;
    }

    /**
     *
     * @return renvoie le graph utilisé pour le calcule de maximum flow
     */
    public EdmondsKarpMFImpl<GPS_node, Route> buildGraph(){
        return new EdmondsKarpMFImpl<GPS_node, Route>(carrefours);
    }

    /**
     *
     * @param carrefour est un carrefour appartenent à la map
     * @return renvoie l'enssemble des routes adjacentes au carrefour passé en param
     */
    public Set<Route> getRouteFromCarrefour(GPS_node carrefour){
        return carrefours.edgesOf(carrefour);
    }

    /**
     *
     * @param route est une route appartenent à la map
     * @return renvoie le cout de la route passé en param
     */
    public double getCoutRoute(Route route){
        return carrefours.getEdgeWeight(route);
    }

    /**
     *
     * @param v1 carrefour de départ de la route
     * @param v2 carrefour d'arrivé de la route
     * @return renvoie la route qui a pour départ v1 et arrivée v2
     */
    public Route getRoute(GPS_node v1, GPS_node v2){
        return carrefours.getEdge(v1,v2);
    }

    /**
     * ajoute un carrefour à la Map
     */
    public void addCarrefours(GPS_node pos){
        carrefours.addVertex(pos);
    }

    public void afficherMap(){
        Iterator<GPS_node> iter_vertex = this.getCarrefours().iterator();
        GPS_node current_node;
        while (iter_vertex.hasNext()){

            current_node = iter_vertex.next();
            System.out.println("degree in =" + carrefours.inDegreeOf(current_node) + " degree out=" + carrefours.outDegreeOf(current_node));
        }
        //TODO Finish this
    }

    public mxGraph MapToMxGraph(mxGraph graph,Object parent){

        //TODO Map to MxGraph to print the map in a panel
        Set<Route> routeSet = carrefours.edgeSet();
        Iterator<Route> iterRoute = routeSet.iterator();
        Route currentRoute;
        Object v1;
        Object v2;

        while (iterRoute.hasNext()){

            currentRoute = iterRoute.next();
            v1 = graph.insertVertex(parent, null, "", currentRoute.getV1().getLat()*10000, currentRoute.getV1().getLon()*10000, 1, 1);
            v2 = graph.insertVertex(parent, null, "", currentRoute.getV2().getLat()*10000, currentRoute.getV2().getLon()*10000, 1, 1);
            graph.insertEdge(parent,null,null,v1,v2);

        }
        return graph;
    }

    public void updateSourcesAndSinks(){

        sources = new ArrayList<GPS_node>();
        sinks = new ArrayList<GPS_node>();
        Iterator<GPS_node> iter_vertex = carrefours.vertexSet().iterator();
        GPS_node current_vertex;

        while (iter_vertex.hasNext()){

            current_vertex = iter_vertex.next();
            if(carrefours.outDegreeOf(current_vertex) == 0 && carrefours.inDegreeOf(current_vertex) > 0)
                sinks.add(current_vertex);
            else if(carrefours.outDegreeOf(current_vertex) > 0 && carrefours.inDegreeOf(current_vertex) == 0)
                sources.add(current_vertex);
        }
    }

    public ArrayList<GPS_node> getSources(){

        if(sources == null)
            updateSourcesAndSinks();
        return sources;
    }

    public ArrayList<GPS_node> getSinks() {

        if(sources == null)
            updateSourcesAndSinks();
        return sinks;
    }

    public GraphPath<GPS_node, Route> getBestPath(GPS_node src, GPS_node dest){

        if(path == null) {
            path = new DijkstraShortestPath<GPS_node, Route>(carrefours);
        }
        return path.getPath(src,dest);
    }

    public Set<Route> getRoutes(){
        return carrefours.edgeSet();
    }

    public static Map getDefaultMap(){

        Map defaultMap = new Map(4);
       /* defaultMap.addRoute(0,1,1);
        defaultMap.addRoute(1,2,1);
        defaultMap.addRoute(2,3,2);
        defaultMap.addRoute(3,1,3);
        defaultMap.addRoute(3,0,3);*/
       //TODO
        return defaultMap;
    }

}