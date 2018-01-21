package engine;

import java.util.Set;

public interface Context {

    public void onDraw();
    public void onTick();
    public long getTick();

    public Set<Agents> getAgents();
    public Set<Patch> getPatchs();

    public void addEvent(Events event);
}
