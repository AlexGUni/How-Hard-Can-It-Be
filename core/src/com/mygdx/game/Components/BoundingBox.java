package com.mygdx.game.Components;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Collision.Collidable;
import com.mygdx.game.Managers.CollisionManager;

public class BoundingBox extends Component implements Collidable {
    public boolean isTrigger;
    private Vector2 max;
    private Vector2 min;

    public BoundingBox() {
        super();
        isTrigger = false;
        max = new Vector2();
        min = new Vector2();
        type = ComponentType.BoundingBox;
        CollisionManager.addBoundingBox(this);
    }

    /**
     * uses the sprites bounding box
     * @param r the source of the sprite
     * @param t source of position
     */
    public BoundingBox(Renderable r, Transform t) {
        this();
        Rectangle re = r.sprite.getBoundingRectangle();
        min = t.getPosition();
        max = new Vector2(re.getWidth(), re.getHeight()).add(min);
    }

    public BoundingBox(Vector2 min, Vector2 max, Transform t) {
        this();

        this.min = min.add(t.getPosition());
        this.max = max.add(t.getPosition());
    }

    /**
     * @return min, max
     */
    public Vector2[] getBoundingBox() {
        return new Vector2[] { min, max };
    }

    public void setBoundingBox(Vector2 min, Vector2 max) {
        this.min = min;
        this.max = max;
    }
}
