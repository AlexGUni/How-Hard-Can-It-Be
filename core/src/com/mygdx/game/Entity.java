package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/*
in regard to implementing smooth player movement the keyboard processing may have to processed in the update loop,
rather than the keyboard event
 */

/**
 * The base class for all entities in the game.
 * I am calling an entity pretty much anything that the user sees or interacts with except the UI.
 * However, there is over head with this class fo in some cases it's better to just use raw sprites
 */
public abstract class Entity {
    /**
     * The sprite contains the image, scale, rotation and position.
     */
    protected Sprite sprite;
    public Entity(){
        sprite = new Sprite();
    }
    /**
     * Called once per frame use to process all keyboard processing.
     * sudo event
     */
    public void onKeyboard(){

    }

    /**
     * Called once per frame, use to process all mouse actions (may split into mouse buttons and mouse movements).
     * sudo event
     */
    public void onMouse(){

    }
    /**
     * Called once per frame.
     * It is bad practice to check inputs in this func (although it may be required for some funcionality).
     */
    public void update(){

    }
    /**
     * Actually draws the sprite.
     * Called once per frame unless doing fancy stuff (not likely to be doing fancy stuff for this game).
     */
    public void render(){

    }

    public void setPosition(float x, float y){
        sprite.setPosition(x, y);
    }

    public void setPosition(Vector2 newPos){
        sprite.setPosition(newPos.x, newPos.y);
    }

    public Vector2 getPosition(float x, float y){
        return new Vector2(sprite.getX(), sprite.getY());
    }
}
