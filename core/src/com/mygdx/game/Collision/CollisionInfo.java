package com.mygdx.game.Collision;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Entitys.Entity;

public class CollisionInfo {
    public Collidable collidable1;
    public Collidable collidable2;
    public Vector2 collisionNormal;
    public float intersectionDepth;
    public Entity object1;
    public Entity object2;
    public boolean collided;
    public CollisionType collisionType;

    public CollisionInfo() {
        collided = false;
        collisionNormal = new Vector2();
        intersectionDepth = 0;
        collidable1 = null;
        collidable2 = null;
        object1 = null;
        object2 = null;
        collisionType = CollisionType.Unknown;
    }
}
