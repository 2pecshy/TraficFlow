package utils.Map;

import utils.Map.Cost.Route;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public void editMap() {
        System.out.println("Edition de la carte...");
        while(true) {
            try{
                System.out.println("Carte :");
                printMap();
                System.out.println("Entrez votre choix (c, r, exit) :");
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                String s = bufferRead.readLine();
                System.out.println(s);
                if (s.equals("c")) {
                    System.out.println("Ajout d'un carrefour");
                    addCarrefours();
                } else if (s.equals("r")) {
                    System.out.println("v1 ?");
                    int v1 = Integer.parseInt(bufferRead.readLine());
                    System.out.println("v2 ?");
                    int v2 = Integer.parseInt(bufferRead.readLine());
                    System.out.println("nombre de voies ?");
                    int nbVoies = Integer.parseInt(bufferRead.readLine());
                    boolean routeValide = true;
                    for(int i=0; i<map.getCarrefours().size(); i++){
                        for(int j=0; j<map.getCarrefours().get(i).size(); j++){
                            if(v1 == map.getCarrefours().get(i).get(j).getV1() && v2 == map.getCarrefours().get(i).get(j).getV2()){
                                routeValide = false;
                            }
                        }
                    }
                    if(routeValide) {
                        addRoute(v1, v2, nbVoies);
                        System.out.println("Ajout d'une route");
                    }
                    else{
                        System.out.println("Route déjà existante");
                    }
                } else if (s.equals("exit")){
                    break;
                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }

        }
    }

    public void addRoute(Integer v1, Integer v2,Integer nombre_de_voie){
        map.addRoute(v1,v2,nombre_de_voie);
    }

    public void addCarrefours(){
        map.addCarrefours();
    }

    public void printMap(){
        map.afficherMap();
    }
}
