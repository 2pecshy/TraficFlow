package engine.Event;

import engine.Agent.Cars;
import engine.Contexts.Context;

public class Setup implements Events {

    private Context context;
    private boolean started;

    public Setup(Context context_){
        context = context_;
        started = false;
    }

    @Override
    public void onStart() {
        started = true;
    }

    @Override
    public void onTick() {

        if (context.getTick() == 1)
            context.addAgent(new Cars());
    }

    @Override
    public boolean isComplet() {
        return true;
    }

    @Override
    public String toString(){
        return "simulation startde";
    }
}
