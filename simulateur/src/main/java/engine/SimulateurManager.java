package engine;

import sample.SimulationWebConfiguration;
import services.simulateurConfiguration.MapDownloader;
import utils.Map.Cost.EnumCriter;
import utils.Map.Map;
import utils.Map.Osm.osmLoader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

public class SimulateurManager {

    private static SimulateurManager instance = null;
    private ArrayList<Model> models;
    private EnumCriter criter = EnumCriter.DISTANCE;
    private int simu_pid;
    private MapDownloader downloader = new MapDownloader();

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

    public int addSimuFromConfig(int pid_, SimulationWebConfiguration conf){

        Map map;
        TraficFlowModel model;
        String mapName = "";

        try {
            mapName = downloader.downloadFile(conf.getMapLink());
            map = osmLoader.load(mapName);
            model = new TraficFlowModel(map,conf);
            model.setPid(pid_);
            models.add(model);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return pid_;
    }

    public int addAndRunFromConfig(int pid_, SimulationWebConfiguration conf){
        if(addSimuFromConfig(pid_,conf) != -1){
            startSimulation(pid_);
            return pid_;
        }
        return -1;
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

    public void startSimulation(int pid){

        Iterator<Model> iter_mod = models.iterator();
        Model curent_mod;
        while (iter_mod.hasNext()){
            curent_mod = iter_mod.next();
            if(curent_mod.getPid() == pid){
                curent_mod.startSimulation();
            }
        }
    }

    public int addAndRunSimulation(Model model){

        int pid_ = simu_pid++;
        model.setPid(pid_);
        models.add(model);
        model.startSimulation();
        return pid_;
    }

    public ArrayList<Integer> listRuningSimulation(){
        ArrayList<Integer> res = new ArrayList<Integer>();
        Iterator<Model> iter = models.iterator();
        Model curent;
        while (iter.hasNext()){
            curent = iter.next();
            if(curent.isRunning() == TraficFlowModel.RUNNING)
                res.add(curent.getPid());
        }
        return res;
    }

    public ArrayList<Integer> listPausedSimulation(){
        ArrayList<Integer> res = new ArrayList<Integer>();
        Iterator<Model> iter = models.iterator();
        Model curent;
        while (iter.hasNext()){
            curent = iter.next();
            if(curent.isRunning() == TraficFlowModel.PAUSE)
                res.add(curent.getPid());
        }
        return res;
    }

    public ArrayList<Integer> listFinishSimulation(){
        ArrayList<Integer> res = new ArrayList<Integer>();
        Iterator<Model> iter = models.iterator();
        Model curent;
        while (iter.hasNext()){
            curent = iter.next();
            if(curent.isRunning() == TraficFlowModel.FINISH)
                res.add(curent.getPid());
        }
        return res;
    }

    public Model getModelOfPID(int pid_){
        Iterator<Model> iter_mod = models.iterator();
        Model curent_mod;
        while (iter_mod.hasNext()){
            curent_mod = iter_mod.next();
            if(curent_mod.getPid() == pid_){
                return curent_mod;
            }
        }
        return null;
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
