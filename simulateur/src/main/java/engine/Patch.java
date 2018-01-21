package engine;

public interface Patch {

    public Patch getPatchsAround(Patch patch);
    public Context getContext();
}
