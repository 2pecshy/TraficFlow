package database;

import java.io.*;

/**
 * Created by Matthieu on 15/02/2018.
 */
public class SauvegardeSimulations {

    public void saveLastSim(int id) throws IOException {
        File f = new File("historique.txt");
        if(!f.exists()){
            f.createNewFile();
        }
        FileWriter fileWriter = new FileWriter("historique.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(id + "\n");
        printWriter.close();
    }

    public int getLastSim() throws IOException {
        File f = new File("historique.txt");
        if(!f.exists()){
            f.createNewFile();
        }
        BufferedReader reader = new BufferedReader(new FileReader("historique.txt"));
        String s = reader.readLine();
        reader.close();
        return Integer.valueOf(s);
    }
}
