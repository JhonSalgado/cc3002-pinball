import logic.gameelements.bumper.*;
import org.junit.*;
import static junit.framework.TestCase.assertEquals;

public class KickerBumperTest {
    private KickerBumper kickerBumper;

    @Before
    public void setUP(){kickerBumper=new KickerBumper();
    }

    @Test
    public void FunctionalityTest(){

        boolean upgradeExpected=false;
        assertEquals(upgradeExpected,kickerBumper.isUpgraded());

        int scoreExpected=500;
        assertEquals(scoreExpected,kickerBumper.getScore());

        for(int i=0; true; i++){
            kickerBumper.hit();
            if(kickerBumper.isUpgraded())break;
        }

        boolean upgradeExpected2=true;
        assertEquals(upgradeExpected2,kickerBumper.isUpgraded());

        int scoreExpected2=1000;
        assertEquals(scoreExpected2,kickerBumper.getScore());

        kickerBumper.downgrade();
        boolean upgradeExpected3=false;
        assertEquals(upgradeExpected3,kickerBumper.isUpgraded());
    }

}
