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
    public static final int MAX_DEAD = 100000;

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

        if(started) {
            GraphPath<GPS_node, Route> path;
            ArrayList<GPS_node> src_s;
            ArrayList<GPS_node> sink_s;
            GPS_node src = null;
            GPS_node sink;

            int i;
            ArrayList<Agents> agents = context.getAgents();
            Agents curentAgent;
            int size_list = agents.size();
            for (i = 0; i < size_list; i++) {

                curentAgent = agents.get(i);
                if (curentAgent.isDead()) {
                    path = null;
                    while (path == null){
                        src_s = context.getMap().getSources();
                        sink_s = context.getMap().getSinks();
                        src = src_s.get(rand.nextInt(src_s.size()));
                        sink = sink_s.get(rand.nextInt(sink_s.size()));
                        path = context.getMap().getBestPath(src, sink);
                    }
                    number_of_dead_cars++;
                    agents.remove(curentAgent);
                    if(number_of_dead_cars >= MAX_DEAD) {
                        System.out.println("PID: "+ context +"Event OnDeadAgent: simulation finish");
                        context.setFinish();
                    }
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
