package engine;

import java.util.ArrayList;
import java.util.Iterator;

public class TraficFlowContext implements Context{

    private ArrayList<Patch> patchs;
    private ArrayList<Events> events;
    private ArrayList<Agents> agents;

    TraficFlowContext(){
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

        while (iterAgents.hasNext()){
            iterAgents.next().onTick();
        }

        while (iterPatch.hasNext()){
            iterPatch.next().onTick();
        }

        while (iterEvents.hasNext()){
            iterEvents.next().onTick();
        }

        iterAgents = agents.iterator();
        iterEvents = events.iterator();

        while (iterAgents.hasNext()){
            curentAgent = iterAgents.next();
            if(curentAgent.isDead())
                agents.remove(curentAgent);
        }

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
