package utils.Map;

/**
 * Created by Michael on 14/10/2017.
 */

public class MapEditor {

    private MapManager manager;
    private Map map;

    public MapEditor(){}

    public void loadMap(String mode, String path){
        this.map = manager.loadMap(mode, path);
    }

    public void saveMap(String mode, String path){
        manager.saveMap(mode, path);
    }

    public void EditMap(String mode, String path){
        System.out.println("Edition de la carte...");
    }
}
