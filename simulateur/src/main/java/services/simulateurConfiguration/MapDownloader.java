package services.simulateurConfiguration;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;



/**
 * Created by Matthieu on 18/01/2018.
 */

@RestController
public class MapDownloader {
        @ResponseBody @RequestMapping("/download")
        public String uploadFile(@RequestBody String s) throws IOException {
                JSONObject req = new JSONObject(s);
                URL url = new URL(req.getString("url"));
                String[] array = s.split("/");
                String path = "./map.t";
                File file = new File(path);
                file.deleteOnExit();
                FileUtils.copyURLToFile(url, file);
                if(file.exists()){
                        return "le fichier a ete telecharge !";
                }
                else{
                        return "rate";
                }
        }
}
