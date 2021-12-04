package com.mygdx.game.Components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Renderable extends Component {
    private Sprite sprite;
    public Renderable(){
        super();
        type = ComponentType.Renderable;
        sprite = new Sprite();
    }

    /**
     * Calls the empty constructor and asigns the texture to the sprite
     * @param image the texture that the sprite will take on
     */
    public Renderable(Texture image) {
        this();
        sprite = new Sprite(image); // TODO: don't call the constructor
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
