package com.mygdx.game.Entitys;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Components.BoundingBox;
import com.mygdx.game.Components.PlayerController;
import com.mygdx.game.Components.Renderable;
import com.mygdx.game.Components.Transform;
import com.mygdx.game.Managers.RenderLayer;

public class Player extends Entity {
    public Player(){
        super();
    }

    /**
     * The player
     * @param id sprite texture id
     * @param speed speed of movement
     */
    public Player(int id, float speed){
        super(4);

        Transform t = new Transform();
        addComponent(t);

        Renderable r = new Renderable(id, RenderLayer.Transparent);
        addComponent(r);

        BoundingBox bb = new BoundingBox(r, t);
        addComponent(bb);

        PlayerController pc = new PlayerController(this, speed);
        addComponent(pc);
    }

    @Override
    public void cleanUp() {
        super.cleanUp();
    }


    public Vector2 getPos(){
        return getComponent(Transform.class).getPosition();
    }
    public void setPos(Vector2 pos){
        getComponent(Transform.class).setPosition(pos);
    }
}
