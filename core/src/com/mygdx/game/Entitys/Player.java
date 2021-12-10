package com.mygdx.game.Entitys;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Components.PlayerController;
import com.mygdx.game.Components.Renderable;
import com.mygdx.game.EntityManager;
import com.mygdx.game.RenderLayer;

public class Player extends Entity {
    public Player(){
        super();
    }
    public Player(int id, float speed){
        super(2);
        Renderable r = new Renderable(id, RenderLayer.One);
        addComponent(r);
        PlayerController pc = new PlayerController(this, speed);
        addComponent(pc);
    }

    @Override
    public void cleanUp() {
        super.cleanUp();
    }


    public Vector2 getPos(){
        return getComponent(Renderable.class).getPosition();
    }
    public void setPos(Vector2 pos){
        getComponent(Renderable.class).setPosition(pos);
    }
}
