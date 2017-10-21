package utils.Map;

import utils.Map.Cost.Route;
import java.util.ArrayList;

/**
 * Created by Michael on 14/10/2017.
 */

public class MapEditor {

    private MapManagerI manager;
    private Map map;

    public MapEditor(Map map){
        this.map = map;
    }

    public void loadMap(String mode, String path){
        this.map = manager.loadMap(mode, path);
    }

    public void saveMap(String mode, String path){
        manager.saveMap(mode, path);
    }

    public void EditMap(String mode, String path){
        System.out.println("Edition de la carte...");
    }

    public void addRoute(Integer v1, Integer v2,Integer nombre_de_voie){
        map.addRoute(v1,v2,nombre_de_voie);
    }

    public void addCarrefours(){
        map.addCarrefours();
    }

    public void printMap(){
        System.out.println("Carte : ");
        for(ArrayList<Route> carrefour : map.getCarrefours()){
            System.out.println(carrefour.toString());
        }
    }
}
