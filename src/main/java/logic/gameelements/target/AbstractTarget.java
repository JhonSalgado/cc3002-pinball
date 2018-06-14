package logic.gameelements.target;

public abstract class AbstractTarget implements Target {
    protected boolean active;

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void reset() {
        active=true;
    }


}
