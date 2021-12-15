package com.mygdx.game.Managers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Components.RigidBody;
import com.mygdx.game.Components.TileMap;

import java.util.ArrayList;

import static com.mygdx.utils.Constants.PHYSICS_TIME_STEP;

// TODO: Tweak Physics settings
// TODO: Map collision

/**
 * Manages the box2D world and bodies for the collision detection and physics
 */
public final class PhysicsManager {
    public static boolean initialized = false;
    private static World box2DWorld;
    private static ArrayList<Body> box2DBodies;
    private static Box2DDebugRenderer debug;

    public static void Initialize() {
        Initialize(false);
    }

    public static void Initialize(boolean drawDebug) {
        if(initialized){
            return;
        }
        initialized = true;
        box2DWorld = new World(new Vector2(0, 0), true);
        box2DBodies = new ArrayList<>();
        box2DWorld.setContactListener(new CollisionManager());
        if(drawDebug){
            debug = new Box2DDebugRenderer(true, false, true, true, false, true);
        }
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

    public static int createBody(TileMap map) {
        tryInit();

        BodyDef def = new BodyDef();
        def.fixedRotation = true;
        Body b = box2DWorld.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(map.getTileDim().x, map.getTileDim().y);

        FixtureDef f = new FixtureDef();
        f.shape = shape;
        f.density = 0;

        b.createFixture(f);
        b.setUserData(map);
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
        box2DWorld.step(PHYSICS_TIME_STEP, 6, 2);

        if(debug != null){
            debug.render(box2DWorld, RenderingManager.getCamera().combined);
        }
    }

    public static void cleanUp(){
        tryInit();
        box2DWorld.dispose();
        if(debug != null) {
            debug.dispose();
        }
    }
}
