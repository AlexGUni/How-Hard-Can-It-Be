package com.mygdx.game.Components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.game.Managers.PhysicsManager;
import com.mygdx.game.Physics.CollisionCallBack;
import com.mygdx.game.Physics.PhysicsBodyType;

public class RigidBody extends Component implements CollisionCallBack {
    int bodyId;
    PhysicsBodyType bodyType;
    public RigidBody() {
        super();
        type = ComponentType.RigidBody;
    }

    public RigidBody(PhysicsBodyType type, Renderable r, Transform t){
        bodyType = type;
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
        def.position.set(t.getPosition().x, t.getPosition().y);
        def.angle = t.getRotation();

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(r.sprite.getWidth(), r.sprite.getHeight());

        FixtureDef f = new FixtureDef();
        f.shape = shape;
        f.density = type == PhysicsBodyType.Static ? 0.0f : 1.0f;

        bodyId = PhysicsManager.createBody(def, f);

        shape.dispose();
    }

    public void setVelocity(Vector2 vel){
        PhysicsManager.getBody(bodyId).setLinearVelocity(vel);
    }

    @Override
    public void update() {
        super.update();
        parent.getComponent(Transform.class).setPosition(PhysicsManager.getBody(bodyId).getPosition());
    }

    @Override
    public void BeginContact() {

    }

    @Override
    public void EndContact() {

    }
}
