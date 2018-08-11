package logic.bonus;

import controller.Game;

import java.util.Observable;

/**
 * Class that represents a DropTarget bonus object.
 *
 * @author Jhon Salgado
 */
public class DropTargetBonus extends AbstractBonus {

    /**
     * Creates an instance of DropTargetBonus for be used in a game
     *
     * @param game
     */
    public DropTargetBonus(Game game){

        this.game=game;
        score=1000000;
    }

    /**
     * Trigger the specific action the bonus does and applies it to the {@link Game} object.
     *
     * @param game the game controller object
     */
    @Override
    public void trigger(Game game) {
        super.timesTriggered++;
        game.addScore(score);
        game.upgradeAllBumpers();
    }
}
