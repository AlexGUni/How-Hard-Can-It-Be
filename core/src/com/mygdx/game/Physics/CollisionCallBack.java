package com.mygdx.game.Physics;

public interface CollisionCallBack {
    /**
     * Called once a collision has being notices
     */
    void BeginContact();

    /**
     * Called after the collision has being solved
     */
    void EndContact();
}
