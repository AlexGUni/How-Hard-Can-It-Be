package com.mygdx.game.Components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.EntityManager;
import com.mygdx.game.RenderLayer;
import com.mygdx.game.RenderingManager;

import java.nio.channels.spi.SelectorProvider;

/**
 * Component that allows the rendering of tilemaps (has its own sprite batch)
 */
public class TileMap extends Component {
    TiledMap map;
    TiledMapRenderer renderer;
    private TileMap(){
        super();
        type = ComponentType.TileMap;
    }

    public TileMap(String fPath, RenderLayer layer) {
        this();
        map = new TmxMapLoader().load(fPath);
        renderer = new OrthogonalTiledMapRenderer(map);
        RenderingManager.addItem(this, layer);
    }

    @Override
    public void update() {
        super.update();
        renderer.setView(RenderingManager.getCamera());
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
