package engine.Event;

import engine.Agent.Agents;
import engine.Agent.Cars;
import engine.Contexts.Context;
import engine.Contexts.TraficFlowContext;

import java.util.ArrayList;

public class OnDeadCars implements Events {

    private Context context;
    private long number_of_dead_cars;
    private boolean started;

    public OnDeadCars(Context context_){

        context = context_;
        number_of_dead_cars = 0;
        started = false;
    }

    @Override
    public void onStart() {
        started = true;
    }

    @Override
    public void onTick() {
        if(started) {
            int i;
            ArrayList<Agents> agents = context.getAgents();
            Agents curentAgent;
            int size_list = agents.size();
            for (i = 0; i < size_list; i++) {

                curentAgent = agents.get(i);
                if (curentAgent.isDead()) {

                    if (curentAgent instanceof Cars) {
                        number_of_dead_cars++;
                        agents.remove(curentAgent);
                        i--;
                        agents.add(new Cars());
                    }
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
