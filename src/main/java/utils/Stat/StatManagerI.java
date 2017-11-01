package utils.Stat;

import utils.StorageManager.IDataManager;
import utils.Map.Map;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by Matthieu on 14/10/2017.
 */
public class StatManagerI {

    private Stat loadStatFromFile(String path){
        Stat loadedStat = new Stat();
        return loadedStat;
    }

    private Stat loadStatFromWeb(String path){
        Stat loadedStat = new Stat();
        return loadedStat;
    }

    private void saveStatToFile(String path, String content){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(path, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.println(content);
        writer.close();
    }

    private void saveStatToWeb(String path){

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

    public void saveStat(String mode, String path, String content) {
        if(mode.equals("file")){
            saveStatToFile(path,content);
        }
        else{
            saveStatToWeb(path);
        }

    }
}
