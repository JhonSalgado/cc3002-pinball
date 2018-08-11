package logic.table;

import logic.bonus.ExtraBallBonus;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.gameelements.target.Target;

import java.util.*;

/**
 * Class that represents a Pinball Table object.
 *
 * @author Jhon Salgado
 */
public class PinballTable extends AbstractTable implements Observer{

    /**
     * Creates an instance of PinballTable.
     *
     * @param name
     * @param numberOfBumpers
     * @param prob
     * @param numberOfTargets
     * @param numberOfDropTargets
     */
    public PinballTable(String name, int numberOfBumpers, double prob, int numberOfTargets, int numberOfDropTargets){
        super.name = name;
        isPlayableTable=true;
        bumpers=new LinkedList<>();
        dropTargets=new LinkedList<>();
        spotTargets=new LinkedList<>();
        targets=new LinkedList<>();
        Random rand = new Random();
        for (int i = 1; i <= numberOfBumpers; i++) {
            if (rand.nextDouble() < prob) {
                PopBumper popBumper = new PopBumper();
                bumpers.add(popBumper);
            } else {
                KickerBumper kickerBumper = new KickerBumper();
                bumpers.add(kickerBumper);
            }
        }
        for(int i=1; i<=numberOfTargets;i++){
            SpotTarget spotTarget=new SpotTarget();
            spotTargets.add(spotTarget);
            targets.add(spotTarget);
        }
        for(int i=1; i<=numberOfDropTargets;i++){
            DropTarget dropTarget=new DropTarget();
            targets.add(dropTarget);
            dropTargets.add(dropTarget);
        }

    }

}

