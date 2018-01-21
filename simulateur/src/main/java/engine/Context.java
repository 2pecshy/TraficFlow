package engine;

import java.util.Set;

public interface Context {

    /*private Set<Agents> agents;
    private Set<Patch> patchs;
    private long tick;*/

    public void onDraw();
    public void onTick();
    public long getTick();

    public Set<Agents> getAgents();
    public Set<Patch> getPatchs();
}
