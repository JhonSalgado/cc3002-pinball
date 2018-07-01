import logic.gameelements.target.SpotTarget;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

public class SpotTargetTest {
    private SpotTarget spotTarget;

    @Before
    public void setUP(){spotTarget=new SpotTarget();
    }

    @Test
    public void FunctionalityTest(){

        boolean activeExpected=true;
        assertEquals(activeExpected,spotTarget.isActive());

        int scoreExpected=0;
        assertEquals(scoreExpected,spotTarget.getScore());

        spotTarget.hit();

        boolean activeExpected2=false;
        assertEquals(activeExpected2,spotTarget.isActive());

        spotTarget.reset();

        boolean activeExpected3=true;
        assertEquals(activeExpected3,spotTarget.isActive());
    }
}

