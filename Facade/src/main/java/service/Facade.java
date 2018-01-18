package service;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Jeremy on 18/01/2018.
 */
public class Facade {

    private final String hello;

    public Facade(String s){
        this.hello = s;
    }

    public String getHello() {
        return hello;
    }
}
