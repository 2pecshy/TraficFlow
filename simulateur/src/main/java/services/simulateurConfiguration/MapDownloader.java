package services.simulateurConfiguration;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sample.SimulationWebConfiguration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;


/**
 * Created by Matthieu on 18/01/2018.
 */

public class MapDownloader {
        private File f = null;
        public String downloadFile(String url) throws  Exception{
                URL website = new URL(url);
                f = new File("map" + checkLastMap()+".osm");
                FileUtils.copyURLToFile(website, f);
                return f.getName();
        }

        private String checkLastMap(){
                int i = 1;
                File f = new File("map" + String.valueOf(i) + ".osm");
                while(f.exists()){
                        i++;
                        f = new File("map" + String.valueOf(i) + ".osm");
                }
                return String.valueOf(i);
        }

        private void deleteFile(String name){
                File f = new File(name);
                if(f.exists()){
                        f.delete();
                }
        }
}
