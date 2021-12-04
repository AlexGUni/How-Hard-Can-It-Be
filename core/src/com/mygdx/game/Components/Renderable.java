package com.mygdx.game.Components;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.utils.ResourceManager;

/**
 * Add the ability for the object to be shown and positioned
 */
public class Renderable extends Component {
    private Sprite sprite;
    public Renderable(){
        super();
        type = ComponentType.Renderable;
        sprite = new Sprite();
    }

    /**
     * Calls the empty constructor and assigns the texture to the sprite
     * @param fPath the fPath that will be retrieved from the Resource Manager
     */
    public Renderable(String fPath) {
        this();
        sprite = new Sprite(ResourceManager.getTexture(fPath)); // TODO: don't call the constructor
    }
    public void setPosition(float x, float y){
        sprite.setPosition(x, y);
    }
    public void setPosition(Vector2 pos){
        sprite.setPosition(pos.x, pos.y);
    }
    public Vector2 getPosition(){
        return new Vector2(sprite.getX(), sprite.getY());
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        sprite.draw(batch);
    }

    @Override
    public void cleanUp() {
        super.cleanUp();
    }
}
