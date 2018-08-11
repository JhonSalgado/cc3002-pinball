package logic.bonus;

import controller.Game;

import java.util.Observable;

/**
 * Abstract class that represents a bonus object.
 *
 * @author Jhon Salgado
 */
public abstract class AbstractBonus extends Observable implements Bonus{
    int score;
    Game game;
    protected int timesTriggered;

    /**
     * Gets the number of times the bonus has been triggered.
     *
     * @return number of times the bonus has been triggered
     */
    @Override
    public int timesTriggered() {
        return timesTriggered;
    }


    /**
     * Trigger the specific action the bonus does and applies it to the {@link Game} object.
     *
     * @param game the game controller object
     */
    @Override
    public void trigger(Game game) {
        timesTriggered++;
        game.addScore(score);
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
        trigger(game);
    }
}
