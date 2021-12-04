package com.mygdx.game.Components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Components.ComponentType;

public abstract class Component {
    protected ComponentType type;
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

    public void onKeyboard() {

    }
    public void onMouse() {

    }

    /**
     * Called once per frame used exclusively for rendering
     */
    public void render(SpriteBatch batch) {

    }

    /**
     * Called at fixed time steps (Not implemented)
     */
    // public abstract void fixedUpdate();
}
