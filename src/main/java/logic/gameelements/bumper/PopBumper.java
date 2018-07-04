package logic.gameelements.bumper;

/**
 * Class that represents a PopBumper object.
 *
 * @author Jhon Salgado
 */
public class PopBumper extends AbstractBumper{

    /**
     * Creates an instance of PopBumper with the parameters indicated by
     * the homework statement
     *
     */
    public PopBumper(){
        super.hitScore=100;
        super.baseHitScore=100;
        super.upgradedHitScore=300;
        super.hitsToUpgrade=3;
        super.timesHit=0;
    }

}
