package logic.gameelements.bumper;

public class KickerBumper extends AbstractBumper {

    public KickerBumper(){
        super.hitScore=500;
        super.baseHitScore=500;
        super.upgradedHitScore=1000;
        super.hitsToUpgrade=5;
        super.timesHit=0;
    }
}
