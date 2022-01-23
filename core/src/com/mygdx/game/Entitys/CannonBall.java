package com.mygdx.game.Entitys;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Components.Renderable;
import com.mygdx.game.Components.RigidBody;
import com.mygdx.game.Components.Transform;
import com.mygdx.game.Managers.EntityManager;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.Managers.RenderLayer;
import com.mygdx.game.Physics.CollisionCallBack;
import com.mygdx.game.Physics.CollisionInfo;
import com.mygdx.game.Physics.PhysicsBodyType;

public class CannonBall extends Entity implements CollisionCallBack {
    private static float speed;
    private boolean toggleLife;

    public CannonBall() {
        super(3);
        setName("ball");
        toggleLife = false;
        Transform t = new Transform();
        t.setPosition(-100, 100);
        t.setScale(0.5f, 0.5f);
        Renderable r = new Renderable(4, "ball", RenderLayer.Transparent);
        RigidBody rb = new RigidBody(PhysicsBodyType.Dynamic, r, t, true);
        rb.setCallback(this);

        addComponents(t, r, rb);

        speed = GameManager.getSettings().get("starting").getFloat("cannonSpeed");
    }

    @Override
    public void update() {
        super.update();
        if (toggleLife) {
            Transform t = getComponent(Transform.class);
            t.setPosition(10000, 10000);

            RigidBody rb = getComponent(RigidBody.class);
            rb.setPosition(t.getPosition());
            rb.setVelocity(0, 0);
            toggleLife = false;
        }
    }

    public void fire(Vector2 pos, Vector2 dir, Ship sender) {
        Transform t = getComponent(Transform.class);
        t.setPosition(pos);

        RigidBody rb = getComponent(RigidBody.class);
        rb.setVelocity(dir.cpy().scl(speed * EntityManager.getDeltaTime()));

    }

    public void kill() {
        toggleLife = true;
    }

    @Override
    public void BeginContact(CollisionInfo info) {

    }

    @Override
    public void EndContact(CollisionInfo info) {

    }

    @Override
    public void EnterTrigger(CollisionInfo info) {

    }

    @Override
    public void ExitTrigger(CollisionInfo info) {

    }
}
