package engine;

public interface Agents {

    public void onTick();
    public void onDraw();
    public boolean isDead();
    public Context getContext();
}
