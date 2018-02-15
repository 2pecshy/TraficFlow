package engine.Contexts;

import engine.Agent.Agents;
import engine.Event.Events;
import engine.Patch;
import sample.SimulatorData;
import services.simulateurConfiguration.SimulateurObserver;
import utils.Map.Cost.Route;
import utils.Map.Map;

import java.util.ArrayList;
import java.util.Iterator;

public class TraficFlowContext implements Context{

    private Map map;
    private ArrayList<Patch> patchs;
    private ArrayList<Events> events;
    private ArrayList<Agents> agents;
    private boolean finish;
    private long tick;

    private SimulateurObserver observer;

    public TraficFlowContext(Map map_){
        map = map_;
        patchs = new ArrayList<Patch>();
        events = new ArrayList<Events>();
        agents = new ArrayList<Agents>();
        finish = false;
        tick = 0;
        observer = new SimulateurObserver();

        Iterator<Route> iter_route = map.getRoutes().iterator();
        Route curent;
        while (iter_route.hasNext()){
            curent = iter_route.next();
            patchs.add(curent);
        }
    }
//    trafficflow model verifie obser.setMap

    @Override
    public void onDraw() {
        System.out.println("update ui!!");
        Iterator<Agents> iterAgents = agents.iterator();

        while (iterAgents.hasNext()){
            iterAgents.next().onDraw();
        }
    }

    public SimulateurObserver getObserver() {
        return observer;
    }

    @Override
    public void onTick() {
        observer.setStep(observer.getStep()+1);
        System.out.println("Tick " + tick);
        tick++;
        updateEvents();
        if(!finish) {
            updatePatch();
            updateAgent();
        }

    }

    private void updateAgent(){
        Iterator<Agents> iterAgents = agents.iterator();
        while (iterAgents.hasNext()){
            iterAgents.next().onTick();
        }
    }

    private void updatePatch(){
        Iterator<Patch> iterPatch = patchs.iterator();
        Patch current;
        while (iterPatch.hasNext()){
            current = iterPatch.next();
            if(current.onTick() > 0){
                if(current.getAgents().size() > 7) {
                    SimulatorData data = new SimulatorData(current.getId(), current.getAgents().size(), this.getTick(), -1);
                    observer.setData(data);
                }
            }
        }
    }


    private void updateEvents(){
        int i,nb_event;
        Events curentEvent;
        Iterator<Events> iterEvents = events.iterator();
        while (iterEvents.hasNext()){
            iterEvents.next().onTick();
        }

        nb_event = events.size();

        for(i = 0; i < nb_event; i++) {

            curentEvent = events.get(i);
            if(curentEvent.isComplet()) {
                events.remove(i);
                i--;
                nb_event--;
            }

        }
    }

    @Override
    public ArrayList<Agents> getAgents() {
        return agents;
    }

    @Override
    public ArrayList<Patch> getPatchs() {
        return patchs;
    }

    @Override
    public ArrayList<Events> getEvents() {
        return events;
    }

    @Override
    public boolean isFinish() {
        return finish;
    }

    @Override
    public void setFinish() {
        finish = true;
    }

    @Override
    public void addAgent(Agents agent) {
        agents.add(agent);
    }

    @Override
    public void addPatch(Patch patch) {
        patchs.add(patch);
    }

    @Override
    public void setPatchs(ArrayList<Patch> setPatch) {
        patchs = setPatch;
    }

    @Override
    public void addEvent(Events event) {
        events.add(event);
    }

    @Override
    public long getTick() {
        return tick;
    }

    public Map getMap() {
        return map;
    }
}
