package logic.gameelements.target;

import java.util.Observable;

/**
 * Abstract Class that represents a Target object.
 *
 * @author Jhon Salgado
 */
public abstract class AbstractTarget extends Observable implements Target {
    protected boolean active;
    protected int score;

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

    /**
     * Defines that a hittable object has to have a score when it is hit.
     *
     * @return the current score of the object when hit
     */
    @Override
    public int getScore() {
        if(isActive()) {
            return score;
        }
        else{
            return 0;
        }
    }

    /**
     * Check if it is a instance of SpotTarget
     *
     * @return if it is a instance of SpotTarget
     */
    public boolean isSpotTarget(){
        return(this instanceof SpotTarget);
    }

    /**
     * Check if it is a instance of DropTarget
     *
     * @return if it is a instance of DropTarget
     */
    public boolean isDropBumper(){
        return(this instanceof DropTarget);
    }



}
