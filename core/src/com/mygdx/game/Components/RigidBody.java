package com.mygdx.game.Components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Managers.PhysicsManager;
import com.mygdx.game.Physics.CollisionCallBack;
import com.mygdx.game.Physics.PhysicsBodyType;

public class RigidBody extends Component {
    int bodyId;
    private final Vector2 halfDim;
    public RigidBody() {
        super();
        type = ComponentType.RigidBody;
        halfDim = new Vector2();
        setRequirements(ComponentType.Transform, ComponentType.Renderable);
    }
    public RigidBody(PhysicsBodyType type, Renderable r, Transform t) {
        this(type, r, t, false);
    }

    public RigidBody(PhysicsBodyType type, Renderable r, Transform t, boolean isTrigger){
        this();
        BodyDef def = new BodyDef();
        switch (type){
            case Static:
                def.type = BodyDef.BodyType.StaticBody;
                break;
            case Dynamic:
                def.type = BodyDef.BodyType.DynamicBody;
                break;
            case Kinematic:
                def.type = BodyDef.BodyType.KinematicBody;
                break;
        }
        final float h_x = r.sprite.getWidth() * 0.5f;
        final float h_y = r.sprite.getHeight() * 0.5f;
        halfDim.set(h_x, h_y);

        def.position.set(t.getPosition().x + h_x, t.getPosition().y + h_y);
        def.angle = t.getRotation();

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(h_x, h_y);

        FixtureDef f = new FixtureDef();
        f.isSensor = isTrigger;
        f.shape = shape;
        f.density = type == PhysicsBodyType.Static ? 0.0f : 1.0f;
        f.restitution = 0; // prevents bouncing
        f.friction = 0;

        bodyId = PhysicsManager.createBody(def, f, null);

        shape.dispose();
    }

    /**
     * Adds a new circular fixture to the body as a trigger
     */
    public void addTrigger(float radius) {
        Body b = getBody();

        FixtureDef fDef = new FixtureDef();
        fDef.isSensor = true;
        CircleShape shape = new CircleShape();
        shape.setPosition(b.getPosition());
        shape.setRadius(radius);

        fDef.shape = shape;

        fDef.density = 0.0f;
        fDef.restitution = 0.0f;
        fDef.friction = 0.0f;

        b.createFixture(fDef);
    }

    public void setCallback(CollisionCallBack data) {
        getBody().setUserData(data);
    }

    public void setVelocity(Vector2 vel){
        Body b = PhysicsManager.getBody(bodyId);
        b.setLinearVelocity(vel);
    }

    public void setPosition(Vector2 position) {
        Body b = PhysicsManager.getBody(bodyId);
        b.setTransform(position, 0);
    }

    public void setPosition(Vector2 position, boolean offset) {
        Body b = PhysicsManager.getBody(bodyId);
        if(offset){
            position.add(halfDim);
        }
        b.setTransform(position, 0);
    }

    public Body getBody() {
        return PhysicsManager.getBody(bodyId);
    }

    @Override
    public void update() {
        super.update();
        // parent.getComponent(Transform.class).setPosition(PhysicsManager.getBody(bodyId).getPosition());
        Transform t = parent.getComponent(Transform.class);
        Body b = getBody();
        Vector2 p = b.getPosition().cpy();
        p.sub(halfDim);
        t.setPosition(p, false);
    }

    public Vector2 getVelocity() {
        return getBody().getLinearVelocity();
    }

    public float getAngularVelocity() {
        return getBody().getAngularVelocity();
    }

    public void applyForce(Vector2 force) {
        getBody().applyForceToCenter(force, true);
    }
}
