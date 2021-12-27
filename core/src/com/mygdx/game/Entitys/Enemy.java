package com.mygdx.game.Entitys;

import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Components.*;
import com.mygdx.game.Managers.EntityManager;
import com.mygdx.game.Managers.RenderLayer;
import com.mygdx.game.Physics.PhysicsBodyType;

public class Enemy extends Ship {
    public Enemy() {
        super();

        setName("Enemy");

        Text text = new Text(new Vector3(1, 0, 0));
        text.setText(getName());
        addComponent(text);

        AINavigation nav = new AINavigation();

        Entity p = EntityManager.getEntity("Player");

        Arrive<Vector2> arrives = new Arrive<>(nav, p.getComponent(Transform.class))
                .setTimeToTarget(0.01f)
                .setArrivalTolerance(16f)
                .setDecelerationRadius(32);

        nav.setBehavior(arrives);

        addComponent(nav);
    }
}
