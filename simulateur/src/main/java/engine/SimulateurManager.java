package engine;

import utils.Map.Cost.EnumCriter;

import java.util.ArrayList;
import java.util.Iterator;

public class SimulateurManager {

    private static SimulateurManager instance = null;
    private ArrayList<Model> models;
    private EnumCriter criter = EnumCriter.ALL;
    private int simu_pid;

    public static SimulateurManager getInstance(){

        if(instance == null)
            throw new NullPointerException();
        return instance;
    }

    private SimulateurManager() {
        simu_pid = 0;
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

        int pid_ = simu_pid++;
        model.setPid(pid_);
        models.add(model);
        model.startSimulation();
        return pid_;
    }

    public void pauseSimulation(int pid){
        Iterator<Model> iter_mod = models.iterator();
        Model curent_mod;
        while (iter_mod.hasNext()){
            curent_mod = iter_mod.next();
            if(curent_mod.getPid() == pid){
                curent_mod.pauseSimulation();
            }
        }
    }

    public void resumeSimulation(int pid){

        Iterator<Model> iter_mod = models.iterator();
        Model curent_mod;
        while (iter_mod.hasNext()){
            curent_mod = iter_mod.next();
            if(curent_mod.getPid() == pid){
                curent_mod.resumeSimulation();
            }
        }
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
