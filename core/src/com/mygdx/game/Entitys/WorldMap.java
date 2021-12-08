package com.mygdx.game.Entitys;

import com.mygdx.game.Components.TileMap;

/**
 * The world map
 */
public class WorldMap extends Entity{
    public WorldMap() {
        super();
    }

    public WorldMap(String fPath) {
        super(1);
        TileMap map = new TileMap(fPath);
        addComponent(map);
    }


}
