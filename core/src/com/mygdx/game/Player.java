package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Components.ComponentType;
import com.mygdx.game.Components.Renderable;

public class Player extends Entity {
    private Texture texture;
    public Player(){
        super();
    }
    public Player(String fPath){
        super();
        Renderable r = new Renderable(fPath);
        this.addComponent(r);
    }

    @Override
    public void cleanUp() {
        super.cleanUp();
        texture.dispose();
    }

    public void draw(SpriteBatch b){
        this.getComponent(ComponentType.Renderable).render(b);
    }

    public Vector2 getPos(){
        return getComponent(Renderable.class).getPosition();
    }
    public void setPos(Vector2 pos){
        getComponent(Renderable.class).setPosition(pos);
    }
}
