package com.mygdx.game.Managers;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Physics.CollisionCallBack;

public class CollisionManager implements ContactListener {
    private static boolean initialized = false;

    public CollisionManager() {
        if(initialized){
            throw new RuntimeException("Collision manager cant be instantiated more then once");
        }
        initialized = true;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture f = contact.getFixtureA();
        Body b = f.getBody();
        Object o = b.getUserData();
        CollisionCallBack cbA = (CollisionCallBack) o;
        cbA.BeginContact();

        CollisionCallBack cbB = (CollisionCallBack) contact.getFixtureB().getBody().getUserData();
        cbB.BeginContact();
    }

    @Override
    public void endContact(Contact contact) {
        CollisionCallBack cbA = (CollisionCallBack) contact.getFixtureA().getBody().getUserData();
        cbA.EndContact();

        CollisionCallBack cbB = (CollisionCallBack) contact.getFixtureB().getBody().getUserData();
        cbB.EndContact();
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
