package services.simulateurConfiguration;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;



/**
 * Created by Matthieu on 18/01/2018.
 */

@RestController
public class MapDownloader {
        @ResponseBody @RequestMapping("/download")
        public String uploadFile(@RequestBody File f) throws IOException {
                File file = new File("map.t");
                FileCopyUtils.copy(f, file);
                if(file.exists()){
                        return "le fichier a ete telecharge !";
                }
                else{
                        return "rate";
                }
        }
}
