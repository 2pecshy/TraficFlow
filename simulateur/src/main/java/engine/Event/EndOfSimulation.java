package engine.Event;

import engine.Contexts.Context;

public class EndOfSimulation implements Events{

    private Context context;
    private boolean started;

    public EndOfSimulation(Context context_){
        context = context_;
        started = false;
    }

    @Override
    public void onStart() {
        started = true;
    }

    @Override
    public void onTick() {

        if(started) {
            if (context.getTick() > 1000) {
                System.out.println("Event: simulation finish");
                context.setFinish();
            }
        }
    }

    @Override
    public boolean isComplet() {
        return context.getTick() > 100;
    }

    @Override
    public String toString(){
        if(context.getTick() > 100){
            return "Event: simulation finish";
        }
        else
            return  "Event: simulation not finish";
    }
}
