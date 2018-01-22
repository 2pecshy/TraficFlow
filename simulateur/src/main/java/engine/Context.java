package engine;

import utils.Map.Cost.Patch;
import utils.Map.Cost.Route;

import java.util.Set;

public interface Context {

    public void onDraw();
    public void onTick();

    public Set<Agents> getAgents();
    public Set<Patch> getPatchs();
    public Set<Events> getEvents();

    public void addAgent(Agents agent);
    public void addPatch(Patch patch);
    public void setPatchs(Set<Patch> setPatch);
    public void addEvent(Events event);
}
