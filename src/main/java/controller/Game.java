package controller;

import logic.bonus.Bonus;
import logic.bonus.DropTargetBonus;
import logic.bonus.ExtraBallBonus;
import logic.bonus.JackPotBonus;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.gameelements.target.Target;
import logic.table.EmptyTable;
import logic.table.PinballTable;
import logic.table.Table;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Game logic controller class.
 *
 * @author Juan-Pablo Silva
 */
public class Game implements Observer {
    private Table table;
    protected int balls;
    protected int score;
    private Boolean isTableSet=false;
    private Bonus extraBallBonus;
    private Bonus jackpotBonus;
    private Bonus dropTargetBonus;

    /**
     * Creates an instance of Game with the number of balls indicated by numberOfBalls;
     *
     * @param numberOfBalls
     */
    public Game(int numberOfBalls){
        this.balls=numberOfBalls;
        score = 0;
        table = new EmptyTable();
        extraBallBonus = new ExtraBallBonus(this);
        jackpotBonus = new JackPotBonus(this);
        dropTargetBonus = new DropTargetBonus(this);
    }

    /**
     * Creates an instance of Game with the number of balls indicated by numberOfBalls
     * and a seed for the Random object "rand" initialized in the {@Link ExtraBallBonus}
     * class method update.
     * This constructor is useful for testing.
     *
     * @param numberOfBalls
     * @param seed
     */
    public Game(int numberOfBalls,long seed){
        this.balls=numberOfBalls;
        score = 0;
        table = new EmptyTable();
        extraBallBonus = new ExtraBallBonus(this,seed);
        jackpotBonus = new JackPotBonus(this);
        dropTargetBonus = new DropTargetBonus(this);
    }

    /**
     * Adds one ball to the current balls in the game.
     *
     */
    public void addBall(){
        balls++;
    }

    /**
     * Adds score to the current score in the Game.
     *
     * @param score
     */
    public void addScore(int score){
        this.score+=score;
    }
    /**
     * Gets whether the current table is playable or not.
     *
     * @return true if the current table is playable, false otherwise
     */
    public boolean isPlayableTable() {
        return isTableSet;
    }

    /**
     * Gets the instance of {@link logic.bonus.DropTargetBonus} currently in the game.
     *
     * @return the DropTargetBonus instance
     */
    public Bonus getDropTargetBonus() {
        return dropTargetBonus;
    }

    /**
     * Gets the instance of {@link logic.bonus.ExtraBallBonus} currently in the game.
     *
     * @return the ExtraBallBonus instance
     */
    public Bonus getExtraBallBonus() {
        return extraBallBonus;
    }

    /**
     * Gets the instance of {@link logic.bonus.JackPotBonus} currently in the game.
     *
     * @return the JackPotBonus instance
     */
    public Bonus getJackPotBonus() {
        return jackpotBonus;
    }

    /**
     * Creates a new table with the given parameters with no targets.
     *
     * @param name            the name of the table
     * @param numberOfBumpers the number of bumpers in the table
     * @param prob            the probability a {@link logic.gameelements.bumper.PopBumper}
     * @return a new table determined by the parameters
     */
    public Table newPlayableTableWithNoTargets(String name, int numberOfBumpers, double prob) {
        return newFullPlayableTable(name,numberOfBumpers,prob,0,0);
    }

    /**
     * Creates a new table with the given parameters.
     *
     * @param name                the name of the table
     * @param numberOfBumpers     the number of bumpers in the table
     * @param prob                the probability a {@link logic.gameelements.bumper.PopBumper}
     * @param numberOfTargets     the number of {@link logic.gameelements.target.SpotTarget}
     * @param numberOfDropTargets the number of {@link logic.gameelements.target.DropTarget}
     * @return a new table determined by the parameters
     */
    public Table newFullPlayableTable(String name, int numberOfBumpers, double prob, int numberOfTargets, int numberOfDropTargets) {
        PinballTable table = new PinballTable(name,numberOfBumpers,prob,numberOfTargets,numberOfDropTargets);
        return table;
    }

    /**
     * Gets the list of bumpers in the current table.
     *
     * @return the list of bumpers
     * @see Bumper
     */
    public List<Bumper> getBumpers() {
        return table.getBumpers();
    }

    /**
     * Upgrade all {@link Bumper}s in the table.
     */
    public void upgradeAllBumpers() {
        table.upgradeAllBumpers();
    }

    /**
     * Gets the list of SpotTargets in the current table.
     *
     * @return the list of SpotTargets
     * @see SpotTarget
     */
    public List<SpotTarget> getSpotTargets() {
        return table.getSpotTargets();
    }

    /**
     * Gets the list of SpotTargets in the current table.
     *
     * @return the list of SpotTargets
     * @see SpotTarget
     */
    public List<DropTarget> getDropTargets() {
        return table.getDropTargets();
    }

    /**
     * Gets the list of targets in the current table.
     *
     * @return the list of targets
     * @see Target
     */
    public List<Target> getTargets() {
        return table.getTargets();
    }

    /**
     * Gets the name of the current table.
     *
     * @return the name of the current table
     */
    public String getTableName() {
        return table.getTableName();
    }

    /**
     * Gets the current number of available balls to play.
     *
     * @return the number of available balls
     */
    public int getAvailableBalls() {
        return balls;
    }

    /**
     * Gets the points earned so far.
     *
     * @return the earned score
     */
    public int getCurrentScore() {
        return score;
    }

    /**
     * Gets the current table.
     *
     * @return the current table
     * @see Table
     */
    public Table getCurrentTable() {
        return table;
    }

    /**
     * Sets a new table to play.
     *
     * @param newTable the new table
     */
    public void setGameTable(Table newTable) {
        table=newTable;
        isTableSet=true;
        List<Bumper> bumpers= getBumpers();
        for(Bumper bumper:bumpers){
            ((Observable)bumper).addObserver(extraBallBonus);
            ((Observable)bumper).addObserver(this);
        }
        List<SpotTarget> spotTargets=getSpotTargets();
        for(SpotTarget spotTarget:spotTargets){
            spotTarget.addObserver(jackpotBonus);
        }
        List<DropTarget> dropTargets=getDropTargets();
        for(DropTarget dropTarget:dropTargets){
            dropTarget.addObserver(extraBallBonus);
            dropTarget.addObserver((Observer) table);
            dropTarget.addObserver(this);
        }
        ((Observable)table).addObserver(dropTargetBonus);
    }

    /**
     * Reduces the number of available balls and returns the new number.
     *
     * @return the new number of available balls
     */
    public int dropBall() {
        if(this.balls==0)return 0;
        else return --balls;
    }

    /**
     * Checks whether the game is over or not. A game is over when the number of available balls are 0.
     *
     * @return true if the game is over, false otherwise
     */
    public boolean gameOver() {
        if(balls == 0){
            return true;
        }
        else {return false;}
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
        double[]args=(double[])arg;
        this.addScore((int)args[0]);
    }
}
