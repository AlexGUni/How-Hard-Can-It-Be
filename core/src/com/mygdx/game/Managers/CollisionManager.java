package com.mygdx.game.Managers;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Collision.CollisionInfo;
import com.mygdx.game.Collision.CollisionType;
import com.mygdx.game.Components.BoundingBox;
import com.mygdx.game.Entitys.Entity;

import java.util.ArrayList;

public final class CollisionManager {
    private static ArrayList<BoundingBox> boundingBoxes;
    private static boolean initialized = false;
    public static void Initialize() {
        boundingBoxes = new ArrayList<>();
        initialized = true;
    }

    private static void tryInit() {
        if(!initialized){
            Initialize();
        }
    }

    public static void addBoundingBox(BoundingBox bb){
        tryInit();
        boundingBoxes.add(bb);
    }

    private static CollisionInfo checkCollision(BoundingBox bb1, BoundingBox bb2) {
        tryInit();
        CollisionInfo info = new CollisionInfo();
        Vector2[] col1 = bb1.getBoundingBox();
        Vector2[] col2 = bb2.getBoundingBox();

        Vector2 min1 = col1[0];
        Vector2 min2 = col2[1];
        Vector2 max1 = col1[0];
        Vector2 max2 = col2[1];

        info.object1 = bb1.getParent();
        info.object2 = bb2.getParent();
        info.collidable1 = bb1;
        info.collidable2 = bb2;
        info.collided =
                min1.x <= max2.x && max1.x >= min2.x &&
                min1.y <= max2.y && max1.y >= min2.y;
        info.collisionType = CollisionType.BoundingBox_BoundingBox;
        return info;
    }

    /**
     * returns all collisions O(n^2)
     */
    private static ArrayList<CollisionInfo> findCollisions() {
        tryInit();
        ArrayList<CollisionInfo> res = new ArrayList<>();
        for(int i = 0; i < boundingBoxes.size(); i++){
            for(int j = i + 1; j < boundingBoxes.size(); j++){
                BoundingBox bb1 = boundingBoxes.get(i);
                BoundingBox bb2 = boundingBoxes.get(j);
                CollisionInfo info = checkCollision(bb1, bb2);
                if(info.collided) {
                    res.add(info);
                }
            }
        }
        return res;
    }

    public static void raiseCollisionEvents() {
        ArrayList<CollisionInfo> collisions = findCollisions();

        for(CollisionInfo info : collisions) {
            Entity b1 = info.object1;
            Entity b2 = info.object2;
            if(b1 == null || b2 == null){
                continue;
            }
            switch (info.collisionType){
                case Unknown:
                    break;
                case BoundingBox_BoundingBox:
                    BoundingBox o1 = (BoundingBox) info.collidable1;
                    BoundingBox o2 = (BoundingBox) info.collidable2;
                    if(o1.isTrigger){
                        b2.onTrigger(info);
                    }
                    else{
                        b2.onCollision(info);
                    }
                    if(o2.isTrigger){
                        b1.onTrigger(info);
                    }
                    else{
                        b1.onCollision(info);
                    }
                    break;
            }
        }
    }

    /**
     * Doesn't do anything just for completeness
     */
    public static void cleanUp() {

    }
}