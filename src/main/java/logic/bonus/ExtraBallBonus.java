package logic.bonus;

import controller.Game;

public class ExtraBallBonus extends AbstractBonus {
    /**
     * Trigger the specific action the bonus does and applies it to the {@link Game} object.
     *
     * @param game the game controller object
     */
    @Override
    public void trigger(Game game) {
        super.timesTriggered++;
        game.balls++;
    }
}
