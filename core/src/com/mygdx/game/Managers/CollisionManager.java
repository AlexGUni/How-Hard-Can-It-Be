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
        Fixture fa = contact.getFixtureA();
        Body ba = fa.getBody();
        Object oa = ba.getUserData();
        CollisionCallBack cbA = (CollisionCallBack) oa;

        Fixture fb = contact.getFixtureB();
        Body bb = fb.getBody();
        Object ob = bb.getUserData();
        CollisionCallBack cbB = (CollisionCallBack) ob;

        if (cbA != null){
            if (fa.isSensor() && cbB != null) {
                cbB.EnterTrigger();
            }
            else {
                cbA.BeginContact();
            }
        }

        if(cbB != null) {
            if(fb.isSensor() && cbA != null) {
                cbA.EnterTrigger();
            }
            else {
                cbB.BeginContact();
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Body ba = fa.getBody();
        Object oa = ba.getUserData();
        CollisionCallBack cbA = (CollisionCallBack) oa;

        Fixture fb = contact.getFixtureB();
        Body bb = fb.getBody();
        Object ob = bb.getUserData();
        CollisionCallBack cbB = (CollisionCallBack) ob;

        if (cbA != null){
            if (fa.isSensor() && cbB != null) {
                cbB.ExitTrigger();
            }
            else {
                cbA.EndContact();
            }
        }

        if(cbB != null) {
            if(fb.isSensor() && cbA != null) {
                cbA.ExitTrigger();
            }
            else {
                cbB.EndContact();
            }
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
