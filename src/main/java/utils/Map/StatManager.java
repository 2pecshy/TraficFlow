package utils.Map;

/**
 * Created by Matthieu on 14/10/2017.
 */
public class StatManager implements DataManager {

    public StatManager(){}

    private Stat loadStatFromFile(String path){
        Stat loadedStat = new Stat();
        return loadedStat;
    }

    private Stat loadStatFromWeb(String path){
        Stat loadedStat = new Stat();
        return loadedStat;
    }

    private void saveStatToFile(String path){

    }

    private void saveStatToWeb(String path){

    }

    public Map loadMap(String mode, String path) {
        return null;
    }

    public void saveMap(String mode, String path) {
    }

    public Stat loadStat(String mode, String path) {
        if(mode == "web"){
            return loadStatFromWeb(path);
        }
        else if (mode == "file"){
            return loadStatFromFile(path);
        }
        return null;
    }

    public void saveStat(String mode, String path) {
        if(mode == "web"){
            saveStatToWeb(path);
        }
        else if (mode == "file"){
            saveStatToFile(path);
        }
    }
}
