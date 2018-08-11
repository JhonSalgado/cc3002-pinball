package logic.gameelements.bumper;

import java.util.Observable;

/**
 * Abstract Class that represents a Bumper object.
 *
 * @author Jhon Salgado
 */
public abstract class AbstractBumper extends Observable implements Bumper{
    protected int hitScore;
    protected int baseHitScore;
    protected int upgradedHitScore;
    protected int hitsToUpgrade;
    protected int timesHit;


    /**
     * Gets the remaining hits the bumper has to receive to upgrade.
     *
     * @return the remaining hits to upgrade the bumper
     */
    @Override
    public int remainingHitsToUpgrade() {
        int dif= hitsToUpgrade-timesHit;
        if(dif>=0)return dif;
        else return 0;
    }

    /**
     * Gets whether the bumper is currently upgraded or not.
     *
     * @return true if the bumper is upgraded, false otherwise
     */
    @Override
    public boolean isUpgraded() {
        return hitScore == upgradedHitScore;
    }

    @Override
    public void upgrade() {
        hitScore=upgradedHitScore;
    }

    @Override
    public void downgrade() {
        hitScore=baseHitScore;
        timesHit=0;
    }

    @Override
    public int hit() {
        timesHit++;
        double[] args=new double[2];
        args[0]=getScore();
        args[1]=0;
        if (remainingHitsToUpgrade()==0 && !this.isUpgraded()){
            this.upgrade();
            args[0]=getScore();
            args[1]=0.1;
        }
        this.setChanged();
        this.notifyObservers(args);
        return getScore();
    }

    @Override
    public int getScore() {
        return hitScore;
    }
}
