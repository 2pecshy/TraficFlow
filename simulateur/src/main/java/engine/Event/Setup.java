package engine.Event;

import engine.Agent.Cars;
import engine.Contexts.TraficFlowContext;
import org.jgrapht.GraphPath;
import utils.Map.Cost.GPS_node;
import utils.Map.Cost.Route;

import java.util.ArrayList;
import java.util.Random;

public class Setup implements Events {

    private TraficFlowContext context;
    private boolean started;
    private int nb_agent;

    public Setup(TraficFlowContext context_){
        context = context_;
        started = false;
        nb_agent = 530;
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
        Random rand = new Random();
        GPS_node src = null,sink;
        GraphPath<GPS_node, Route> path = null;
        if (context.getTick() == 1) {
            for (i = 0; i < nb_agent; i++) {
                while (path == null){
                    ArrayList<GPS_node> src_s = context.getMap().getSources();
                    ArrayList<GPS_node> sink_s = context.getMap().getSinks();
                    src = src_s.get(rand.nextInt(src_s.size()));
                    sink = sink_s.get(rand.nextInt(sink_s.size()));
                    path = context.getMap().getBestPath(src, sink);
//                    System.out.println("src_size: " + src_s.size());
                }
                context.addAgent(new Cars(src, path));
                path = null;
            }
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
