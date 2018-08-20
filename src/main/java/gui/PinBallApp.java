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

/**
 * Pinball gui aplication.
 *
 * @author Jhon Salgado
 */

public class PinBallApp extends GameApplication {

    private Game game;
    private boolean isBallRemoved=true;
    private boolean isLeftFlipperActive =false;
    private boolean leftFlipperBack=false; //Indica si el flipper izquierdo debe regresar a su posicion inicial
    private boolean isRightFlipperActive =false;
    private boolean rightFlipperBack =false; //Indica si el flipper derecho debe regresar a su posicion inicial

    public enum Types{
        PLAYER, BALL, WALL, HITTABLE_BUMPER, HITTABLE_TARGET
    }

    /**
     * Initiates a window with the tittle of the Game and the specified size
     *
     * @param gameSettings
     */
    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(700);
        gameSettings.setHeight(600);
        gameSettings.setTitle("PinBall");
        gameSettings.setVersion("0.1");
    }

    /**
     * Creates and attached the basic Entities for the game, like the flippers, the walls and the background
     */
    @Override
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

    /**
     * Creates and initiates variables for save information from the state of the Game
     *
     * @param vars
     */
    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("Score", 0);
        vars.put("Balls", 3);
    }

    /**
     * Cretes and initiates the text entities and attached to the GameScene
     *
     */
    @Override
    protected void initUI() {
        Text scoreTextUI= getUIFactory().newText("Score: ",Color.BLACK, 30);
        Text scoreUI= getUIFactory().newText("", Color.BLACK, 30);
        Text ballsTextUI= getUIFactory().newText("Balls: ",Color.BLACK, 30);
        Text ballsUI= getUIFactory().newText("",Color.BLACK
                , 30);

        scoreTextUI.setTranslateX(520);
        scoreTextUI.setTranslateY(80);

        scoreUI.setTranslateX(520);
        scoreUI.setTranslateY(120);

        ballsTextUI.setTranslateX(520);
        ballsTextUI.setTranslateY(260);

        ballsUI.setTranslateX(520);
        ballsUI.setTranslateY(300);

        scoreUI.textProperty().bind(getGameState().intProperty("Score").asString());
        ballsUI.textProperty().bind(getGameState().intProperty("Balls").asString());

        getGameScene().addUINodes(scoreTextUI, scoreUI, ballsTextUI,ballsUI);
    }

    /**
     * Set the action that the game will make with specifics keys
     */
    @Override
    protected void initInput(){
        Input input=getInput();
        input.addAction(new UserAction("Hit with LeftFlipper"){
            @Override
            protected void onAction(){
                isLeftFlipperActive =true;
            }
            @Override
            protected void onActionEnd(){
                leftFlipperBack=true;
            }
        },KeyCode.A);

        input.addAction(new UserAction("Hit with RightFlipper"){
            @Override
            protected void onAction(){
                isRightFlipperActive =true;
            }
            @Override
            protected void onActionEnd(){
                rightFlipperBack=true;
            }
        },KeyCode.D);

        input.addAction(new UserAction("New ball"){
            @Override
            protected void onAction() {
                if(!game.gameOver()){
                    if (isBallRemoved){
                        Entity ball = newBall(200, 200);
                        getGameWorld().addEntity(ball);
                        isBallRemoved=false;
                    }
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
                        Entity k=newKickerBumper(x,y,bumper);
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

    /**
     * Initiates the physics components of the Game
     * and set what happen if two entities collide
     */
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
                            game.dropBall();
                            getGameState().increment("Balls",game.getAvailableBalls() - getGameState().getInt("Balls"));
                            isBallRemoved=true;
                            if(game.gameOver()){
                                Text text=getUIFactory().newText("Game Over", Color.WHITE, 50);
                                text.setTranslateX(100);
                                text.setTranslateY(100);
                                getGameScene().addUINodes(text);
                            }
                        }
                    }
                });
        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(Types.HITTABLE_BUMPER, Types.BALL) {
                    @Override
                    protected void onHitBoxTrigger(Entity hittable, Entity balls, HitBox boxHittable, HitBox ball){
                        Bumper h=(Bumper) hittable.getComponent(HittableComponent.class).getHittable();
                        h.hit();
                        getAudioPlayer().playSound(hittable.getComponent(SoundComponent.class).getAudio());
                        getGameState().increment("Score", game.getCurrentScore() - getGameState().getInt("Score"));
                        int addedBalls=game.getAvailableBalls()-getGameState().getInt("Balls");
                        if(addedBalls>0){getAudioPlayer().playSound("Bonus.wav");}
                        getGameState().increment("Balls",addedBalls);
                        if(h.isUpgraded()) hittable.setView(hittable.getComponent(AlternateViewComponent.class).getAlternativeNode());
                    }
                });
    getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(Types.HITTABLE_TARGET, Types.BALL) {
                    @Override
                    protected void onHitBoxTrigger(Entity hittable, Entity balls, HitBox boxHittable, HitBox ball){
                        Target h=(Target) hittable.getComponent(HittableComponent.class).getHittable();
                        h.hit();
                        getAudioPlayer().playSound(hittable.getComponent(SoundComponent.class).getAudio());
                        int addedScore=game.getCurrentScore()-getGameState().getInt("Score");
                        if(addedScore>=100000){
                            getAudioPlayer().playSound("Bonus.wav");
                            if(addedScore>=1000000){
                                getGameWorld().getEntitiesByType(Types.HITTABLE_BUMPER).forEach(
                                        e->e.setView(e.getComponent(AlternateViewComponent.class).getAlternativeNode())
                                );
                            }
                        }
                        getGameState().increment("Score", addedScore);
                        int addedBalls=game.getAvailableBalls()-getGameState().getInt("Balls");
                        if(addedBalls>0){getAudioPlayer().playSound("Bonus.wav");}
                        getGameState().increment("Balls",addedBalls);
                        if(!h.isActive()){
                            hittable.getComponent(SoundComponent.class).setNewAudio("DisableTarget.wav");
                            hittable.setView(hittable.getComponent(AlternateViewComponent.class).getAlternativeNode());
                        }
                        else{
                            hittable.getComponent(SoundComponent.class).setBaseAudioAsset();
                            hittable.setView(hittable.getComponent(AlternateViewComponent.class).getBaseNode());
                        }
                    }
                }
    );
}

    /**
     * Set what will the entities make everytime an action is happening
     *
     * @param tpf
     */
    @Override
    protected void onUpdate(double tpf){
        Entity left=getGameWorld().getEntitiesByType(Types.PLAYER).get(0);
        Entity right=getGameWorld().getEntitiesByType(Types.PLAYER).get(1);
        double rotL =left.getRotation();
        double rotR=right.getRotation();
        if(isLeftFlipperActive){
            if(rotL>-50){
                left.getComponent(PhysicsComponent.class).setAngularVelocity(-15);
            }
            else{
                left.getComponent(PhysicsComponent.class).setAngularVelocity(0);
                isLeftFlipperActive =false;
            }
        }
        if(leftFlipperBack){
            if(rotL<0) {
                left.getComponent(PhysicsComponent.class).setAngularVelocity(15);
            }
            else{
                left.getComponent(PhysicsComponent.class).setAngularVelocity(0);
                leftFlipperBack=false;
            }
        }

        if(isRightFlipperActive){
            if(rotR<50){
                right.getComponent(PhysicsComponent.class).setAngularVelocity(15);
            }
            else{
                right.getComponent(PhysicsComponent.class).setAngularVelocity(0);
                isRightFlipperActive =false;
            }
        }
        if(rightFlipperBack){
            if(rotR>0) {
                right.getComponent(PhysicsComponent.class).setAngularVelocity(-15);
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
