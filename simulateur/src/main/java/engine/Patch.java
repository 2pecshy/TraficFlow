package engine;

import engine.Context;

public interface Patch {

    public int onTick();
    public int getType();
}
