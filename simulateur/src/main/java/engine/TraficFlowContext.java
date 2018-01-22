package engine;

import utils.Map.Cost.Patch;
import utils.Map.Cost.Route;
import utils.Map.Map;
import utils.Map.Ui_graph;

import java.util.Iterator;
import java.util.Set;

public class TraficFlowContext implements Context{

    private Set<Patch> patchs;
    private Set<Events> events;
    private Set<Agents> agents;

    TraficFlowContext(){
    }

    @Override
    public void onDraw() {
        System.out.println("wow tros beau!! <3");
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
    public Set<Agents> getAgents() {
        return agents;
    }

    @Override
    public Set<Patch> getPatchs() {
        return patchs;
    }

    @Override
    public Set<Events> getEvents() {
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
    public void setPatchs(Set<Patch> setPatch) {
        patchs = setPatch;
    }

    @Override
    public void addEvent(Events event) {
        events.add(event);
    }
}
