package engine.Event;

import engine.Agent.Agents;
import engine.Agent.Cars;
import engine.Contexts.Context;
import engine.Contexts.TraficFlowContext;
import org.jgrapht.GraphPath;
import utils.Map.Cost.GPS_node;
import utils.Map.Cost.Route;

import java.util.ArrayList;
import java.util.Random;

public class OnDeadAgent implements Events {

    private TraficFlowContext context;
    private long number_of_dead_cars;
    private boolean started;
    private Random rand;

    public OnDeadAgent(TraficFlowContext context_){

        context = context_;
        number_of_dead_cars = 0;
        started = false;
        rand = new Random();
    }

    @Override
    public void onStart() {
        started = true;
    }

    @Override
    public void onTick() {
        ArrayList<GPS_node> src_s = context.getMap().getSources();
        ArrayList<GPS_node> sink_s = context.getMap().getSinks();
        GPS_node src = src_s.get(rand.nextInt(src_s.size()));
        GPS_node sink = sink_s.get(rand.nextInt(sink_s.size()));
        GraphPath<GPS_node, Route> path = context.getMap().getBestPath(src, sink);
        if(path == null){
            this.onTick();
        }
        if(started) {
            int i;
            ArrayList<Agents> agents = context.getAgents();
            Agents curentAgent;
            int size_list = agents.size();
            for (i = 0; i < size_list; i++) {

                curentAgent = agents.get(i);
                if (curentAgent.isDead()) {

                    number_of_dead_cars++;
                    agents.remove(curentAgent);
                    i--;
                    agents.add(new Cars(src,path));
                }

            }
        }
    }

    @Override
    public boolean isComplet() {
        return false;
    }

    @Override
    public String toString() {
        return "number of dead cars: " + number_of_dead_cars;
    }
}
