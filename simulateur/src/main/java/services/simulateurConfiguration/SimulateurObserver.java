package services.simulateurConfiguration;

import sample.SimulatorData;

import java.util.Observable;

/**
 * Created by Matthieu on 28/01/2018.
 */
public class SimulateurObserver extends Observable {
    private int step = 0;
    private SimulatorData data = new SimulatorData("0", 0, 0, -1);
    private boolean finish = false;

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

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public void sayFinish(){
        finish = true;
        setChanged();
        notifyObservers();

    }
}
