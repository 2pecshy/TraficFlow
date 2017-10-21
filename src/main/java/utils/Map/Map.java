package utils.Map;

import engine.Simulateur;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import utils.Map.Cost.Route;

import java.util.ArrayList;
import java.util.Iterator;

public class Map {

    private ArrayList<ArrayList<Route>> carrefours;

    public Map() {

        Integer nb_carrefours = 4;
        carrefours = new ArrayList<ArrayList<Route>>();

        for(int i = 0; i < nb_carrefours; i++){
            this.addCarrefours();
        }
        this.addRoute(0,1,1);
        this.addRoute(1,2,1);
        this.addRoute(2,3,2);
        this.addRoute(3,1,3);
        this.addRoute(3,0,3);

    }

    public SimpleDirectedWeightedGraph<Integer, Route> build(){

        SimpleDirectedWeightedGraph<Integer,Route> G = new SimpleDirectedWeightedGraph<Integer, Route>(Route.class){

            @Override
            public double getEdgeWeight(Route e){
                if (e == null) {
                    throw new NullPointerException();
                }
                return e.getCout(Simulateur.getInstance().getCriter());
            }

        };


        for(Integer i = 0; i < carrefours.size(); i++){
                G.addVertex(i);
        }

        for (Integer i = 0; i < carrefours.size(); i++){

            Iterator<Route> iter_tmp = carrefours.get(i).iterator();

            while (iter_tmp.hasNext()){

                Route route_to_add = iter_tmp.next();
                G.addEdge(route_to_add.getV1(),route_to_add.getV2(),route_to_add);

                }
        }
        return G;
    }

    public void addRoute(Integer v1, Integer v2,Integer nombre_de_voie){

        carrefours.get(v1).add(new Route(v1,v2,nombre_de_voie));
    }

    public void addCarrefours(){

        carrefours.add(new ArrayList<Route>());
    }
}