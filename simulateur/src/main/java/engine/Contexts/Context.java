package engine.Contexts;

import engine.Agent.Agents;
import engine.Event.Events;
import engine.Patch;

import java.util.ArrayList;

public interface Context {

    public void onDraw();
    public void onTick();

    public ArrayList<Agents> getAgents();
    public ArrayList<Patch> getPatchs();
    public ArrayList<Events> getEvents();

    public boolean isFinish();
    public void setFinish();
    public void addAgent(Agents agent);
    public void addPatch(Patch patch);
    public void setPatchs(ArrayList<Patch> setPatch);
    public void addEvent(Events event);

    long getTick();
}
