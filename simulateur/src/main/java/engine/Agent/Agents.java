package engine.Agent;

import engine.Contexts.Context;

public interface Agents {

    public void onTick();
    public void onDraw();
    public boolean isDead();
    public Context getContext();
    public long getDureeVie();
}
