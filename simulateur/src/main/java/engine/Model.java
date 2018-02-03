package engine;

public abstract class Model extends Thread {

    protected static final int PAUSE = 2;
    protected static final int RUNNING = 1;
    protected static final int NOT_RUNNING = 0;
    protected static final int FINISH = 0;
    protected long clock_speed;
    public static final long DEFAULT_CLOCK_SPEED = 500; //0.5sec
    public static final  long MIN_CLOCK_SPEED = 10;

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

    public void setClock_speed(Integer value){
        if(clock_speed > 10){
            clock_speed = value;
        }
    }

    protected abstract void pauseSimulation();
    protected abstract void resumeSimulation();
    protected abstract void startSimulation();
}
