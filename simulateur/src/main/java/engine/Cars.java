package engine;

import utils.Map.Cost.GPS_node;
import utils.Map.Cost.Route;

import java.util.ArrayList;

public class Cars implements Agents {

    public static final double G = 9.80665; //M/s  constante terestre
    public static final double DEFAULT_LENGTH = 3.4;
    public static final double DEFAULT_SPACE = 2.5;
    public static final double DEFAULT_MAX_ACCELERATION = 0.7; // in G

    private ArrayList<GPS_node> path_road;
    private double length;// in M
    private double curent_speed; // in Km/h
    private double curent_acceleration; //in g
    private double min_space_with_next; // in m
    private double max_acceleration;
    private Route curent_route;
    private int way_num;

    Cars(GPS_node start_point,ArrayList<GPS_node> path){
        length = DEFAULT_LENGTH;
        min_space_with_next = DEFAULT_SPACE;
        curent_speed = 0;
        curent_acceleration = 0;
        max_acceleration = DEFAULT_MAX_ACCELERATION;
        way_num = 0;
    }

    Cars(){
        length = DEFAULT_LENGTH;
        min_space_with_next = DEFAULT_SPACE;
        curent_speed = 0;
        curent_acceleration = 0.1;
        max_acceleration = DEFAULT_MAX_ACCELERATION;
        way_num = 0;
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

    private double compute_curent_speed(double elapseTime){

        return 0.2;
    }
}
