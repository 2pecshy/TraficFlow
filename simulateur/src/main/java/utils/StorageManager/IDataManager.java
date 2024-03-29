package utils.StorageManager;

import utils.Map.Map;
import utils.Stat.Stat;

import java.io.File;

/**
 * Created by Matthieu on 14/10/2017.
 */
public interface IDataManager {

    //mode = (web, file, BDD etc)

    public Map loadMap(String mode, String path);

    public void saveMap(String mode, String path, Map map);

    public Stat loadStat(String mode, String path);

    public void saveStat(String mode, String path, String content);

}
