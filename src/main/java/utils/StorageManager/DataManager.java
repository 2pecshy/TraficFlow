package utils.StorageManager;

import utils.Map.Map;
import utils.Stat.Stat;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Jeremy on 21/10/2017.
 */
public class DataManager implements IDataManager {

    private FileInputStream fis = null;
    private Scanner scanner;

    public Map loadMap(String mode, String path) {
        return null;
    }

    public Map loadMap(String mode, File file) {
        if(mode.equals("file")){
            Map map = new Map();

            try {
                scanner = new Scanner(new FileReader(file));
                String line = scanner.nextLine();
                for(int i = 0; i < Integer.valueOf(line); i++) {
                    map.addCarrefours();
                }
                while( scanner.hasNextLine()){
                    line = scanner.nextLine();
                    System.out.println("Ligne lue : " + line + "\n");
                    String values[] = line.split(",");

                    int numericValues[] = new int[values.length];
                    for(int i = 0; i < values.length;i++){
                        numericValues[i] = Integer.parseInt(values[i]);
                    }
                    map.addRoute(numericValues[0],numericValues[1],numericValues[2]);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }finally {
                scanner.close();
            }
            return map;

        }
        return null;
    }

    public void saveMap(String mode, String path) {

    }

    public Stat loadStat(String mode, String path) {
        return null;
    }

    public void saveStat(String mode, String path) {

    }

    public void saveStat(String mode, String path, String content) {
        if(mode.equals("file")){

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

    }
}
