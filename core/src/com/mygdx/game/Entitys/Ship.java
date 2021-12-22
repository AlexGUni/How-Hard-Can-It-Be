package com.mygdx.game.Entitys;

import com.mygdx.game.Components.*;
import com.mygdx.game.Managers.RenderLayer;
import com.mygdx.game.Physics.PhysicsBodyType;

public class Ship extends Entity {
    private static int shipCount = 0;
    public Ship() {
        super(5);
        setName("Ship (" + shipCount++ + ")");
        Transform t = new Transform();
        Renderable r = new Renderable(5, "yellow-up", RenderLayer.Transparent);
        RigidBody rb = new RigidBody(PhysicsBodyType.Dynamic, r, t);
        Living l = new Living();
        Pirate p = new Pirate();

        addComponents(t, r, rb, l, p);
    }
}
