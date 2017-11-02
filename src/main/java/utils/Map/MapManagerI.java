package utils.Map;

import utils.Map.Cost.EnumCriter;
import utils.Map.Cost.Route;
import utils.StorageManager.IDataManager;
import utils.Stat.Stat;

import java.io.*;
import java.util.Iterator;
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

    private void saveMapToFile(String path, Map map) {
        try {
            FileOutputStream out = new FileOutputStream("carte1edit.txt");
            int n = map.getCarrefours().size();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
            bw.write(String.valueOf(n));
            bw.newLine();
            for (int i = 0; i < map.getCarrefours().size(); i++) {
                Iterator<Route> iter_tmp = map.getRouteFromCarrefour(i).iterator();
                System.out.print("carrefour " + i + " [");
                while (iter_tmp.hasNext()) {
                    Route item = iter_tmp.next();
                    int v1 = item.getV1();
                    int v2 = item.getV2();
                    int cout = item.getRoutes();
                    String line = v1 + "," + v2 + "," + cout;
                    bw.write(line);
                    bw.newLine();
                }
            }
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveMap(String mode, String path, Map map) {
        if(mode == "web"){
            saveMapToWeb(path);
        }
        else if (mode == "file"){
            saveMapToFile(path, map);
        }
    }




}
