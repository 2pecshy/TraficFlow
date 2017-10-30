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

    /**
     *
     * Constructeur par recopie
     * @param map est la map a copier
     */
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

    /**
     *
     * @param nb_carrefours nombre de carrefours de la nouvelle map crée
     */
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

    /**
     *
     * @param v1 carrefour de départ de la route à ajouter
     * @param v2 carrefour d'arrivé de la route à ajouter
     * @param nombre_de_voie nombre de voie de la nouvelle route
     * @return renvoi faux si la route existe déja
     */
    public boolean addRoute(Integer v1, Integer v2,Integer nombre_de_voie){
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
    public Set<Integer> getCarrefours(){

        return carrefours.vertexSet();
    }

    /**
     *
     * @return renvoie le graph utilisé pour le calcule de maximum flow
     */
    public EdmondsKarpMFImpl<Integer, Route> buildGraph(){
        return new EdmondsKarpMFImpl<Integer, Route>(carrefours);
    }

    /**
     *
     * @param carrefour est un carrefour appartenent à la map
     * @return renvoie l'enssemble des routes adjacentes au carrefour passé en param
     */
    public Set<Route> getRouteFromCarrefour(Integer carrefour){
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
    public Route getRoute(Integer v1,Integer v2){
        return carrefours.getEdge(v1,v2);
    }

    /**
     * ajoute un carrefour à la Map
     */
    public void addCarrefours(){
        carrefours.addVertex(carrefours.vertexSet().size());
    }

    public void afficherMap(){
        for(int i=0; i<this.getCarrefours().size(); i++){
            Iterator<Route> iter_tmp = this.getRouteFromCarrefour(i).iterator();
            System.out.print("carrefour " + i + " [");
            while (iter_tmp.hasNext()) {
                System.out.print(iter_tmp.next());
            }
            System.out.print("]");
            System.out.print("\n");
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