package logic.table;

import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.gameelements.target.Target;

import java.util.List;

/**
 * Interface that represents the basics of a table to be played on.
 *
 * @author Juan-Pablo Silva
 */
public interface Table {
    /**
     * Gets the table name.
     *
     * @return the table's name
     */
    String getTableName();

    /**
     * Gets the number of {@link logic.gameelements.target.DropTarget} in the table.
     *
     * @return the number of DropTargets in the table
     */
    int getNumberOfDropTargets();

    /**
     * Gets the number of {@link logic.gameelements.target.DropTarget} that are currently dropped or inactive.
     *
     * @return the number of DropTargets that are currently inactive
     */
    int getCurrentlyDroppedDropTargets();

    /**
     * Gets the {@link List} of {@link Bumper}s in the table.
     *
     * @return the bumpers in the table
     */
    List<Bumper> getBumpers();

    /**
     * Gets the {@link List} of {@link Target}s in the table.
     *
     * @return the targets in the table
     */
    List<Target> getTargets();

    /**
     * Gets the {@link List} of {@link SpotTarget}s in the table.
     *
     * @return the SpotTargets in the table
     */
    List<SpotTarget> getSpotTargets();

    /**
     * Gets the {@link List} of {@link SpotTarget}s in the table.
     *
     * @return the DropTargets in the table
     */
    List<DropTarget> getDropTargets();

    /**
     * Resets all {@link logic.gameelements.target.DropTarget} in the table. Make them active.
     */
    void resetDropTargets();

    /**
     * Upgrade all {@link Bumper}s in the table.
     */
    void upgradeAllBumpers();

    /**
     * Gets whether the table is playable or not.
     *
     * @return true if the table is playable, false otherwise
     */
    boolean isPlayableTable();

}
