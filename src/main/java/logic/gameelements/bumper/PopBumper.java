package logic.gameelements.bumper;

public class PopBumper extends AbstractBumper{

    public PopBumper(){
        super.hitScore=100;
        super.baseHitScore=100;
        super.upgradedHitScore=300;
        super.hitsToUpgrade=3;
        super.timesHit=0;
    }

}
