import logic.gameelements.bumper.*;
import org.junit.*;
import static junit.framework.TestCase.assertEquals;

public class PopBumperTest {
    private PopBumper popBumper;

    @Before
    public void setUP(){
        popBumper=new PopBumper();
    }

    @Test
    public void allTest(){

        boolean upgradeExpected=false;
        assertEquals(upgradeExpected,popBumper.isUpgraded());

        int scoreExpected=100;
        assertEquals(scoreExpected,popBumper.getScore());

        for(int i=0; true; i++){
            popBumper.hit();
            if(popBumper.isUpgraded())break;
        }

        boolean upgradeExpected2=true;
        assertEquals(upgradeExpected2,popBumper.isUpgraded());

        int scoreExpected2=300;
        assertEquals(scoreExpected2,popBumper.getScore());

        popBumper.downgrade();
        boolean upgradeExpected3=false;
        assertEquals(upgradeExpected3,popBumper.isUpgraded());
    }
}
