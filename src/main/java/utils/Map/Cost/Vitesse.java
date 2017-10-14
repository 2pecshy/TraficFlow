package utils.Map.Cost;

public class Vitesse extends Cost {

    //vitesse en KM/H
    Vitesse(Integer vitesse){

        super(vitesse);
    }

    public void setCost(Integer v1,Integer v2,Integer cost){

        super.setCost(v1,v2,cost);
    }

    public Integer getCost(Integer v1,Integer v2, Distance d) {

        return super.getCost(v1,v2);
    }
}
