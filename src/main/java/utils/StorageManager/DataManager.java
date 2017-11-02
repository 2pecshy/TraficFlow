package utils.StorageManager;

import utils.Map.Map;
import utils.Map.MapManagerI;
import utils.Stat.Stat;
import utils.Stat.StatManagerI;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Jeremy on 21/10/2017.
 */
public class DataManager implements  IDataManager{
    private MapManagerI mapManager;
    private StatManagerI statManager;

    public DataManager(){
        this.mapManager = new MapManagerI();
        this.statManager = new StatManagerI();
    }

    public Map loadMap(String mode, String path) {
        return mapManager.loadMap(mode,path);
    }


    public void saveMap(String mode, String path, Map map) {
        mapManager.saveMap(mode,path,map);
    }

    public Stat loadStat(String mode, String path){
        return statManager.loadStat(mode,path);
    }

    public void saveStat(String mode, String path, String content) {
        statManager.saveStat(mode,path,content);

    }
}
