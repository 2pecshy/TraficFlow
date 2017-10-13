package utils.Map.Cost;

public class Vitesse extends Cost {

    Vitesse(double v){
        super(v);
    }

    public double getCost(Distance d) {

        return d.getCost()/super.getCost();
    }
}
