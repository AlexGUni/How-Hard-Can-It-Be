package com.mygdx.game.Components;

import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.utils.Utilities;

/**
 * Position, Scale, Rotation (in radians clockwise)
 */
public class Transform extends Component implements Location<Vector2> {
    private final Vector2 position;
    private final Vector2 scale;
    private float rotation;

    public Transform() {
        position = new Vector2();
        scale = new Vector2(1, 1);
        rotation = 0;
        type = ComponentType.Transform;
    }

    public void setPosition(Vector2 pos, boolean rb) {
        setPosition(pos.x, pos.y, rb);
    }

    public void setPosition(float x, float y, boolean rb_) {
        position.set(x, y);
        if (parent != null && rb_) {
            RigidBody rb = parent.getComponent(RigidBody.class);
            if (rb != null) {
                rb.setPosition(position);
            }
        }
    }

    public void setPosition(Vector2 pos) {
        setPosition(pos.x, pos.y);
    }

    public void setPosition(float x, float y) {
        position.set(x, y);
        if (parent != null) {
            RigidBody rb = parent.getComponent(RigidBody.class);
            if (rb != null) {
                rb.setPosition(position, true);
            }
        }
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
    public void setRotation(float rot) {
        rotation = rot;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getCenter() {
        RigidBody rb = parent.getComponent(RigidBody.class);
        if (rb == null) {
            return getPosition();
        }
        return rb.getBody().getWorldCenter();
    }

    // For Ai Navigation

    public void getLocation() {

    }

    @Override
    public float getOrientation() {
        return 0;
    }

    @Override
    public void setOrientation(float orientation) {

    }

    @Override
    public float vectorToAngle(Vector2 vector) {
        return Utilities.vectorToAngle(vector);
    }

    @Override
    public Vector2 angleToVector(Vector2 outVector, float angle) {
        return Utilities.angleToVector(outVector, angle);
    }

    @Override
    public Location<Vector2> newLocation() {
        return new Transform();
    }

    public Vector2 getScale() {
        return scale;
    }

    /**
     * @return radians
     */
    public float getRotation() {
        return rotation;
    }
}
