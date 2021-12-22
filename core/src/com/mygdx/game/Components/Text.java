package com.mygdx.game.Components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Managers.CollisionManager;
import com.mygdx.game.Managers.RenderLayer;
import com.mygdx.game.Managers.RenderingManager;
import com.mygdx.game.Managers.ResourceManager;

/**
 * Renders text with give font and colour.
 * Uses transform component for position but if not present then internal position
 * position is the bottom left
 */
public class Text extends Component {
    BitmapFont font;
    Vector3 fontColour;
    Vector2 position;
    Vector2 offset;
    String text;
    public Text() {
        super();
        position = new Vector2();
        offset = new Vector2();
        type = ComponentType.Text;
    }
    public Text(int font_id, Vector3 fontColour) {
        this();
        font = ResourceManager.getFont(font_id);
        this.fontColour = fontColour;
        RenderingManager.addItem(this, RenderLayer.Transparent);
    }

    /**
     * isn't used if parent has a transform component
     * @param pos pos to render the text
     */
    public void setPosition(Vector2 pos) {
        position.set(pos);
    }/**
     * isn't used if parent has a transform component
     */
    public void setPosition(float x, float y) {
        position.set(x, y);
    }

    public void setFontColour(Vector3 col) {
        fontColour.set(col);
    }

    public void setText(String text){
        this.text = text;
    }

    @Override
    public void update() {
        super.update();
        Transform t = parent.getComponent(Transform.class);
        if(t == null){
            return;
        }
        position.set(t.getPosition());
    }

    @Override
    public void render() {
        super.render();
        Color c = new Color();
        c.r = fontColour.x;
        c.g = fontColour.y;
        c.b = fontColour.z;
        c.a = 1;
        font.setColor(c);
        float x = position.x + offset.x;
        float y = position.y + font.getXHeight() * 2.f + offset.x;
        font.draw(RenderingManager.getBatch(), text, x, y);
    }

    @Override
    public void cleanUp() {
        super.cleanUp();
    }
}
