package utils.Map;

import engine.Simulateur;
import org.jgrapht.alg.flow.EdmondsKarpMFImpl;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import utils.Map.Cost.Route;

import java.util.Iterator;
import java.util.Set;

public class Map {

    private SimpleDirectedWeightedGraph<Integer, Route> carrefours;

    public Map() {
        carrefours = new SimpleDirectedWeightedGraph<Integer, Route>(Route.class) {

            @Override
            public double getEdgeWeight(Route e) {
                if (e == null) {
                    throw new NullPointerException();
                }
                return e.getCout(Simulateur.getInstance().getCriter());
            }
        };
    }

    //constructeur par copy
    public Map(Map map){
        this.carrefours = new SimpleDirectedWeightedGraph<Integer, Route>(Route.class) {

            @Override
            public double getEdgeWeight(Route e) {
                if (e == null) {
                    throw new NullPointerException();
                }
                return e.getCout(Simulateur.getInstance().getCriter());
            }
        };

        Iterator<Integer> iter_tmp = map.getCarrefours().iterator();
        while (iter_tmp.hasNext()){
            iter_tmp.next();
            this.addCarrefours();
        }

        iter_tmp = map.getCarrefours().iterator();
        while ((iter_tmp.hasNext())){
            Iterator<Route> iter_route_tmp = map.getRouteFromCarrefour(iter_tmp.next()).iterator();
            while (iter_route_tmp.hasNext()){
                Route route_to_copy = iter_route_tmp.next();
                this.addRoute(route_to_copy.getV1(),route_to_copy.getV2(),route_to_copy.getNombre_de_voie());
            }
        }
    }

    public Map(Integer nb_carrefours){
        carrefours = new SimpleDirectedWeightedGraph<Integer, Route>(Route.class) {

            @Override
            public double getEdgeWeight(Route e) {
                if (e == null) {
                    throw new NullPointerException();
                }
                return e.getCout(Simulateur.getInstance().getCriter());
            }
        };

        for(int i = 0; i < nb_carrefours; i++){
            this.addCarrefours();
        }
    }

    public boolean addRoute(Integer v1, Integer v2,Integer nombre_de_voie){

        if(!carrefours.containsEdge(v1,v2)) {
            carrefours.addEdge(v1, v2, new Route(v1, v2, nombre_de_voie));
            return true;
        }
        return false;
    }

    public Set<Integer> getCarrefours(){

        return carrefours.vertexSet();
    }

    public EdmondsKarpMFImpl<Integer, Route> buildGraph(){
        return new EdmondsKarpMFImpl<Integer, Route>(carrefours);
    }

    public Set<Route> getRouteFromCarrefour(Integer carrefour){
        return carrefours.edgesOf(carrefour);
    }

    public double getCoutRoute(Route route){
        return carrefours.getEdgeWeight(route);
    }

    public Route getRoute(Integer v1,Integer v2){
        return carrefours.getEdge(v1,v2);
    }

    public void addCarrefours(){
        carrefours.addVertex(carrefours.vertexSet().size());
    }

    public void afficherMap(){
        for(int i=0; i<this.getCarrefours().size(); i++){
            Iterator<Route> iter_tmp = this.getRouteFromCarrefour(i).iterator();
            System.out.println("[carrefours " + i);
            while (iter_tmp.hasNext()) {
                System.out.println(iter_tmp);
            }
            System.out.println("]");
        }
    }

    public static Map getDefaultMap(){

        Map defaultMap = new Map(4);
        defaultMap.addRoute(0,1,1);
        defaultMap.addRoute(1,2,1);
        defaultMap.addRoute(2,3,2);
        defaultMap.addRoute(3,1,3);
        defaultMap.addRoute(3,0,3);
        return defaultMap;
    }
}