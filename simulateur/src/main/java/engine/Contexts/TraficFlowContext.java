package engine.Contexts;

import engine.Agent.Agents;
import engine.Agent.Cars;
import engine.Event.Events;
import engine.Patch;

import java.util.ArrayList;
import java.util.Iterator;

public class TraficFlowContext implements Context{

    private ArrayList<Patch> patchs;
    private ArrayList<Events> events;
    private ArrayList<Agents> agents;
    private boolean finish;
    private long tick;

    public TraficFlowContext(){
        patchs = new ArrayList<Patch>();
        events = new ArrayList<Events>();
        agents = new ArrayList<Agents>();
        finish = false;
        tick = 0;
    }

    @Override
    public void onDraw() {
        System.out.println("update ui!!");
        Iterator<Agents> iterAgents = agents.iterator();

        while (iterAgents.hasNext()){
            iterAgents.next().onDraw();
        }
    }

    @Override
    public void onTick() {

        System.out.println("Tick");
        tick++;
        updateEvents();
        updatePatch();
        updateAgent();
        System.out.println("number of agents: " + agents.size());

    }

    private void updateAgent(){
        Iterator<Agents> iterAgents = agents.iterator();
        while (iterAgents.hasNext()){
            iterAgents.next().onTick();
        }
    }

    private void updatePatch(){
        Iterator<Patch> iterPatch = patchs.iterator();
        while (iterPatch.hasNext()){
            iterPatch.next().onTick();
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
}
