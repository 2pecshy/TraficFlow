package utils.Stat;

import utils.Map.Cost.Route;

import java.util.ArrayList;

/**
 * Created by Matthieu on 14/10/2017.
 */
public class Stat{

    String startPoint;
    String endPoint;
    double maxVoiture;
    ArrayList<Route> route_saturee;
    double maxvoies;

    public Stat(){};

    public Stat(String startPoint, String endPoint, double maxVoiture, ArrayList<Route> route_saturee, double maxvoies) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.maxVoiture = maxVoiture;
        this.route_saturee = route_saturee;
        this.maxvoies = maxvoies;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public double getMaxVoiture() {
        return maxVoiture;
    }

    public void setMaxVoiture(double maxVoiture) {
        this.maxVoiture = maxVoiture;
    }

    public ArrayList<Route> getRoute_saturee() {
        return route_saturee;
    }

    public void setRoute_saturee(ArrayList<Route> route_saturee) {
        this.route_saturee = route_saturee;
    }

    public double getMaxvoies() {
        return maxvoies;
    }

    public void setMaxvoies(double maxvoies) {
        this.maxvoies = maxvoies;
    }

    public void printStats() {
        System.out.println("(départ: carrefours 3, arrivée: carrefours 1)flow max avant saturation des routes: " + maxVoiture/60 + " voitures par minute" );
        System.out.println("route saturées: " + route_saturee.toString());
        System.out.println("route(0->1): amélioration max,  " + maxvoies/60  + " voitures par minute");

    }

    public String getAll() {
        String s ="";
        s+= "(départ: carrefours 3, arrivée: carrefours 1)flow max avant saturation des routes: " + maxVoiture;
        s+= "\n";
        s+= "route saturées: " + route_saturee.toString();
        s+= "\n";
        s+= "route(0->1): amélioration max,  " + maxvoies  + " voies";

        return s;
    }
}
