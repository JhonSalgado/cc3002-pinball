import controller.Game;
import logic.bonus.DropTargetBonus;
import logic.bonus.ExtraBallBonus;
import logic.bonus.JackPotBonus;
import logic.table.Table;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class GameTest {
    Game game;

    @Before
    public void setUp(){
        game=new Game(3,799999999);
    }

    @Test
    public void ballsTest(){

        assertEquals(3,game.getAvailableBalls());

        game.addBall();
        assertEquals(4,game.getAvailableBalls());

        assertFalse(game.gameOver());

        game.dropBall();
        game.dropBall();
        game.dropBall();
        game.dropBall();
        assertEquals(0,game.getAvailableBalls());

        assertTrue(game.gameOver());
    }

    @Test
    public void tableElementsTest() {

        assertFalse(game.isPlayableTable());

        Table noTargetsTable = game.newPlayableTableWithNoTargets("PoorPinball", 3, 0.5);
        game.setGameTable(noTargetsTable);
        assertEquals(0,game.getTargets().size());

        Table table = game.newFullPlayableTable("IPinball", 3, 0.5, 2, 2);
        game.setGameTable(table);
        assertTrue(game.isPlayableTable());

        assertEquals("IPinball",game.getTableName());

        assertEquals(table,game.getCurrentTable());

        int numberOfTargetsExpected=4;
        assertEquals(numberOfTargetsExpected, game.getTargets().size());

        assertFalse(game.getBumpers().get(0).isUpgraded());
        game.upgradeAllBumpers();
        assertTrue(game.getBumpers().get(0).isUpgraded());

    }

    @Test
    public void extraBallBonusCommunicationTest(){
        Table table = game.newFullPlayableTable("IPinball", 3, 0.5, 2, 2);
        game.setGameTable(table);
        assertEquals(3,game.getAvailableBalls());

        game.getBumpers().get(0).hit();
        game.getBumpers().get(0).hit();
        game.getBumpers().get(0).hit();
        game.getBumpers().get(0).hit();
        game.getBumpers().get(0).hit();
        assertEquals(4,game.getAvailableBalls());
    }

    @Test
    public void jackpotBonusCommunicationTest(){
        Table table = game.newFullPlayableTable("IPinball", 3, 0.5, 2, 2);
        game.setGameTable(table);
        assertEquals(0,game.getCurrentScore());

        game.getSpotTargets().get(0).hit();
        assertEquals(100000,game.getCurrentScore());
    }

    @Test
    public void dropTargetsBonusCommunication(){
        Table table = game.newFullPlayableTable("IPinball", 3, 0.5, 2, 2);
        game.setGameTable(table);
        assertFalse(game.getBumpers().get(0).isUpgraded());
        assertFalse(game.getBumpers().get(1).isUpgraded());
        assertFalse(game.getBumpers().get(2).isUpgraded());
        assertEquals(3,game.getAvailableBalls());

        game.getDropTargets().get(0).hit();
        game.getDropTargets().get(1).hit();

        assertTrue(game.getBumpers().get(0).isUpgraded());
        assertTrue(game.getBumpers().get(1).isUpgraded());
        assertTrue(game.getBumpers().get(2).isUpgraded());
        assertEquals(5,game.getAvailableBalls());
        assertEquals(1000200,game.getCurrentScore());
    }

    @Test
    public void bonusTest(){
        Table table = game.newFullPlayableTable("IPinball", 3, 0.5, 2, 2);
        game.setGameTable(table);

        ExtraBallBonus extraBallBonus= (ExtraBallBonus) game.getExtraBallBonus();
        ExtraBallBonus extraBallBonus2= new ExtraBallBonus(game);
        JackPotBonus jackPotBonus= (JackPotBonus) game.getJackPotBonus();
        DropTargetBonus dropTargetBonus= (DropTargetBonus) game.getDropTargetBonus();


        extraBallBonus.trigger(game);
        assertEquals(4,game.getAvailableBalls());
        assertEquals(1,extraBallBonus.timesTriggered());

        extraBallBonus.trigger(game);
        assertEquals(5,game.getAvailableBalls());

        jackPotBonus.trigger(game);
        assertEquals(100000,game.getCurrentScore());
        assertEquals(1,jackPotBonus.timesTriggered());

        dropTargetBonus.trigger(game);
        assertEquals(1100000,game.getCurrentScore());
        assertEquals(1,dropTargetBonus.timesTriggered());
    }


}
