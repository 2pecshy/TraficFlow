package utils.Map;

/**
 * Created by Michael on 14/10/2017.
 */

public class MapEditor {

    private MapManager manager;

    public MapEditor(){
    }

    public void EditMap(String mode, String path){
        Map map = manager.loadMap(mode, path);
        System.out.println("Edition de la carte...");
        manager.saveMap(mode, path);
    }
}
