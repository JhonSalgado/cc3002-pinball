package logic.gameelements.bumper;

public abstract class AbstractBumper implements Bumper {
    protected int hitScore;
    protected int baseHitScore;
    protected int upgradedHitScore;
    protected int hitsToUpgrade;
    protected int timesHit;


    @Override
    public int remainingHitsToUpgrade() {
        return hitsToUpgrade-timesHit;

    }

    @Override
    public boolean isUpgraded() {
        if(hitScore==upgradedHitScore){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void upgrade() {
        hitScore=upgradedHitScore;
    }

    @Override
    public void downgrade() {
        hitScore=baseHitScore;
        timesHit=hitsToUpgrade;
    }

    @Override
    public int hit() {
        timesHit++;
        if (remainingHitsToUpgrade()==0){
            this.upgrade();
        }
        return 0;
    }

    @Override
    public int getScore() {
        return hitScore;
    }
}
