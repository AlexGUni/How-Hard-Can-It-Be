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
    private final Vector2 mapDim;

    private final ObjectMap<Node, Array<Connection<Node>>> nodePaths;

    private TileMapGraph() {

        heuristic = new NodeHeuristic();
        nodes = new Array<>();
        paths = new Array<>();
        nodePaths = new ObjectMap<>();
        mapDim = new Vector2();
    }

    private int getType(TiledMapTileLayer.Cell c) {
        return c.getTile().getId();
    }

    public TileMapGraph(TiledMap map) {
        this();

        MapLayers layers = map.getLayers();
        TiledMapTileLayer layer = null;

        // find the collision layer
        for(MapLayer l : layers) {
            if(l.getName().equals("Collision")) {
                layer = (TiledMapTileLayer) l;
            }
        }
        // if there is no collision layer
        if(layer == null) {
            return;
        }
        // the map dimensions
        mapDim.set(layer.getWidth(), layer.getHeight());

        // create all the nodes
        for(int i = 0; i < mapDim.x * mapDim.y; i++) {
            nodes.add(new Node(0, 0));
        }

        // for each column
        for (int x = 0; x < layer.getWidth(); x++) {
            // for each row
            for (int y = 0; y < layer.getHeight(); y++) {
                TiledMapTileLayer.Cell center = layer.getCell(x, y);

                // the central node
                addNode(x, y, getType(center) == OBSTACLE);

                // all surrounding nodes
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {

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

                            addNode(x + i, y + j, getType(cell) == OBSTACLE);
                            addPath(x, y, x + i, y + j);

                            /*Node c2 = nodes.get(getIndex(x + i, y + j));
                            addNode(c2);
                            addPath(c1, c2);*/
                        }
                    }
                }
            }
        }
    }

    public Node getNode(float x, float y){
        return nodes.get(getIndex(x, y));
    }

    private int getIndex(float x, float y) {
        return (int) (mapDim.x * y + x);
    }

    private int getIndex(int x, int y) {
        return (int) mapDim.x * y + x;
    }

    private void addNode(float x, float y, boolean isObstacle) {
        Node n = nodes.get(getIndex((int) x, (int) y));
        if(n.cost > 0) {
            return;
        }
        n.set(x, y);
        if (isObstacle) {
            n.cost = OBSTACLE_COST;
        }
        else {
            n.cost = 1;
        }
    }

    private void addPath(Node a, Node b) {
        Path path = new Path(a, b);

        // check if b to a exists

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
        return getIndex(node.getPosition().x, node.getPosition().y);
    }

    @Override
    public int getNodeCount() {
        return (int) (mapDim.x * mapDim.y);
    }

    @Override
    public Array<Connection<Node>> getConnections(Node fromNode) {
        if(nodePaths.containsKey(fromNode)){
            return nodePaths.get(fromNode);
        }

        return new Array<>();
    }
}
