package com.mygdx.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import java.util.ArrayList;

/**
 * Manages all assets and disposes of them when appropriate
 */
public final class ResourceManager {
    private static boolean initialized = false;
    private static boolean loaded;
    private static AssetManager manager;
    private static ArrayList<String> ids;
    private static ArrayList<TiledMap> tileMaps;
    /**
     * The equivalent to a constructor
     */
    public static void Initialize() {
        if(initialized){
            return;
        }
        initialized = true;
        manager = new AssetManager();
        loaded = false;
        ids = new ArrayList<>();
        tileMaps = new ArrayList<>();
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
     * Prefaces name with |TM| followed by the internal index of the tilemap however this isn't required to access this asset
     * @param fPath the file location of the asset
     * @return id of the asset
     */
    public static int addTileMap(String fPath) {
        tryInit();
        checkAdd();
        TiledMap map = new TmxMapLoader().load(fPath);
        tileMaps.add(map);
        ids.add("|TM|" + tileMaps.size() + fPath);
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

    /**
     * Looks for fPath in ids then determines if it is a tile map and returns the corresponding map
     * @param fPath the fPath to the asset
     * @return The asset if found or null
     */
    public static TiledMap getTileMap(String fPath){
        tryInit();
        int id = -1;
        for (String fName : ids) {
            if (fName.length() < 4) {
                continue;
            }
            String slice = fName.substring(0, 4);
            if (slice.equals("|TM|") && fName.contains(fPath)) {
                id = Character.getNumericValue(fName.charAt(4));
                break;
            }
        }
        if(id < 0){
            return null;
        }
        return tileMaps.get(id - 1);
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
     * Gets the tile map returns null if not a tile map
     * @param id the id of the tile map
     * @return the tile map
     */
    public static TiledMap getTileMap(int id){
        tryInit();

        String fPath = ids.get(id - 1);
        if(fPath.length() < 4){
            return null;
        }
        String slice = fPath.substring(0, 4);
        if (!slice.equals("|TM|")) {
            return null;
        }
        int id_ = Character.getNumericValue(fPath.charAt(4));
        return tileMaps.get(id_ - 1);
    }

    /**
     * It is imperative that this is called
     */
    public static void cleanUp(){
        tryInit();
        manager.dispose();
        for (TiledMap map : tileMaps) {
            map.dispose();
        }
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
        if(!initialized){
            Initialize();
        }
    }
}
