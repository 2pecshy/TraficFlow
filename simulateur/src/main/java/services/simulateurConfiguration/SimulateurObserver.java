package services.simulateurConfiguration;

import java.util.Observable;

/**
 * Created by Matthieu on 28/01/2018.
 */
public class SimulateurObserver extends Observable {
    private int step = 0;

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
        setChanged();
        notifyObservers();
    }
}
