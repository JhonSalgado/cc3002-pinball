package logic.gameelements.bumper;

/**
 * Class that represents a KickerBumper object.
 *
 * @author Jhon Salgado
 */
public class KickerBumper extends AbstractBumper {

    /**
     * Creates an instance of KickerBumper with the parameters indicated by
     * the homework statement
     *
     */
    public KickerBumper(){
        super.hitScore=500;
        super.baseHitScore=500;
        super.upgradedHitScore=1000;
        super.hitsToUpgrade=5;
        super.timesHit=0;
    }
}
