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

    public TraficFlowContext(){
        patchs = new ArrayList<Patch>();
        events = new ArrayList<Events>();
        agents = new ArrayList<Agents>();
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

        System.out.println("tick tac");

        Iterator<Agents> iterAgents = agents.iterator();
        Iterator<Patch> iterPatch = patchs.iterator();
        Iterator<Events> iterEvents = events.iterator();
        Agents curentAgent;
        Events curentEvent;
        int i, size_list;

        while (iterAgents.hasNext()){
            iterAgents.next().onTick();
        }

        while (iterPatch.hasNext()){
            iterPatch.next().onTick();
        }

        while (iterEvents.hasNext()){
            iterEvents.next().onTick();
        }

        size_list = agents.size();
        for(i = 0; i < size_list; i++){

            curentAgent = agents.get(i);
            if(curentAgent.isDead()) {
                agents.remove(curentAgent);
                i--;
                agents.add(new Cars());
            }

        }
        System.out.println("number of agents: " + agents.size());

        iterEvents = events.iterator();

        while (iterEvents.hasNext()){
            curentEvent = iterEvents.next();
            if(curentEvent.isComplet())
                events.remove(events);
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
}
