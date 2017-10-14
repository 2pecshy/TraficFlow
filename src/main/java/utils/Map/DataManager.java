package utils.Map;

/**
 * Created by Matthieu on 14/10/2017.
 */
public interface DataManager {

    //mode = (web, file, BDD etc)

    public Map loadMap(String mode, String path);

    public void saveMap(String mode, String path);

    public Stat loadStat(String mode, String path);

    public void saveStat(String mode, String path);

}
