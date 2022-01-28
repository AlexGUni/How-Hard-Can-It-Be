package com.mygdx.game.Managers;


import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.Components.ComponentEvent;

/**
 * Wrapper class for libGDX's input system. Calls functions based on keyboard and mouse inputs
 */
public class InputManager implements InputProcessor {
    public static boolean initialized = false;

    public InputManager() {
        if (initialized) {
            throw new RuntimeException("Cant have multiple instances of input manager");
        }
        initialized = true;


    }

    @Override
    public boolean keyDown(int keycode) {
        EntityManager.raiseEvents(ComponentEvent.OnKeyDown);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        EntityManager.raiseEvents(ComponentEvent.OnKeyUp);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        EntityManager.raiseEvents(ComponentEvent.OnMouseMove);
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
