package com.mygdx.game.Components;

import com.badlogic.gdx.math.Vector2;

/**
 * Position, Scale, Rotation (in radians clockwise)
 */
public class Transform extends Component {
    private final Vector2 position;
    private final Vector2 scale;
    private float rotation;
    public Transform() {
        position = new Vector2();
        scale = new Vector2(1, 1);
        rotation = 0;
        type = ComponentType.Transform;
    }

    public void setPosition(Vector2 pos) {
        position.set(pos);
    }
    public void setPosition(float x, float y) {
        position.set(x, y);
    }

    public void setScale(Vector2 pos) {
        scale.set(pos);
    }
    public void setScale(float x, float y) {
        scale.set(x, y);
    }

    /**
     * @param rot in Radians
     */
    public void setRotation(float rot){
        rotation = rot;
    }

    public Vector2 getPosition(){
        return position;
    }
    public Vector2 getScale(){
        return scale;
    }

    /**
     * @return radians
     */
    public float getRotation(){
        return rotation;
    }
}
