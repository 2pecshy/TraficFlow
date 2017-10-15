package utils.Map.Cost;

//
public class Voie extends Cost {

    //nombre de voie sur une route
    public Voie(Integer nb_voie){

        super(nb_voie);
    }

    //distance en Mètre
    public void setCost(Integer v1,Integer v2,Integer cost){

        super.setCost(v1,v2,cost);
    }

    public GraphWeight getCost(Integer v1,Integer v2) {

        return super.getCost(v1,v2);
    }

}
