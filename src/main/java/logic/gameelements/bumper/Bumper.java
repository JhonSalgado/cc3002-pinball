package logic.gameelements.bumper;

import logic.gameelements.Hittable;

/**
 * Interface that represents operations related to a bumper behavior.
 *
 * @author Juan-Pablo Silva
 * @see KickerBumper
 * @see PopBumper
 * @see AbstractBumper
 */
public interface Bumper extends Hittable {

    /**
     * Check if it is a instance of KickerBumper
     *
     * @return if it is a instance of KickerBumper
     */
    boolean isKickerBumper();

    /**
     * Check if it is a instance of PopBumper
     *
     * @return if it is a instance of PopBumper
     */
    boolean isPopBumper();

    /**
     * Gets the remaining hits the bumper has to receive to upgrade.
     *
     * @return the remaining hits to upgrade the bumper
     */
    int remainingHitsToUpgrade();

    /**
     * Gets whether the bumper is currently upgraded or not.
     *
     * @return true if the bumper is upgraded, false otherwise
     */
    boolean isUpgraded();

    /**
     * Upgrades a bumper making {@link #isUpgraded()} return true.
     */
    void upgrade();

    /**
     * Downgrades a bumper making {@link #isUpgraded()} return false.
     */
    void downgrade();
}
