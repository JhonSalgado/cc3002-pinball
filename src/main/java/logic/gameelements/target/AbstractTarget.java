package logic.gameelements.target;

import java.util.Observable;

/**
 * Abstract Class that represents a Target object.
 *
 * @author Jhon Salgado
 */
public abstract class AbstractTarget extends Observable implements Target {
    protected boolean active;

    /**
     * Gets whether the target is currently active or not.
     *
     * @return true if the target is active, false otherwise
     */
    @Override
    public boolean isActive() {
        return active;
    }

    /**
     * Resets the state of a target making it active again.
     */
    @Override
    public void reset() {
        active=true;
    }

}
