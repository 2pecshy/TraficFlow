package engine;

public abstract class Model extends Thread {

    protected static final int PAUSE = 2;
    protected static final int RUNNING = 1;
    protected static final int NOT_RUNNING = 0;

    private int pid;

    protected int isRunning;

    public int isRunning() {
        return isRunning;
    }

    public int getPid(){
        return pid;
    }

    protected void setPid(int pid_){
        pid = pid_;
    }

    protected abstract void pauseSimulation();
    protected abstract void resumeSimulation();
    protected abstract void startSimulation();
}
