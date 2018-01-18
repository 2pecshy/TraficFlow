package service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Jeremy on 18/01/2018.
 */

@RestController
public class ObserveurControlleur {

    private static AtomicLong compteur = new AtomicLong();

    public void setCompteur(AtomicLong compteur) {
        this.compteur = compteur;
    }

    @RequestMapping("/observeur")
    public AtomicLong atom(@RequestParam(value="hey") int n){
        compteur.set(n);
        return new AtomicLong(compteur.getAndIncrement());
    }
}
