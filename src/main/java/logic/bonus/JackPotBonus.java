package logic.bonus;

import controller.Game;

import java.util.Observable;

/**
 * Class that represents a JackPot bonus object.
 *
 * @author Jhon Salgado
 */
public class JackPotBonus extends AbstractBonus {

    /**
     * Creates an instance of JackPotBonus for be used in a game
     *
     * @param game
     */
    public JackPotBonus(Game game){
        this.game=game;
        score=100000;
    }

}
