package utils.Map.Cost;

public class Route {

    private Integer v1,v2;
    private Integer nombre_de_voie;

    // private Integer vitesse_max;    // Km/h
   // private Integer distance;  // Metre

    /**
     *
     * @param v1_ est le carrefour de départ de la route
     * @param v2_ est le carrefour d'arrivé de la route
     * @param nombre_de_voie_ est le nombre de voie sur cette route
     */
    public Route(Integer v1_, Integer v2_, Integer nombre_de_voie_){

        if(nombre_de_voie_ < 0) throw new ExceptionInInitializerError("nombre de voie < 0");
        if(v1_ == v2_) throw new ExceptionInInitializerError("v1 = v2");
        nombre_de_voie = nombre_de_voie_;
        v1 = v1_;
        v2 = v2_;
    }

    //constructeur par copy

    /**
     * Constructeur par copy
     * @param route est la route à copier
     */
    public Route(Route route){
        this.nombre_de_voie = route.nombre_de_voie;
        this.v1 = route.v1;
        this.v2 = route.v2;
    }

    /**
     *
     * @return renvoie le nombre de voie de cette route
     */
    public Integer getNombre_de_voie() {
        return nombre_de_voie;
    }

    /**
     *
     * @param nombre_de_voie nombre de voie à setter sur la route
     * @return vrais si le nombre de voie est >= 0
     */
    public boolean setNombre_de_voie(Integer nombre_de_voie) {
        if(nombre_de_voie >= 0) {
            this.nombre_de_voie = nombre_de_voie;
            return true;
        }
        return false;
    }

    /**
     *
     * @return renvoie le carrefour de départ de la route
     */
    public Integer getV1() {
        return v1;
    }

    /**
     *
     * @return renvie le carrefour d'arrivé de la route
     */
    public Integer getV2() {
        return v2;
    }

    /**
     *
     * @param c le critère à prendre en conte pour le calcule du cout sur cette route
     * @return renvoie le cout de la route
     */
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
