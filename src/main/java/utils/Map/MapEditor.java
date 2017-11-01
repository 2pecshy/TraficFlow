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

    public Map editMap() {
        System.out.println("Edition de la carte...");
        while(true) {
            try{
                System.out.println("Carte :");
                printMap();
                System.out.println("Entrez votre choix (c, r, exit) :");
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                String s = bufferRead.readLine();
                if (s.equals("c")) {
                    System.out.println("Ajout d'un carrefour");
                    addCarrefours();
                }
                else if (s.equals("r")) {
                    int v1 = -1;
                    int v2 = -1;
                    int nbVoies = -1;
                    while(v1 < 0) {
                        System.out.println("v1 ?");
                        String v1str = bufferRead.readLine();
                        if (v1str.matches("[0-9]+")) {
                            v1 = Integer.parseInt(v1str);
                        }
                        else {
                            System.out.println("v1 doit être un entier positif !");
                        }
                    }
                    while(v2 < 0) {
                        System.out.println("v2 ?");
                        String v2str = bufferRead.readLine();
                        if (v2str.matches("[0-9]+")) {
                            v2 = Integer.parseInt(v2str);
                        }
                        else {
                            System.out.println("v2 doit être un entier positif !");
                        }
                    }
                    while(nbVoies < 0){
                        System.out.println("nombre de voies ?");
                        String nbVoiesstr = bufferRead.readLine();
                        if (nbVoiesstr.matches("[0-9]+")) {
                            nbVoies = Integer.parseInt(nbVoiesstr);
                        }
                        else {
                            System.out.println("nbVoies doit être un entier positif !");
                        }
                        if(v1 == v2){
                            System.out.println("v1 doit être différent de v2 !");
                        }
                        else if(addRoute(v1, v2, nbVoies)) {
                            System.out.println("Ajout d'une route");
                        }
                        else{
                            System.out.println("Route déjà existante ou carrefour non existant");
                        }
                    }
                } else if (s.equals("exit")){
                    return map;
                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }

        }
    }

    public boolean addRoute(Integer v1, Integer v2,Integer nombre_de_voie){
        return map.addRoute(v1,v2,nombre_de_voie);
    }

    public void addCarrefours(){
        map.addCarrefours();
    }

    public void printMap(){
        map.afficherMap();
    }
}
