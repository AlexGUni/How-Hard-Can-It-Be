package com.mygdx.utils;

import com.badlogic.gdx.math.Vector2;

public final class Utilities {
    public static float vectorToAngle (Vector2 v) {
        return (float) Math.atan2(-v.x, v.y);
    }
    public static Vector2 angleToVector(Vector2 out, float angle) {
        out.x = -(float)Math.sin(angle);
        out.y = (float)Math.cos(angle);
        return out;
    }
}
