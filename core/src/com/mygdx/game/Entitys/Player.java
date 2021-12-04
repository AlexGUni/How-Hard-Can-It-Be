package com.mygdx.game.Entitys;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Components.ComponentType;
import com.mygdx.game.Components.Renderable;

public class Player extends Entity {
    public Player(){
        super();
    }
    public Player(String fPath){
        super();
        Renderable r = new Renderable(fPath);
        this.addComponent(r);
    }
    public Player(int id){
        super();
        Renderable r = new Renderable(id);
        this.addComponent(r);
    }

    @Override
    public void cleanUp() {
        super.cleanUp();
    }

    /*public void draw(){
        this.getComponent(ComponentType.Renderable).render(b);
    }*/

    public Vector2 getPos(){
        return getComponent(Renderable.class).getPosition();
    }
    public void setPos(Vector2 pos){
        getComponent(Renderable.class).setPosition(pos);
    }
}
