package logic.gameelements.target;

/**
 * Class that represents a SpotTarget object.
 *
 * @author Jhon Salgado
 */
public class SpotTarget extends AbstractTarget {

    public SpotTarget(){
        active=true;
        score=0;
    }

    /**
     * Defines that an object have been hit.
     * Implementations should consider the events that a hit to an object can trigger.
     *
     * @return the score the player obtained hitting the object
     */
    @Override
    public int hit() {
        if(isActive()){
            setChanged();
            notifyObservers();
            active=false;
        }
        return 0;
    }
}
