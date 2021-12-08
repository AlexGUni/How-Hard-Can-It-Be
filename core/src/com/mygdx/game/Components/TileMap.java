package com.mygdx.game.Components;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.mygdx.game.EntityManager;

public class TileMap extends Component {
    TiledMap map;
    TiledMapRenderer renderer;
    private TileMap(){
        super();
        type = ComponentType.TileMap;
    }

    public TileMap(String fPath) {
        this();
        map = new TmxMapLoader().load(fPath);
        renderer = new OrthoCachedTiledMapRenderer(map);
    }

    @Override
    public void update() {
        super.update();
        renderer.setView(EntityManager.getCamera());
    }

    @Override
    public void render() {
        super.render();
        renderer.render();
    }

    @Override
    public void cleanUp() {
        super.cleanUp();
        map.dispose();
    }
}
