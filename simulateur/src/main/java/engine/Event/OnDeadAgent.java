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
    private long number_of_created_cars;
    private boolean started;
    private Random rand;
    private int nb_spawn_on_dead;
    public static final int MAX_DEAD = 100000;

    private long nb_tick_arrivee;

    public OnDeadAgent(TraficFlowContext context_){

        context = context_;
        number_of_dead_cars = 0;
        started = false;
        nb_spawn_on_dead = 1;
        rand = new Random();
        number_of_created_cars = 0;
        nb_tick_arrivee = 0;
    }
    public OnDeadAgent(TraficFlowContext context_, int nb_spawn_on_dead_){

        context = context_;
        number_of_dead_cars = 0;
        started = false;
        nb_spawn_on_dead = nb_spawn_on_dead_;
        rand = new Random();
        number_of_created_cars = 0;
        nb_tick_arrivee = 0;
    }


    @Override
    public void onStart() {
        started = true;
    }

    @Override
    public void onTick() {

        if(started) {
            GraphPath<GPS_node, Route> path = null;
            ArrayList<GPS_node> src_s;
            ArrayList<GPS_node> sink_s;
            GPS_node src = null;
            GPS_node sink;

            int i;
            ArrayList<Agents> agents = context.getAgents();
            Agents curentAgent;
            int size_list = agents.size();
            if(number_of_created_cars == 0)
                number_of_created_cars = agents.size();
            for (i = 0; i < size_list; i++) {

                curentAgent = agents.get(i);
                if (curentAgent.isDead()) {
                    for(int k = 0; k < nb_spawn_on_dead; k++) {
                        path = null;
                        while (path == null) {
                            src_s = context.getMap().getSources();
                            sink_s = context.getMap().getSinks();
                            src = src_s.get(rand.nextInt(src_s.size()));
                            sink = sink_s.get(rand.nextInt(sink_s.size()));
                            path = context.getMap().getBestPath(src, sink);
                        }
                        agents.add(new Cars(src,path));
                        number_of_created_cars++;
                    }

                    if(number_of_dead_cars >= MAX_DEAD) {
                        System.out.println("PID: "+ context +"Event OnDeadAgent: simulation finish");
                        context.setFinish();
                    }
                    number_of_dead_cars++;
                    nb_tick_arrivee += curentAgent.getDureeVie();
                    agents.remove(curentAgent);
                    i--;

                }

            }
        }
    }

    public long getNumber_of_created_cars() {
        return number_of_created_cars;
    }

    public long getNb_tick_arrivee() {
        return nb_tick_arrivee;
    }

    public long getNumber_of_dead_cars() {
        return number_of_dead_cars;
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
