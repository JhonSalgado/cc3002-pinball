package logic.bonus;

import controller.Game;

import java.util.Observable;

/**
 * Class that represents a DropTarget bonus object.
 *
 * @author Jhon Salgado
 */
public class DropTargetBonus extends AbstractBonus {
    Game game;

    /**
     * Creates an instance of DropTargetBonus for be used in a game
     *
     * @param game
     */
    public DropTargetBonus(Game game){
        this.game=game;
    }

    /**
     * Trigger the specific action the bonus does and applies it to the {@link Game} object.
     *
     * @param game the game controller object
     */
    @Override
    public void trigger(Game game) {
        super.timesTriggered++;
        game.addScore(1000000);
        game.upgradeAllBumpers();
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
