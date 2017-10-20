package utils.Map.Cost;

public class Route {

    private Integer v1,v2;
    private Integer nombre_de_voie;

    // private Integer vitesse_max;    // Km/h
   // private Integer distance;  // Metre

    public Route(Integer v1_, Integer v2_, Integer nombre_de_voie_){

        nombre_de_voie = nombre_de_voie_;
        v1 = v1_;
        v2 = v2_;
    }

    public Integer getNombre_de_voie() {
        return nombre_de_voie;
    }

    public void setNombre_de_voie(Integer nombre_de_voie) {
        this.nombre_de_voie = nombre_de_voie;
    }

    public Integer getV1() {
        return v1;
    }

    public Integer getV2() {
        return v2;
    }

    public double getCout(EnumCriter c){
        return nombre_de_voie;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("(");
        res.append(v1);
        res.append("->");
        res.append(v2);
        res.append(")");
        return res.toString();
    }
}
