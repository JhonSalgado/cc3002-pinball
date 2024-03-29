package logic.gameelements.target;

import logic.gameelements.Hittable;

/**
 * Interface that represents operations related to a target behavior.
 *
 * @author Juan-Pablo Silva
 * @see SpotTarget
 * @see DropTarget
 */
public interface Target extends Hittable {
    /**
     * Gets whether the target is currently active or not.
     *
     * @return true if the target is active, false otherwise
     */
    boolean isActive();

    /**
     * Resets the state of a target making it active again.
     */
    void reset();

    /**
     * Check if it is a instance of SpotTarget
     *
     * @return if it is a instance of SpotTarget
     */
    boolean isSpotTarget();

    /**
     * Check if it is a instance of DropTarget
     *
     * @return if it is a instance of DropTarget
     */
    boolean isDropTarget();
}
