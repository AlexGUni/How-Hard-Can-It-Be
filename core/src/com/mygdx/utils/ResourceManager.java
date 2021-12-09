package com.mygdx.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.ArrayList;

/**
 * Manages all assets and disposes of them when appropriate
 */
public final class ResourceManager {
    private static boolean inited = false;
    private static boolean loaded;
    private static AssetManager manager;
    private static ArrayList<String> ids;
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
        ids = new ArrayList<>();
    }

    /**
     * Schedules an asset for loading
     * @param fPath the file path of the asset
     * @return returns the id of the asset can be used in place of the name;
     */
    public static int addTexture(String fPath){
        tryInit();
        checkAdd();
        manager.load(fPath, Texture.class);
        ids.add(fPath);
        return ids.size();
    }

    /**
     * Schedules an asset for loading
     * @param fPath the file path of the asset
     * @return returns the id of the asset can be used in place of the name;
     */
    public static int addTextureAtlas(String fPath){
        tryInit();
        checkAdd();
        manager.load(fPath, TextureAtlas.class);
        ids.add(fPath);
        return ids.size();
    }

    /**
     * Actually loads the assets
     */
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

    public static Texture getTexture(int id){
        tryInit();
        String fPath = ids.get(id - 1);
        return manager.get(fPath);
    }
    public static TextureAtlas getTextureAtlas(int id){
        tryInit();
        String fPath = ids.get(id - 1);
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
