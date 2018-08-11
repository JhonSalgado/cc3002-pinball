package logic.table;


import logic.gameelements.bumper.Bumper;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.gameelements.target.Target;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

/**
 * Class that represents a empty Table object.
 *
 * @author Jhon Salgado
 */
public class EmptyTable extends AbstractTable {

    public EmptyTable(){
        super.name = "";
        isPlayableTable=false;
        bumpers=new LinkedList<>();
        dropTargets=new LinkedList<>();
        spotTargets=new LinkedList<>();
        targets=new LinkedList<>();
    }
}
