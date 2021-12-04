package com.mygdx.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Manages all assets and disposes of them when appropriate
 */
public final class ResourceManager {
    private static boolean inited = false;
    private static boolean loaded;
    private static AssetManager manager;

    /**
     * The equivalent to a constructor
     */
    public static void Initialize() {
        if(inited){
            return;
        }
        inited = true;
        manager = new AssetManager();
        loaded = false;
    }

    public static void addTexture(String fPath){
        tryInit();
        checkAdd();
        manager.load(fPath, Texture.class);
    }

    public static void addTextureAtlas(String fPath){
        tryInit();
        checkAdd();
        manager.load(fPath, TextureAtlas.class);
    }

    public static void loadAssets(){
        tryInit();
        loaded = true;
        manager.finishLoading();
    }

    public static Texture getTexture(String fPath){
        tryInit();
        return manager.get(fPath);
    }
    public static TextureAtlas getTextureAtlas(String fPath){
        tryInit();
        return manager.get(fPath);
    }

    /**
     * It is imperative that this is called
     */
    public static void cleanUp(){
        tryInit();
        manager.dispose();
    }

    /**
     * Will check if new assets can be added if not throw an error
     */
    private static void checkAdd(){
        if(loaded){
            throw new RuntimeException("Can't load assets at this stage");
        }
    }
    /**
     * Calls Initialize if not already done so
     */
    private static void tryInit(){
        if(!inited){
            Initialize();
        }
    }
}
