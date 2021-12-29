package com.mygdx.game.Managers;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Entitys.Entity;
import com.mygdx.game.Physics.CollisionCallBack;
import com.mygdx.game.Physics.CollisionInfo;

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


        final CollisionInfo info = new CollisionInfo();
        info.fA = fa;
        info.fB = fb;

        info.bA = ba;
        info.bB = bb;

        info.a = (Entity) cbA;
        info.b = (Entity) cbB;

        if (cbA != null){
            if (fa.isSensor() && cbB != null) {
                cbB.EnterTrigger(info);
            }
            else {
                cbA.BeginContact(info);
            }
        }

        if(cbB != null) {
            if(fb.isSensor() && cbA != null) {
                cbA.EnterTrigger(info);
            }
            else {
                cbB.BeginContact(info);
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

        final CollisionInfo info = new CollisionInfo();
        info.fA = fa;
        info.fB = fb;

        info.bA = ba;
        info.bB = bb;

        info.a = (Entity) cbA;
        info.b = (Entity) cbA;

        if (cbA != null){
            if (fa.isSensor() && cbB != null) {
                cbB.ExitTrigger(info);
            }
            else {
                cbA.EndContact(info);
            }
        }

        if(cbB != null) {
            if(fb.isSensor() && cbA != null) {
                cbA.ExitTrigger(info);
            }
            else {
                cbB.EndContact(info);
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
