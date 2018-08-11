package gui;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.input.KeyCode;

import static gui.GameFactory.*;

public class Interface extends GameApplication {

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(500);
        gameSettings.setHeight(600);
        gameSettings.setTitle("PinBall");
        gameSettings.setVersion("0.1");
    }

    public enum Types{
        PLAYER, BALL, WALL
    }

    protected void initGame(){
        Entity walls=newWalls();
        Entity ball=newBall(200,200);
        Entity bg=newBackground();
        Entity playerLeft= newPlayer(125,520);
        Entity playerRight= newPlayer(285,520);
        getGameWorld().addEntities(bg, playerLeft, playerRight,ball,walls);
    }

    protected void initInput(){
        Input input=getInput();

        input.addAction(new UserAction("Move Right"){
            @Override
            protected void onAction() {
                getGameWorld().getEntitiesByType(Types.PLAYER)
                        .forEach(e->e.rotateBy(45));
            }
        },KeyCode.D);
    }

    protected void initPhysics() {
        getPhysicsWorld().setGravity(0,0);
        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(Types.BALL, Types.WALL) {
                    @Override
                    protected void onHitBoxTrigger(Entity ball, Entity wall, HitBox boxBall, HitBox boxWall) {
                        if (boxWall.getName().equals("BOT")) {
                            ball.removeFromWorld();
                        }
                    }
                });
        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(Types.PLAYER, Types.BALL) {

                    @Override
                    protected void onCollisionBegin(Entity player, Entity ball) {
                        ;
                    }
                });
    }


    public static void main(String args[]){
        launch(args);
    }
}
