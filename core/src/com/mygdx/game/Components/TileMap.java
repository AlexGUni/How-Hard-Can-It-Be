package com.mygdx.game.Components;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Managers.RenderLayer;
import com.mygdx.game.Managers.RenderingManager;
import com.mygdx.game.Physics.CollisionCallBack;
import com.mygdx.utils.ResourceManager;

/**
 * Component that allows the rendering of tilemaps (has its own sprite batch)
 */
public class TileMap extends Component implements CollisionCallBack {
    TiledMap map;
    TiledMapRenderer renderer;
    private TileMap(){
        super();
        type = ComponentType.TileMap;
        // CollisionManager.addTileMap(this);
    }

    public TileMap(int id, RenderLayer layer, boolean canCollide) {
        this();
        map = ResourceManager.getTileMap(id);
        renderer = new OrthogonalTiledMapRenderer(map);
        RenderingManager.addItem(this, layer);

        if(canCollide) {

        }
    }

    public TiledMapTileLayer.Cell getCell(Vector2 pos){
        Vector2 p = pos.cpy();
        TiledMapTileLayer l = (TiledMapTileLayer) map.getLayers().get(1);
        p.x /= l.getTileWidth();
        p.y /= l.getTileHeight();

        return l.getCell((int) p.x, (int) p.y);
    }

    public Vector2 getTileDim() {
        return new Vector2(
                ((TiledMapTileLayer) map.getLayers().get(1)).getTileWidth(),
                ((TiledMapTileLayer) map.getLayers().get(1)).getTileHeight());
    }

    public TiledMap getTileMap() {
        return map;
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

    @Override
    public void BeginContact() {

    }

    @Override
    public void EndContact() {

    }
}
