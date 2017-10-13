package utils.Map.Cost;

public class Distance extends Cost {

    //distance en Mètre
    Distance(double distance){

        super(distance);
    }

    public double getCost(Vitesse v) {

        return super.getCost()/v.getCost();
    }
}
