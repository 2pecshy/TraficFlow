package utils.Map;

import javax.swing.*;
import java.io.File;

public class MapLoader {

    /**
     * fonction pour récupérer un fichier que l'utilisateur aura sélectionné
     */
    public File retrieveFile(){
        JFileChooser jfc = new JFileChooser();
        jfc.showDialog(null,"Select a map");
        jfc.setVisible(true);
        File file = jfc.getSelectedFile();
        return file;
    }

    /**
     * transforme un fichier en objet carte
     * @param f
     */
    public Map fileToMap(File f){
        MapManagerI manager = new MapManagerI();
        Map map = manager.loadMap("file", f.getPath());
        return map;
    }

    public void sendMap(){
        System.out.println("Map envoyée");
    }

    public void uploadMap(){
        File f = this.retrieveFile();
        if(f.getName().endsWith(".txt")){
            System.out.println("Carte détectée");
            Map map = fileToMap(f);
            this.sendMap(); //upload map to façade
        }
        else{
            System.out.println("Map must ends with '.txt' ... at least for now");
        }
    }
}
