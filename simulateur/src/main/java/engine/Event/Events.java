package engine.Event;

public interface Events {

    public void onStart();
    public void onTick();
    public boolean isComplet();
}
