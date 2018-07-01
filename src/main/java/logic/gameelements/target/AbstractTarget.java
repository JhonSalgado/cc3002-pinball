package logic.gameelements.target;

import java.util.Observable;

public abstract class AbstractTarget extends Observable implements Target {
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
