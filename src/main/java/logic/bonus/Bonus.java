package logic.bonus;

import controller.Game;

import java.util.Observable;
import java.util.Observer;

/**
 * Interface that represents a bonus object.
 *
 * @author Juan-Pablo Silva
 * @see ExtraBallBonus
 * @see JackPotBonus
 * @see DropTargetBonus
 */
public interface Bonus extends Observer {
    /**
     * Gets the number of times the bonus has been triggered.
     *
     * @return number of times the bonus has been triggered
     */
    int timesTriggered();

    /**
     * Trigger the specific action the bonus does and applies it to the {@link Game} object.
     *
     * @param game the game controller object
     */
    void trigger(Game game);

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
    void update(Observable o, Object arg);
}
