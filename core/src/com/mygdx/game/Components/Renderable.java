package com.mygdx.game.Components;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Managers.RenderLayer;
import com.mygdx.game.Managers.RenderingManager;
import com.mygdx.game.Managers.ResourceManager;

/**
 * Add the ability for the object to be shown and positioned
 */
public class Renderable extends Component {
    protected Sprite sprite;
    public Renderable(){
        super();
        type = ComponentType.Renderable;
    }

    /**
     * Calls default constructor
     * @param texId the id of the texture the sprite will take on
     * @param layer the rendering layer
     */
    public Renderable(int texId, RenderLayer layer) {
        this();
        sprite = new Sprite(ResourceManager.getTexture(texId));
        RenderingManager.addItem(this, layer);
    }
    public Renderable(int atlasId, String texName, RenderLayer layer) {
        this();
        sprite = new Sprite(ResourceManager.getSprite(atlasId, texName));
        RenderingManager.addItem(this, layer);
    }

    @Override
    public void update() {
        super.update();
        if(sprite == null){
            return;
        }
        Transform c = parent.getComponent(Transform.class);
        if(c == null){
            return;
        }
        Vector2 p = c.getPosition();
        Vector2 s = c.getScale();

        sprite.setPosition(p.x, p.y);
        sprite.setRotation(MathUtils.radiansToDegrees * c.getRotation());
        sprite.setScale(s.x, s.y);
    }

    @Override
    public void render() {
        super.render();
        if(sprite == null){
            return;
        }
        sprite.draw(RenderingManager.getBatch());
    }

    @Override
    public void cleanUp() {
        super.cleanUp();
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setTexture(Sprite s) {
        Sprite a = getSprite();
        a.setU(s.getU());
        a.setV(s.getV());
        a.setU2(s.getU2());
        a.setV2(s.getV2());
        a.setTexture(s.getTexture());
    }
}
