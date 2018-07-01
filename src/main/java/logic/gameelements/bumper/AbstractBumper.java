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
        return hitsToUpgrade-timesHit;

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
        if (remainingHitsToUpgrade()==0){
            this.upgrade();
            this.setChanged();
            this.notifyObservers(0.1);
        }
        return getScore();
    }

    @Override
    public int getScore() {
        return hitScore;
    }
}
