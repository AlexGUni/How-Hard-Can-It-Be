package com.mygdx.game.Components;

import com.mygdx.game.Managers.EntityManager;

/**
 * Base class for the Components
 */
public abstract class Component {
    protected ComponentType type;

    protected Component(){
        type = ComponentType.Unknown;
        EntityManager.addComponent(this);
    }

    public ComponentType getType(){
        return type;
    }

    /**
     * Called once before start prior to the update loop.
     */
    public void awake() {

    }

    /**
     * Called once after awake but prior to the update loop.
     */
    public void start() {

    }

    /**
     * Called once after the update loop has finished.
     */
    public void cleanUp() {

    }

    /**
     * Called once per frame
     */
    public void update() {

    }

    public void onKeyUp() {

    }
    public void onKeyDown() {

    }
    public void onMouseMove() {

    }

    /**
     * Called once per frame used exclusively for rendering
     */
    public void render() {

    }
}
