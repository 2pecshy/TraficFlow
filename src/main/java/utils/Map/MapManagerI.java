package utils.Map;

import utils.StorageManager.IDataManager;
import utils.Stat.Stat;

import java.io.File;

public class MapManagerI implements IDataManager {

    public MapManagerI(){

    }

    private Map loadMapFromFile(String path){
        Map loadedMap = new Map();
        return loadedMap;
    }

    private Map loadMapFromWeb(String path){
        Map loadedMap = new Map();
        return loadedMap;
    }

    private void saveMapToWeb(String path){

    }

    private void saveMapToFile(String path){

    }

    public Map loadMap(String mode, String path) {
        if(mode == "web"){
            return loadMapFromWeb(path);
        }
        else if (mode == "file"){
            return loadMapFromFile(path);
        }
        return null;
    }

    public Map loadMap(String mode, File file) {
        return null;
    }

    public void saveMap(String mode, String path) {
        if(mode == "web"){
            saveMapToWeb(path);
        }
        else if (mode == "file"){
            saveMapToWeb(path);
        }
    }

    public Stat loadStat(String mode, String path) {
        return null;
    }

    public void saveStat(String mode, String path) {

    }



}
