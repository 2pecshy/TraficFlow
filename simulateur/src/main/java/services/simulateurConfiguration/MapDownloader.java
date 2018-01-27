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
        public void downloadFile(String url) throws  Exception{
                URL website = new URL(url);
                String array[] = website.getPath().split("/");
                f = new File(array[array.length-1]);
                FileUtils.copyURLToFile(website, f);
        }
}
