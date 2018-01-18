package service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Jeremy on 18/01/2018.
 */

@org.springframework.web.bind.annotation.RestController
public class FacadeController {

    private static final String template = "Hello, %s !";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/facade")
    public Facade facade(@RequestParam(value="name",defaultValue = "World") String name){
        return new Facade(String.format(template,name));
    }
}
