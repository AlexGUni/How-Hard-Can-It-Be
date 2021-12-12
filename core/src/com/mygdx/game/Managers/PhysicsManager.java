package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Components.RigidBody;

import java.util.ArrayList;

public final class PhysicsManager {
    public static boolean initialized = false;
    private static World box2DWorld;
    private static ArrayList<Body> box2DBodies;

    public static void Initialize() {
        if(initialized){
            return;
        }
        initialized = true;
        box2DWorld = new World(new Vector2(0, 0), true);
        box2DBodies = new ArrayList<>();
        box2DWorld.setContactListener(new CollisionManager());
    }

    public static int createBody(BodyDef bDef, FixtureDef fDef){
        tryInit();
        Body b = box2DWorld.createBody(bDef);
        b.createFixture(fDef);
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
    }

    public static void cleanUp(){
        tryInit();
        box2DWorld.dispose();
    }
}
