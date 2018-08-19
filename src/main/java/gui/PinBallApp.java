package gui;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.Body;
import com.almasb.fxgl.settings.GameSettings;
import controller.Game;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.gameelements.target.Target;
import logic.table.PinballTable;
import logic.table.Table;

import java.util.Map;

import static gui.GameFactory.*;

public class PinBallApp extends GameApplication {

    private Game game;
    private boolean isBallRemoved=true;
    private boolean leftFlipperHit =false; //Indica si se el usuario a ordenado golpear con el flipper izquierdo
    private boolean leftFlipperBack=false; //Indica si el flipper izquierdo debe regresar a su posicion inicial
    private boolean rightFlipperHit =false;  // Indica si se el usuario a ordenado golpear con el flipper derecho
    private boolean rightFlipperBack =false; //Indica si el flipper derecho debe regresar a su posicion inicial

    public enum Types{
        PLAYER, BALL, WALL, HITTABLE_BUMPER, HITTABLE_TARGET
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(700);
        gameSettings.setHeight(600);
        gameSettings.setTitle("PinBall");
        gameSettings.setVersion("0.1");
    }

    protected void initGame(){

        //Creating and Attaching entities
        Entity walls=newWalls();
        Entity infoPanel=newInfoPanel(500,0);
        Entity bg=newBackground();
        Entity playerLeft= newPlayer(125,520);
        Entity playerRight= newPlayer(285,520);


        getGameWorld().addEntities(bg, playerLeft, playerRight ,walls,infoPanel);

        game=new Game(3);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("Score", 0);
        vars.put("Balls", 3);
    }

    @Override
    protected void initUI() {
        Text scoreT= getUIFactory().newText("Score: ",Color.BLACK, 30);
        Text score= getUIFactory().newText("",Color.BLACK, 30);
        Text balls=new Text();

        scoreT.setTranslateX(520);
        scoreT.setTranslateY(80);

        score.setTranslateX(520);
        score.setTranslateY(120);

        balls.setTranslateX(520);
        balls.setTranslateY(200);

        score.textProperty().bind(getGameState().intProperty("Score").asString());
        balls.textProperty().bind(getGameState().intProperty("Balls").asString());
        getGameScene().addUINodes(scoreT, score, balls);
    }

    @Override
    protected void initInput(){
        Input input=getInput();
        input.addAction(new UserAction("Hit with LeftFlipper"){
            @Override
            protected void onAction(){
                leftFlipperHit =true;
            }
        },KeyCode.A);

        input.addAction(new UserAction("Hit with RightFlipper"){
            @Override
            protected void onAction(){
                rightFlipperHit =true;
            }
        },KeyCode.D);

        input.addAction(new UserAction("New ball"){
            @Override
            protected void onAction() {
                if (isBallRemoved){
                    Entity ball = newBall(200, 200);
                    getGameWorld().addEntity(ball);
                    isBallRemoved=false;
                }
            }
        },KeyCode.SPACE);

        input.addAction(new UserAction("New Table"){
            @Override
            protected void onAction() {
                if (game.isPlayableTable()){
                    getGameWorld().removeEntities(getGameWorld().getEntitiesByType(Types.HITTABLE_BUMPER));
                    getGameWorld().removeEntities(getGameWorld().getEntitiesByType(Types.HITTABLE_TARGET));
                }
                Table pinballTable=new PinballTable("Generic", 6, 0.5, 3, 2);
                game.setGameTable(pinballTable);
                for(Bumper bumper: game.getBumpers()){
                    if(bumper.isKickerBumper()){
                        double x=Math.random()*450;
                        double y=Math.random()*460;
                        Entity k=newKickBumper(x,y,bumper);
                        getGameWorld().addEntity(k);
                    }
                    else{

                        double x=Math.random()*465;
                        double y=Math.random()*460;
                        Entity p=newPopBumper(x,y,bumper);
                        getGameWorld().addEntity(p);
                    }
                }
                for(SpotTarget spotTarget: game.getSpotTargets()){
                    double x=Math.random()*480;
                    double y=Math.random()*480;
                    Entity s=newSpotTarget(x,y,spotTarget);
                    getGameWorld().addEntity(s);
                }
                for(DropTarget dropTarget: game.getDropTargets()){
                    double x=Math.random()*480;
                    double y=Math.random()*480;
                    Entity s=newDropTarget(x,y,dropTarget);
                    getGameWorld().addEntity(s);
                }
            }
        },KeyCode.N);
    }

    protected void initPhysics() {
        getPhysicsWorld().setGravity(0,0);
        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(Types.BALL, Types.WALL) {
                    @Override
                    protected void onHitBoxTrigger(Entity ball, Entity wall, HitBox boxBall, HitBox boxWall) {
                        if (boxWall.getName().equals("BOT")) {
                            Body body = ball.getComponent(PhysicsComponent.class).getBody();
                            ball.removeComponent(PhysicsComponent.class);
                            getMasterTimer().runOnceAfter(() -> {
                                getPhysicsWorld().getJBox2DWorld().destroyBody(body);
                            }, Duration.seconds(0.1));
                            getGameWorld().removeEntity(ball);
                            getGameState().increment("Balls",-1);
                            isBallRemoved=true;
                        }
                    }
                });
        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(Types.HITTABLE_BUMPER, Types.BALL) {
                    @Override
                    protected void onHitBoxTrigger(Entity hittable, Entity balls, HitBox boxHittable, HitBox ball){
                        Bumper h=(Bumper) hittable.getComponent(HittableComponent.class).getHittable();
                        h.hit();
                        getGameState().increment("Score",game.getCurrentScore());
                        if(h.isUpgraded()){
                            if(h.isKickerBumper()){
                                hittable.setView(new Circle(20,Color.LIGHTSALMON));
                            }
                            else{
                                hittable.setView(new Circle(20,Color.DEEPPINK));
                            }
                        }
                    }
                });
    getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(Types.HITTABLE_TARGET, Types.BALL) {
                    @Override
                    protected void onHitBoxTrigger(Entity hittable, Entity balls, HitBox boxHittable, HitBox ball){
                        Target h=(Target) hittable.getComponent(HittableComponent.class).getHittable();
                        h.hit();
                        getGameState().increment("Score",game.getCurrentScore());
                        if(!h.isActive()){
                            hittable.setView(new Rectangle(25,15, Color.BLACK));
                        }
                    }
                }
    );
}

    @Override
    protected void onUpdate(double tpf){
        Entity left=getGameWorld().getEntitiesByType(Types.PLAYER).get(0);
        Entity right=getGameWorld().getEntitiesByType(Types.PLAYER).get(1);
        double rotL =left.getRotation();
        double rotR=right.getRotation();
        if(leftFlipperHit){
            if(rotL>-50){
                left.getComponent(PhysicsComponent.class).setAngularVelocity(-10);
            }
            else{
                left.getComponent(PhysicsComponent.class).setAngularVelocity(0);
                leftFlipperHit =false;
                leftFlipperBack=true;
            }
        }
        if(leftFlipperBack){
            if(rotL<0) {
                left.getComponent(PhysicsComponent.class).setAngularVelocity(10);
            }
            else{
                left.getComponent(PhysicsComponent.class).setAngularVelocity(0);
                leftFlipperBack=false;
            }
        }

        if(rightFlipperHit){
            if(rotR<50){
                right.getComponent(PhysicsComponent.class).setAngularVelocity(10);
            }
            else{
                right.getComponent(PhysicsComponent.class).setAngularVelocity(0);
                rightFlipperHit =false;
                rightFlipperBack =true;
            }
        }
        if(rightFlipperBack){
            if(rotR>0) {
                right.getComponent(PhysicsComponent.class).setAngularVelocity(-10);
            }
            else{
                right.getComponent(PhysicsComponent.class).setAngularVelocity(0);
                rightFlipperBack =false;
            }
        }
    }


    public static void main(String args[]){
        launch(args);
    }
}
