package logic.gameelements.target;

import java.util.Random;

/**
 * Class that represents a DropTarget object.
 *
 * @author Jhon Salgado
 */
public class DropTarget extends AbstractTarget{

    public DropTarget(){
        active=true;
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
            active=false;
            setChanged();
            notifyObservers(0.3);
        }
        return getScore();
    }

    /**
     * Defines that a hittable object has to have a score when it is hit.
     *
     * @return the current score of the object when hit
     */
    @Override
    public int getScore() {
        if(isActive()) {
            return 100;
        }
        else{
            return 0;
        }
    }
}
