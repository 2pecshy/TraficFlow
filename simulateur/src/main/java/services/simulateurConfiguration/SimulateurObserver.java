package services.simulateurConfiguration;

import sample.SimulatorData;

import java.util.Observable;

/**
 * Created by Matthieu on 28/01/2018.
 */
public class SimulateurObserver extends Observable {
    private int step = 0;
    private SimulatorData data = new SimulatorData();

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
        setChanged();
        notifyObservers();
    }

    public SimulatorData getData() {
        return data;
    }

    public void setData(SimulatorData data) {
        this.data = data;
        setChanged();
        notifyObservers();
    }
}
