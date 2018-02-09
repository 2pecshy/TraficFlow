package engine.Event;

import engine.Contexts.Context;
import engine.Contexts.TraficFlowContext;

public class EndOfSimulation implements Events{

    private Context context;
    private boolean started;
    private int nb_ticks_to_end;

    public EndOfSimulation(Context context_){
        context = context_;
        started = false;
        nb_ticks_to_end = 1000;
    }

    public EndOfSimulation(TraficFlowContext context_, int simulationLenght) {
        context = context_;
        started = false;
        nb_ticks_to_end = simulationLenght;
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

    public int getNb_ticks_to_end() {
        return nb_ticks_to_end;
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
