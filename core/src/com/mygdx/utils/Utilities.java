package com.mygdx.utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import static com.mygdx.utils.Constants.TILE_SIZE;

public final class Utilities {
    public static float vectorToAngle (Vector2 v) {
        return (float) Math.atan2(-v.x, v.y);
    }
    public static Vector2 angleToVector(Vector2 out, float angle) {
        out.x = -(float)Math.sin(angle);
        out.y = (float)Math.cos(angle);
        return out;
    }

    public static float tilesToDistance(float tiles){
        return TILE_SIZE * tiles;
    }

    public static float angleBetween(Vector2 v, Vector2 w) {
        return MathUtils.atan2(w.y * v.x - w.x * v.y, w.x * v.x + w.y * v.y);
    }

    public static float scale(float x, float min0, float max0, float min1, float max1) {
        return (max1 - min1) * ((x - min0 * x) / (max0 * x - min0 * x)) + min1;
    }

    public static float scale(float x, Vector2 a, Vector2 b) {
        return (b.y - b.x) * ((x - a.x * x) / (a.y * x - a.x * x)) + b.x;
    }

    /**
     * @param x the vector to round
     * @return x modified for chaining
     */
    public static Vector2 round(Vector2 x) {
        x.x = Math.round(x.x);
        x.y = Math.round(x.y);
        return x;
    }
}
