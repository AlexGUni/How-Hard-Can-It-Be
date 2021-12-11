package com.mygdx.game.Components;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.Managers.RenderLayer;
import com.mygdx.game.Managers.RenderingManager;
import com.mygdx.utils.ResourceManager;

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

    public TileMap(int id, RenderLayer layer) {
        this();
        map = ResourceManager.getTileMap(id);
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
    }
}
