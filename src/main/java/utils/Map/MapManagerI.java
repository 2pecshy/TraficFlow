package utils.Map;

import utils.StorageManager.IDataManager;
import utils.Stat.Stat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class MapManagerI{

    public MapManagerI(){

    }

    private FileInputStream fis = null;
    private Scanner scanner;

    public Map loadMap(String mode, String path) {
        if(mode.equalsIgnoreCase("file")){
            return loadMapFromFile(new File(path));
        }
        return loadMapFromWeb(path);
    }

    private Map loadMapFromFile(File file) {
        Map map = new Map();
        try {
            scanner = new Scanner(new FileReader(file));
            String line = scanner.nextLine();
            for (int i = 0; i < Integer.valueOf(line); i++) {
                map.addCarrefours();
            }
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                System.out.println("Ligne lue : " + line + "\n");
                String values[] = line.split(",");

                int numericValues[] = new int[values.length];
                for (int i = 0; i < values.length; i++) {
                    numericValues[i] = Integer.parseInt(values[i]);
                }
                map.addRoute(numericValues[0], numericValues[1], numericValues[2]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
        return map;
    }

    private Map loadMapFromWeb(String path){
        Map loadedMap = new Map();
        return loadedMap;
    }

    private void saveMapToWeb(String path){

    }

    private void saveMapToFile(String path){

    }

    public void saveMap(String mode, String path) {
        if(mode == "web"){
            saveMapToWeb(path);
        }
        else if (mode == "file"){
            saveMapToFile(path);
        }
    }




}
