package com.mygdx.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public final class Constants {

    public static void INIT_CONSTANTS() {
        SCREEN_WIDTH = Gdx.graphics.getWidth();
        SCREEN_HEIGHT = Gdx.graphics.getHeight();
        FULLSCREEN = true;
        ASPECT_RATIO = !FULLSCREEN ? 1.0f / 1.0f : (float)SCREEN_WIDTH / (float)SCREEN_HEIGHT;
        VIEWPORT_HEIGHT = !FULLSCREEN ? 1080 : SCREEN_HEIGHT;
        VIEWPORT_WIDTH = !FULLSCREEN ? (int)(ASPECT_RATIO * VIEWPORT_HEIGHT) : SCREEN_WIDTH;
        HALF_VIEWPORT_HEIGHT = VIEWPORT_WIDTH / 2;
        HALF_VIEWPORT_WIDTH = VIEWPORT_HEIGHT / 2;
        DIMENTIONS = new Vector2(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        HALF_DIMENTIONS = new Vector2(HALF_VIEWPORT_WIDTH, HALF_VIEWPORT_HEIGHT);
        VIEWPORT_TITLE = "Jimbo Mc.Fimbo";
        BACKGROUND_COLOUR = new Vector3(0.5f, 0.5f, 0.5f);
    }

    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;
    public static boolean FULLSCREEN;
    public static float ASPECT_RATIO;
    public static int VIEWPORT_HEIGHT;
    public static int VIEWPORT_WIDTH;
    public static int HALF_VIEWPORT_HEIGHT;
    public static int HALF_VIEWPORT_WIDTH;
    public static Vector2 DIMENTIONS;
    public static Vector2 HALF_DIMENTIONS;
    public static String VIEWPORT_TITLE;

    public static Vector3 BACKGROUND_COLOUR;
}
