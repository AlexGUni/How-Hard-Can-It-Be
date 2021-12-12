package com.mygdx.game.Entitys;

import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Components.*;
import com.mygdx.game.Managers.RenderLayer;
import com.mygdx.game.Physics.PhysicsBodyType;

public class Enemy extends Entity {
    public Enemy() {
        super(4);

        Transform t = new Transform();
        t.setPosition(64, 64);
        addComponent(t);

        Renderable r = new Renderable(1, RenderLayer.Five);
        addComponent(r);

        RigidBody bb = new RigidBody(PhysicsBodyType.Dynamic, r, t);
        addComponent(bb);

        Text text = new Text(4, new Vector3(1, 0, 0));
        text.setText("Beta Male No.1");
        addComponent(text);
    }
}
