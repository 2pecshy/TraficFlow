package utils.Map.Cost;

import engine.Agent.Agents;
import engine.Patch;

import java.util.ArrayList;

public class Route implements Patch {

    public static final Integer DEFAULT_NB_VOIES = 1;
    public static final Double DEFAULT_DISTANCE = 1000.0;
    public static final Integer DEFAULT_VITESSE = 50;

    public static final Integer DEFAULT_DISTANCE_ENTRE_VOITURE = 5;
    public static final Integer DEFAULT_TAILLE_VOITURE = 3;

    private GPS_node v1,v2;
    private Integer nombre_de_voie;

    private Integer vitesse_max;    // Km/h
    private Double distance;  // Metre

    private ArrayList<Agents> agentsOnTheRoad;

    /**
     *
     * @param v1_ est le carrefour de départ de la route
     * @param v2_ est le carrefour d'arrivé de la route
     * @param nombre_de_voie_ est le nombre de voie sur cette route
     */
    public Route(GPS_node v1_, GPS_node v2_, Integer nombre_de_voie_){

        if(nombre_de_voie_ < 0) throw new ExceptionInInitializerError("nombre de voie < 0");
        if(v1_ == v2_) throw new ExceptionInInitializerError("v1 = v2");
        nombre_de_voie = nombre_de_voie_;
        vitesse_max = DEFAULT_VITESSE;
        v1 = v1_;
        v2 = v2_;
        distance = GPS_node.distFromGpsPos(v1,v2);
        agentsOnTheRoad = new ArrayList<Agents>();
    }

    public Route(GPS_node v1_, GPS_node v2_, Integer nombre_de_voie_, Double distance_, Integer vitesse_max_){

        if(nombre_de_voie_ < 0) throw new ExceptionInInitializerError("nombre de voie < 0");
        if(v1_ == v2_) throw new ExceptionInInitializerError("v1 = v2");
        nombre_de_voie = nombre_de_voie_;
        distance = distance_;
        vitesse_max = vitesse_max_;
        v1 = v1_;
        v2 = v2_;
        agentsOnTheRoad = new ArrayList<Agents>();
    }

    //constructeur par copy

    /**
     * Constructeur par copy
     * @param route est la route à copier
     */
    public Route(Route route){
        this.nombre_de_voie = route.nombre_de_voie;
        this.vitesse_max = route.vitesse_max;
        this.distance = route.distance;
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
     * @return vrais si le nombre de voie est plus grand 0
     */
    public boolean setNombre_de_voie(Integer nombre_de_voie) {
        if(nombre_de_voie >= 0) {
            this.nombre_de_voie = nombre_de_voie;
            return true;
        }
        return false;
    }

    public Integer getVitesse_max() {
        return vitesse_max;
    }

    /**
     *
     * @return renvoie le carrefour de départ de la route
     */
    public GPS_node getV1() {
        return v1;
    }

    /**
     *
     * @return renvie le carrefour d'arrivé de la route
     */
    public GPS_node getV2() {
        return v2;
    }

    /**
     *
     * @param c le critère à prendre en conte pour le calcule du cout sur cette route
     * @return renvoie le cout de la route
     */
    public double getCout(EnumCriter c){

        if(c == EnumCriter.VOIES)
            return nombre_de_voie;
        else if(c == EnumCriter.DISTANCE)
            return distance;
        else if(c == EnumCriter.ALL){
            return this.func_eval();
        }
        return 1;
    }

    public int getRoutes(){
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

    /**
     * source: https://en.wikipedia.org/wiki/Traffic_flow#Traffic_stream_properties
     * @return renvoie le nombre de voiture/h qui peut circuler sur cette route
     */
    private double func_eval(){

        double density = ((double)DEFAULT_DISTANCE_ENTRE_VOITURE + (double)DEFAULT_TAILLE_VOITURE) / (double)distance;
        double speed = vitesse_max*1000; //km/h to m/h
        double flow = (density * speed) * nombre_de_voie;

        System.out.println("DEBUG: flow = " + flow + " " + density + " " + speed);
        return flow;
    }


    @Override
    public int onTick() {
        //TODO
        return 0;
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public ArrayList<Agents> getAgents() {
        return agentsOnTheRoad;
    }

    @Override
    public boolean addAgents(Agents to_add) {
        if(agentsOnTheRoad.size() < 10) {
            agentsOnTheRoad.add(to_add);
            //System.out.println("agent add to route");
            return true;
        }
        //
        return false;
    }

    @Override
    public boolean removeAgents(Agents to_remove) {
        agentsOnTheRoad.remove(to_remove);
        //System.out.println("agent remove from route");
        return true;
    }
}
