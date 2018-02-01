package engine.Event;

import engine.Agent.Cars;
import engine.Contexts.Context;
import engine.Contexts.TraficFlowContext;
import org.jgrapht.GraphPath;
import utils.Map.Cost.GPS_node;
import utils.Map.Cost.Route;

import java.util.ArrayList;

public class Setup implements Events {

    private TraficFlowContext context;
    private boolean started;
    private int nb_agent;

    public Setup(TraficFlowContext context_){
        context = context_;
        started = false;
        nb_agent = 1;
    }

    public Setup(TraficFlowContext context_,int nb_agent_){
        context = context_;
        started = false;
        nb_agent = nb_agent_;
    }

    @Override
    public void onStart() {
        started = true;
    }

    @Override
    public void onTick() {

        int i;
        ArrayList<GPS_node> src_s = context.getMap().getSources();
        ArrayList<GPS_node> sink_s = context.getMap().getSinks();
        GPS_node src = src_s.get(0);
        GPS_node sink = sink_s.get(0);
        GraphPath<GPS_node, Route> path = context.getMap().getBestPath(src, sink);
        if (context.getTick() == 1) {
            for (i = 0; i < nb_agent; i++)
                context.addAgent(new Cars(src, path));
        }
    }

    @Override
    public boolean isComplet() {
        return true;
    }

    @Override
    public String toString(){
        return "simulation started";
    }
}
