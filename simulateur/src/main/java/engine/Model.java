package engine;

public abstract class Model extends Thread {

    protected static final int PAUSE = 2;
    protected static final int RUNNING = 1;
    protected static final int NOT_RUNNING = 0;

    protected int isRunning;

    public int isRunning() {
        return isRunning;
    }

    protected abstract void pauseSimulation();
    protected abstract void resumeSimulation();
    protected abstract void startSimulation();
}
