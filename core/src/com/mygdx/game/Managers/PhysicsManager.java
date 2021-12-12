package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Components.RigidBody;

import java.util.ArrayList;

public final class PhysicsManager {
    public static boolean initialized = false;
    private static World box2DWorld;
    private static ArrayList<Body> box2DBodies;
    private static Box2DDebugRenderer debug;

    public static void Initialize() {
        if(initialized){
            return;
        }
        initialized = true;
        box2DWorld = new World(new Vector2(0, 0), true);
        box2DBodies = new ArrayList<>();
        box2DWorld.setContactListener(new CollisionManager());
        debug = new Box2DDebugRenderer(true, false, true, true, false, true);
    }

    public static int createBody(BodyDef bDef, FixtureDef fDef, RigidBody rb){
        tryInit();
        bDef.fixedRotation = true;
        Body b = box2DWorld.createBody(bDef);
        b.createFixture(fDef);
        b.setUserData(rb);
        box2DBodies.add(b);
        return box2DBodies.size();
    }

    public static Body getBody(int id) {
        return box2DBodies.get(id - 1);
    }

    private static void tryInit() {
        if(!initialized) {
            Initialize();
        }
    }

    public static void update() {
        tryInit();
        box2DWorld.step(EntityManager.getDeltaTime(), 3, 3);

        debug.render(box2DWorld, RenderingManager.getCamera().combined);
    }

    public static void cleanUp(){
        tryInit();
        box2DWorld.dispose();
    }
}
