package logic.table;

import logic.gameelements.bumper.Bumper;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.gameelements.target.Target;

import java.util.*;

public abstract class AbstractTable extends Observable implements Table, Observer {
    protected String name;
    protected List<Bumper> bumpers;
    protected List<DropTarget> dropTargets;
    protected List<SpotTarget> spotTargets;
    protected List<Target> targets;
    protected boolean isPlayableTable;


    /**
     * Gets the table name.
     *
     * @return the table's name
     */
    @Override
    public String getTableName() {
        return name;
    }

    /**
     * Gets the number of {@link DropTarget} in the table.
     *
     * @return the number of DropTargets in the table
     */
    @Override
    public int getNumberOfDropTargets() {
        return dropTargets.size();
    }

    /**
     * Gets the number of {@link DropTarget} that are currently dropped or inactive.
     *
     * @return the number of DropTargets that are currently inactive
     */
    @Override
    public int getCurrentlyDroppedDropTargets() {
        int contador =0;
        for(DropTarget dropTarget: dropTargets){
            if (!dropTarget.isActive()){
                contador+=1;
            }
        }
        return contador;
    }

    /**
     * Gets the {@link List} of {@link Bumper}s in the table.
     *
     * @return the bumpers in the table
     */
    @Override
    public List<Bumper> getBumpers() {
        return bumpers;
    }

    /**
     * Gets the {@link List} of {@link Target}s in the table.
     *
     * @return the targets in the table
     */
    @Override
    public List<Target> getTargets() {
        return targets;
    }

    /**
     * Gets the {@link List} of {@link SpotTarget}s in the table.
     *
     * @return the targets in the table
     */
    @Override
    public List<SpotTarget> getSpotTargets() { return spotTargets;
    }

    /**
     * Gets the {@link List} of {@link DropTarget}s in the table.
     *
     * @return the targets in the table
     */
    @Override
    public List<DropTarget> getDropTargets() {
        return dropTargets;
    }

    /**
     * Resets all {@link DropTarget} in the table. Make them active.
     */
    @Override
    public void resetDropTargets() {
        for(DropTarget dropTarget: dropTargets){
            dropTarget.reset();
        }
    }

    /**
     * Upgrade all {@link Bumper}s in the table.
     */
    @Override
    public void upgradeAllBumpers() {
        for(Bumper bumper:bumpers){
            if(!bumper.isUpgraded()) {
                bumper.upgrade();
            }
        }
    }

    /**
     * Gets whether the table is playable or not.
     *
     * @return true if the table is playable, false otherwise
     */
    @Override
    public boolean isPlayableTable() {
        return isPlayableTable;
    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        if (getNumberOfDropTargets()==getCurrentlyDroppedDropTargets()){
            setChanged();
            notifyObservers();
        }
    }

}
