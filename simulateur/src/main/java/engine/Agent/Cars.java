package engine.Agent;

import engine.Contexts.Context;
import org.jgrapht.GraphPath;
import utils.Map.Cost.GPS_node;
import utils.Map.Cost.Route;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cars implements Agents {

    public static final double TICK_ELAPSE_TIME = 1; // 1 tick/sec
    public static final double G = 9.80665; //M/s  gravity on earth
    public static final double DEFAULT_LENGTH = 3.4;
    public static final double DEFAULT_SPACE = 2.5;
    public static final double DEFAULT_MAX_ACCELERATION = 0.7; // in G

    private GraphPath<GPS_node, Route> path_road;
    private List<Route> currentPath;
    private GPS_node start;
    private GPS_node end;
    private double length;// in M
    private double curent_speed; // in Km/h
    private double curent_acceleration; //in g
    private double min_space_with_next; // in m
    private double max_acceleration;
    private Route curent_route;
    private int way_num;
    private GPS_node current_pos;
    private long nb_ticks;

    public Cars(GPS_node start_point, GraphPath<GPS_node, Route> path){
        length = DEFAULT_LENGTH;
        min_space_with_next = DEFAULT_SPACE;
        curent_speed = 0;
        curent_acceleration = 0;
        max_acceleration = DEFAULT_MAX_ACCELERATION;
        way_num = 0;
        current_pos = new GPS_node(0,0,0);
        nb_ticks = 0;
        path_road = path;
        start = path.getStartVertex();
        end = path.getEndVertex();
        currentPath = path_road.getEdgeList();
        curent_route = null;//path.getGraph().edgesOf(start).iterator().next();
    }

    public Cars(){
        length = DEFAULT_LENGTH;
        min_space_with_next = DEFAULT_SPACE;
        curent_speed = 0;
        curent_acceleration = 0.1;
        max_acceleration = DEFAULT_MAX_ACCELERATION;
        way_num = 0;
        current_pos = new GPS_node(0,0,0);
        nb_ticks = 0;
    }


    @Override
    public void onTick() {

        /*update_acceleration_and_speed();
        update_distance();*/
        nb_ticks++;
        update_distance();
        /*System.out.println("Voiture id: " + this + " en vie depuis " + nb_ticks
                + " ticks. taille chemain: " + path_road.getEdgeList().size());*/

    }

    private void update_acceleration_and_speed(){
        Cars carInFRont = getCarsInFrontOfMe();
        double dist = GPS_node.distFromGpsPos(this.getCurrent_pos(),carInFRont.getCurrent_pos());
        //double dist_traveled;
        double new_speed = 0;
        if(dist < min_space_with_next)
            decelerate(dist);

        else if(curent_speed != curent_route.getVitesse_max()) {

            if(new_speed > curent_route.getVitesse_max()){
                curent_speed = curent_route.getVitesse_max();
                curent_acceleration = 0;
            }

            else if (new_speed < curent_route.getVitesse_max()) {
                curent_speed = new_speed;
            }

        }
    }

    private void update_distance(){
        //TODO
        //path.getGraph().edgesOf(start).iterator().next();
        Route nextRoute;
        Iterator<Route> iter_route;
        if(curent_route == null) {
            nextRoute = currentPath.get(0);
            if(nextRoute.addAgents(this)) {
                curent_route = nextRoute;
                currentPath.remove(0);
            }
            return;
        }

        //iter_route = path_road.getGraph().edgesOf(curent_route.getV2()).iterator();
        //nextRoute = iter_route.next();
        nextRoute = currentPath.get(0);
        //System.out.println("forward" + nextRoute.getAgents().size());
        if(nextRoute.addAgents(this)) {
            curent_route.removeAgents(this);
            curent_route = nextRoute;
            currentPath.remove(0);
        }

    }

    @Override
    public void onDraw() {

    }

    @Override
    public boolean isDead() {
        if(curent_route == null) return false;
        if(curent_route.getV2() == end) {
            curent_route.removeAgents(this);
            System.out.println(this + " finish!");
            return true;
        }
        return false;
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public long getDureeVie() {
        return nb_ticks;
    }

    private void decelerate(double dist){
        //TODO
    }

    private double compute_curent_speed(double elapseTime){

        return 0.2;
    }

    public GraphPath<GPS_node, Route> getPath_road() {
        return path_road;
    }

    public double getLength() {
        return length;
    }

    public double getCurent_speed() {
        return curent_speed;
    }

    public double getCurent_acceleration() {
        return curent_acceleration;
    }

    public double getMin_space_with_next() {
        return min_space_with_next;
    }

    public double getMax_acceleration() {
        return max_acceleration;
    }

    public Route getCurent_route() {
        return curent_route;
    }

    public int getWay_num() {
        return way_num;
    }

    public GPS_node getCurrent_pos() {
        return current_pos;
    }

    private Cars getCarsInFrontOfMe(){
        //TODO
        return null;
    }
}
