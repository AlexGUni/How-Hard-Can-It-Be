package com.mygdx.game.Entitys;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Components.Renderable;
import com.mygdx.game.Components.RigidBody;
import com.mygdx.game.Components.Transform;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.Managers.RenderLayer;
import com.mygdx.game.Physics.PhysicsBodyType;

public class CannonBall extends Entity {
    private static float speed;
    private static Ship parent;
    public CannonBall() {
        super(3);
        parent = null;
        Transform t = new Transform();
        Renderable r = new Renderable(4, "ball", RenderLayer.Transparent);
        RigidBody rb = new RigidBody(PhysicsBodyType.Kinematic, r, t);

        addComponents(t, r, rb);

        speed = GameManager.getSettings().get("starting").getFloat("cannonSpeed");
    }

    public void fire(Vector2 pos, Vector2 dir, Ship sender) {
        Transform t = getComponent(Transform.class);
        t.setPosition(pos);

        RigidBody rb = getComponent(RigidBody.class);
        rb.setVelocity(dir.cpy().scl(speed));

        parent = sender;
    }
}
