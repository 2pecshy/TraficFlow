package engine.Event;

import engine.Contexts.Context;

public class EndOfSimulation implements Events{

    private Context context;
    private boolean started;
    public static final int nb_ticks_to_end = 10000;

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
            if (context.getTick() > nb_ticks_to_end) {
                System.out.println("Event: simulation finish");
                context.setFinish();
            }
        }
    }

    @Override
    public boolean isComplet() {
        if(started)
            return context.getTick() > nb_ticks_to_end;
        return false;
    }

    @Override
    public String toString(){
        if(context.getTick() > nb_ticks_to_end){
            return "Event: simulation finish";
        }
        else
            return  "Event: simulation not finish";
    }
}