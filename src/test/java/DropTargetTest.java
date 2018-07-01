import logic.gameelements.target.DropTarget;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

public class DropTargetTest {
    private DropTarget dropTarget;

    @Before
    public void setUP(){dropTarget=new DropTarget();
    }

    @Test
    public void FunctionalityTest(){

        boolean activeExpected=true;
        assertEquals(activeExpected,dropTarget.isActive());

        int scoreExpected=100;
        assertEquals(scoreExpected,dropTarget.getScore());

        dropTarget.hit();

        boolean activeExpected2=false;
        assertEquals(activeExpected2,dropTarget.isActive());

        int scoreExpected2=0;
        assertEquals(scoreExpected2,dropTarget.getScore());

        dropTarget.reset();

        boolean activeExpected3=true;
        assertEquals(activeExpected3,dropTarget.isActive());
    }
}
