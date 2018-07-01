import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.DropTarget;
import logic.table.EmptyTable;
import logic.table.PinballTable;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class TableTest {
    PinballTable pinballTable;
    EmptyTable emptyTable;

    @Before
    public void setUp(){
        pinballTable=new PinballTable("pinball",3,0.5,2,2);
        emptyTable=new EmptyTable();
    }

    @Test
    public void pinballTest(){

        assertEquals(true, pinballTable.isPlayableTable());

        String nameExpected="pinball";
        assertEquals(nameExpected,pinballTable.getTableName());

        int numberOfSpotTargetsExpected=2;
        assertEquals(numberOfSpotTargetsExpected, pinballTable.getSpotTargets().size());

        int numberOfDropTargetsExpected=2;
        assertEquals(numberOfDropTargetsExpected,pinballTable.getNumberOfDropTargets());
        assertEquals(numberOfDropTargetsExpected, pinballTable.getDropTargets().size());

        int numberOfTargetsExpected=4;
        assertEquals(numberOfTargetsExpected, pinballTable.getTargets().size());

        int currentlyDroppedDropTargetsExpected=0;
        assertEquals(currentlyDroppedDropTargetsExpected,pinballTable.getCurrentlyDroppedDropTargets());

        DropTarget drop1=pinballTable.getDropTargets().get(0);
        DropTarget drop2=pinballTable.getDropTargets().get(1);
        drop1.hit();
        drop2.hit();

        int currentlyDroppedDropTargetsExpected2=2;
        assertEquals(currentlyDroppedDropTargetsExpected2,pinballTable.getCurrentlyDroppedDropTargets());

        pinballTable.resetDropTargets();
        int currentlyDroppedDropTargetsExpected3=0;
        assertEquals(currentlyDroppedDropTargetsExpected3,pinballTable.getCurrentlyDroppedDropTargets());

        assertEquals(false, pinballTable.getBumpers().get(2).isUpgraded());
        pinballTable.upgradeAllBumpers();
        assertEquals(true, pinballTable.getBumpers().get(2).isUpgraded());
    }

    @Test
    public void emptyTableTest(){
        assertNull(emptyTable.getTableName());
        assertNull(emptyTable.getBumpers());
        assertNull(emptyTable.getDropTargets());
        assertNull(emptyTable.getSpotTargets());
        assertNull(emptyTable.getTargets());
        assertEquals(0,emptyTable.getNumberOfDropTargets());
        assertEquals(0,emptyTable.getCurrentlyDroppedDropTargets());
        emptyTable.resetDropTargets();
        assertEquals(0,emptyTable.getCurrentlyDroppedDropTargets());
        assertFalse(emptyTable.isPlayableTable());

    }
}
