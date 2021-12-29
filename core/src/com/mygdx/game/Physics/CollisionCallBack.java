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

    /**
     * Called on the object that enters the trigger
     */
    void EnterTrigger();

    /**
     * Called upon exiting a trigger
     */
    void ExitTrigger();
}
