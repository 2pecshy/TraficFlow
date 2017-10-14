package utils.Map;

import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import utils.Map.Cost.Voie;

import java.util.ArrayList;

public class Map {

    //private ArrayList<ArrayList<ArrayList<Cost>>> costList;
    //private DirectedGraph<Integer, Integer> map;

    private ArrayList<ArrayList<Integer>> carrefours;
    private Voie nombre_de_voie;

    public Map() {

        Integer nb_carrefours = 4;
        carrefours = new ArrayList<ArrayList<Integer>>();
        nombre_de_voie = new Voie(nb_carrefours);

        for(int i = 0; i < 4; i++){
            carrefours.add(new ArrayList<Integer>());
        }

        carrefours.get(0).add(1);
        nombre_de_voie.setCost(0,1,01);

        carrefours.get(1).add(2);
        nombre_de_voie.setCost(1,2,12);

        carrefours.get(2).add(3);
        nombre_de_voie.setCost(2,3,23);

        carrefours.get(3).add(1);
        nombre_de_voie.setCost(3,1,31);

        carrefours.get(3).add(0);
        nombre_de_voie.setCost(3,0,30);
    }

    public SimpleDirectedWeightedGraph<Integer, Integer> build(Class c){

        SimpleDirectedWeightedGraph<Integer,Integer> G = new SimpleDirectedWeightedGraph<Integer, Integer>(Integer.class){

            @Override
            public double getEdgeWeight(Integer e){
                if (e == null) {
                    throw new NullPointerException();
                }
                return e;
            }

        };

        if( c.isInstance(nombre_de_voie)){

            for(Integer i = 0; i < carrefours.size(); i++){
                G.addVertex(i);
            }

            for (Integer i = 0; i < carrefours.size(); i++){

                for (Integer j = 0; j < carrefours.get(i).size(); j++){

                    Integer v1 = i;
                    Integer v2 = carrefours.get(i).get(j);
                    //System.out.println("debug: add node:" + v1 +"," + v2 + "," + nombre_de_voie.getCost(v1,v2));
                    G.addEdge(v1,v2,v1*10+v2);

                }
            }
        }
        return G;
    }
}