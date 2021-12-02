package com.mygdx.utils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public final class Constants {
    public final static float ASPECT_RATIO = 1.0f / 1.0f;
    public final static int VIEWPORT_HEIGHT = 1080;
    public final static int VIEWPORT_WIDTH = (int)(ASPECT_RATIO * VIEWPORT_HEIGHT);
    public static final int HALF_VIEWPORT_HEIGHT = VIEWPORT_WIDTH / 2;
    public static final int HALF_VIEWPORT_WIDTH = VIEWPORT_HEIGHT / 2;
    public final static Vector2 DIMENTIONS = new Vector2(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
    public final static Vector2 HALF_DIMENTIONS = new Vector2(HALF_VIEWPORT_WIDTH, HALF_VIEWPORT_HEIGHT);
    public final static String VIEWPORT_TITLE = "Jimbo Mc.Fimbo";

    public final static Vector3 BACKGROUND_COLOUR = new Vector3(0.5f, 0.5f, 0.5f);
}
