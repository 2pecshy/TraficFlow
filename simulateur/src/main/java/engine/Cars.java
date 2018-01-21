package engine;

import utils.Map.Cost.GPS_node;

import java.util.ArrayList;

public class Cars implements Agents {

    //private
    private ArrayList<GPS_node> path_road;
    private float length;// in M
    private float curent_speed; // in Km/h
    private float acceleration; //in

    Cars(){


    }

    @Override
    public void onTick() {

    }

    @Override
    public void onDraw() {

    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public Context getContext() {
        return null;
    }
}
