package com.mygdx.game.AI;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import static com.mygdx.utils.TileMapCells.*;

public class TileMapGraph implements IndexedGraph<Node> {
    private final NodeHeuristic heuristic;
    private final Array<Node> nodes;
    private final Array<Path> paths;
    private int pathIndex;
    private final Vector2 mapDim;

    private final ObjectMap<Node, Array<Connection<Node>>> nodePaths;

    private TileMapGraph() {
        pathIndex = 0;
        heuristic = new NodeHeuristic();
        nodes = new Array<>();
        paths = new Array<>();
        nodePaths = new ObjectMap<>();
        mapDim = new Vector2();
    }

    public TileMapGraph(TiledMap map) {
        this();

        MapLayers layers = map.getLayers();
        TiledMapTileLayer layer = null;
        for(MapLayer l : layers) {
            if(l.getName().equals("Collision")) {
                layer = (TiledMapTileLayer) l;
            }
        }
        if(layer == null) {
            return;
        }
        mapDim.set(layer.getWidth(), layer.getHeight());

        for(int i = 0; i < mapDim.x * mapDim.y; i++) {
            nodes.add(new Node(0, 0));
        }

        for (int x = 0; x < layer.getWidth(); x++) {
            for (int y = 0; y < layer.getHeight(); y++) {
                TiledMapTileLayer.Cell center = layer.getCell(x, y);
                // is the center an obstacle
                if (center == null || center.getTile().getId() == OBSTACLE) {
                    continue;
                }

                addNode(x, y);
                // all surrounding nodes
                for (int i = -1; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        // prevents the node pathing to its self
                        if (i == 0 && j == 0){
                            continue;
                        }
                        TiledMapTileLayer.Cell cell = layer.getCell(x + i, y + j);
                        // is cell outside the map
                        if (cell == null) {
                           continue;
                        }

                        // is the cell passable
                        if (cell.getTile().getId() == PASSABLE) {

                            addNode(x + i, y + j);
                            addPath(x, y, x + i, y + j);

                            /*Node c2 = nodes.get(getIndex(x + i, y + j));
                            addNode(c2);
                            addPath(c1, c2);*/
                        }
                    }
                }
            }
        }

        for(int i = 0; i < nodes.size; i++){
            if(nodes.get(i).index == -1){
                nodes.removeIndex(i);
                i--;
            }
        }
    }

    public Node getNode(float x, float y){
        return nodes.get(getIndex((int) x, (int) y));
    }

    private int getIndex(int x, int y) {
        return (int) mapDim.x * y + x;
    }

    public void addNode(float x, float y) {
        Node n = nodes.get(getIndex((int) x, (int) y));
        n.set(x, y);
        n.index = pathIndex++;
    }

    public void addPath(Node a, Node b) {
        Path path = new Path(a, b);
        if(!nodePaths.containsKey(a)){
            nodePaths.put(a, new Array<Connection<Node>>());
        }
        nodePaths.get(a).add(path);
        paths.add(path);
    }

    public void addPath(float x1, float y1, float x2, float y2) {
        Node a = getNode(x1, y1);
        Node b = getNode(x2, y2);

        addPath(a, b);
    }

    public GraphPath<Node> findPath(Node startCity, Node goalCity){
        GraphPath<Node> path = new DefaultGraphPath<>();
        new IndexedAStarPathFinder<>(this).searchNodePath(startCity, goalCity, heuristic, path);
        return path;
    }

    @Override
    public int getIndex(Node node) {
        return getIndex((int) node.getPosition().x, (int) node.getPosition().y);
    }

    @Override
    public int getNodeCount() {
        return pathIndex;
    }

    @Override
    public Array<Connection<Node>> getConnections(Node fromNode) {
        if(nodePaths.containsKey(fromNode)){
            return nodePaths.get(fromNode);
        }

        return new Array<>();
    }
}
