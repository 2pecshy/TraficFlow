package engine;

import engine.Agent.Agents;

import java.util.ArrayList;

public interface Patch {
    public String getId();
    public int onTick();
    public int getType();
    public ArrayList<Agents> getAgents();
    public boolean addAgents(Agents to_add);
    public boolean removeAgents(Agents to_remove);
}
