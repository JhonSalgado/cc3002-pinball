package logic.bonus;

import controller.Game;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 * Class that represents a ExtraBall bonus object.
 *
 * @author Jhon Salgado
 */
public class ExtraBallBonus extends AbstractBonus implements Observer {
    Game game;
    long seed;


    /**
     * Creates an instance of ExtraBallBonus for be used in a game
     *
     * @param game
     */
    public ExtraBallBonus(Game game){

        this.game=game;
        seed=System.currentTimeMillis();
    }

    /**
     * Creates an instance of ExtraBallBonus for be used in a test
     *
     * @param game
     * @param seed
     */
    public ExtraBallBonus(Game game, long seed){

        this.game=game;
        this.seed=seed;
    }

    /**
     * Trigger the specific action the bonus does and applies it to the {@link Game} object.
     *
     * @param game the game controller object
     */
    @Override
    public void trigger(Game game) {
        super.timesTriggered++;
        game.addBall();
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
        Random rnd= new Random(seed);
        if(rnd.nextDouble()<((double)arg)){
            trigger(this.game);
        }
    }
}
