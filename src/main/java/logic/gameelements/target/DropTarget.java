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
        double[] args=new double[2];
        args[0]=0;
        args[1]=0;
        if(isActive()){
            args[0]=getScore();
            args[1]=0.3;
            active=false;
        }
        setChanged();
        notifyObservers(args);
        return (int)args[0];
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
