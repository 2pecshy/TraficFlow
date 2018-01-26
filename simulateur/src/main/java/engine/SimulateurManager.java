package engine;

import utils.Map.Cost.EnumCriter;

import java.util.ArrayList;

public class SimulateurManager {

    private static SimulateurManager instance = null;
    private ArrayList<Model> models;
    private EnumCriter criter = EnumCriter.ALL;

    public static SimulateurManager getInstance(){

        if(instance == null)
            throw new NullPointerException();
        return instance;
    }

    private SimulateurManager() {
        models = new ArrayList<Model>();
    }

    public static boolean INIT_Simulateur(){
        if(instance == null) {
            System.out.println("INIT: engine.SimulateurManager");
            instance = new SimulateurManager();
            return true;
        }
        System.out.println("engine.SimulateurManager Already Running");
        return false;
    }

    public static boolean KILL_Simulateur(){
        if(instance != null) {
            System.out.println("Killing: engine.SimulateurManager");
            instance.models = null;
            instance = null;
            return true;
        }
        System.out.println("engine.SimulateurManager Already dead");
        return false;
    }

    public int addAndRunSimulation(Model model){

        int pid;
        models.add(model);
        pid = models.indexOf(model);
        models.get(pid).startSimulation();
        return pid;
    }

    public void pauseSimulation(int pid){
        models.get(pid).pauseSimulation();
    }

    public void resumeSimulation(int pid){
        models.get(pid).resumeSimulation();
    }

    /**
     *
     * @return renvoie le critère pris en compte actuelement par le simulateur
     */
    public EnumCriter getCriter() {
        return criter;
    }

    /**
     *
     * @param criter est le nouveau critère qui doit être pris en compte par le simulateur
     */
    public void setCriter(EnumCriter criter) {
        this.criter = criter;
    }
}
