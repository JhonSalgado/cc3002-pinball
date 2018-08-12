package gui;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.input.KeyCode;

import static gui.GameFactory.*;

public class PinBallApp extends GameApplication {

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
        Entity kickBumper= newKickBumper(100,200);
        Entity kickBumper2= newKickBumper(250,200);
        Entity kickBumper3= newKickBumper(400,200);
        Entity popBumper= newPopBumper(50,400);
        Entity spotTarget= newSpotTarget(100,60);
        Entity spotTarget2= newSpotTarget(200,60);
        Entity spotTarget3= newSpotTarget(300,60);
        getGameWorld().addEntities(bg, playerLeft, playerRight,ball,walls);
        getGameWorld().addEntities(kickBumper, kickBumper2, kickBumper3);
        getGameWorld().addEntities(popBumper);
        getGameWorld().addEntities(spotTarget, spotTarget2, spotTarget3);
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
                    protected void onCollisionBegin(Entity player, Entity ball){
                        ball.rotateBy(180);
                    }
                });
    }


    public static void main(String args[]){
        launch(args);
    }
}
