package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Components.ComponentType;
import com.mygdx.game.Components.Renderable;
import org.w3c.dom.Text;

public class Player extends Entity {
    private Texture texture;
    public Player(){
        super();
    }
    public Player(String fPath){
        super();
        texture = new Texture(fPath);
        Renderable r = new Renderable(texture);
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
}
