package gui;

import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.RenderLayer;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import logic.gameelements.Hittable;

public final class GameFactory {

    /**
     * Creates an Entity Player in (x,y)
     * It will represent a flipper
     *
     * @param x
     * @param y
     * @return
     */
    public static Entity newPlayer(double x, double y){
        PhysicsComponent physics=new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);
        return Entities.builder()
                .at(x, y)
                .type(PinBallApp.Types.PLAYER)
                .bbox(new HitBox("rectangle", BoundingShape.box(100,30)))
                .viewFromNode(new Rectangle(100,30, Color.BLUE))
                .with(physics, new CollidableComponent(true))
                .build();
    }

    /**
     * Creates an Entity InfoPanel in (x,y)
     * this entity will separate the part of the screen that shows text from the part where the game is running
     *
     * @param x
     * @param y
     * @return
     */
    public static Entity newInfoPanel(double x, double y){
        PhysicsComponent physics=new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);
        return Entities.builder()
                .at(x, y)
                .bbox(new HitBox("rectangle2", BoundingShape.box(200,600)))
                .viewFromNode(new Rectangle(1,600, Color.BLACK))
                .with(physics, new CollidableComponent(true))
                .build();
    }


    /**
     * Creates the background
     *
     * @return
     */
    public static Entity newBackground(){
        return Entities.builder()
                .viewFromNode(new Rectangle(500,600, Color.GREEN))
                .renderLayer(RenderLayer.BACKGROUND)
                .build();
    }

    public static Entity newBall(double x, double y){
        PhysicsComponent physics=new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().restitution(1f).density(0.1f).friction(0f));
        physics.setOnPhysicsInitialized(
                () -> physics.setLinearVelocity(5 * 60, -5 * 60));
        return Entities.builder()
                .at(x,y)
                .type(PinBallApp.Types.BALL)
                .bbox(new HitBox("Ball", BoundingShape.circle(10)))
                .viewFromNode(new Circle(10, Color.LIGHTGRAY))
                .with(physics, new CollidableComponent(true))
                .build();
    }

    public static Entity newWalls() {
        Entity walls = Entities.makeScreenBounds(100);
        walls.setType(PinBallApp.Types.WALL);
        walls.addComponent(new CollidableComponent(true));
        return walls;
    }

    public static Entity newPopBumper(double x, double y, Hittable h){
        Node node=new Circle(20, Color.BROWN);
        PhysicsComponent physics=new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);
        return Entities.builder()
                .at(x, y)
                .type(PinBallApp.Types.HITTABLE_BUMPER)
                .bbox(new HitBox("bumper", BoundingShape.circle(25)))
                .viewFromNode(node)
                .with(physics, new CollidableComponent(true), new HittableComponent(h))
                .with(new SoundComponent("popBumper.wav"))
                .with(new AlternateViewComponent(node, new Circle(20, Color.DEEPPINK)))
                .build();
    }

    public static Entity newKickerBumper(double x, double y, Hittable h){
        Node node=new Circle(20, Color.ORANGE);
        PhysicsComponent physics=new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);
        return Entities.builder()
                .at(x, y)
                .type(PinBallApp.Types.HITTABLE_BUMPER)
                .bbox(new HitBox("bumper", BoundingShape.circle(25)))
                .viewFromNode(node)
                .with(physics, new CollidableComponent(true), new HittableComponent(h))
                .with(new SoundComponent("kickerBumper.wav"))
                .with(new AlternateViewComponent(node, new Circle(20, Color.LIGHTSALMON)))
                .build();
    }

    public static Entity newDropTarget(double x, double y, Hittable h){
        Node node=new Rectangle(25,15, Color.RED);
        PhysicsComponent physics=new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);
        return Entities.builder()
                .at(x, y)
                .type(PinBallApp.Types.HITTABLE_TARGET)
                .viewFromNodeWithBBox(node)
                .with(physics, new CollidableComponent(true), new HittableComponent(h))
                .with(new SoundComponent("DropTarget.wav"))
                .with(new AlternateViewComponent(node, new Rectangle(25,15, Color.BLACK)))
                .build();
    }

    public static Entity newSpotTarget(double x, double y, Hittable h){
        Node node=new Rectangle(25,15, Color.GOLD);
        PhysicsComponent physics=new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);
        return Entities.builder()
                .at(x, y)
                .type(PinBallApp.Types.HITTABLE_TARGET)
                .viewFromNodeWithBBox(node)
                .with(physics, new CollidableComponent(true), new HittableComponent(h))
                .with(new SoundComponent("SpotTarget.wav"))
                .with(new AlternateViewComponent(node, new Rectangle(25,15, Color.BLACK)))
                .build();
    }

}
