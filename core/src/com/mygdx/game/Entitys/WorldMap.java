package com.mygdx.game.Entitys;

import com.mygdx.game.Components.TileMap;

public class WorldMap extends Entity{
    public WorldMap() {
        super();
    }

    public WorldMap(String fPath) {
        this();
        TileMap map = new TileMap(fPath);
        addComponent(map);
    }


}
