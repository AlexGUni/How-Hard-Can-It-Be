package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.AI.TileMapGraph;
import com.mygdx.game.Components.Transform;
import com.mygdx.game.Entitys.*;
import com.mygdx.game.Faction;
import com.mygdx.utils.QueueFIFO;

import java.util.ArrayList;

import static com.mygdx.utils.Constants.TILE_SIZE;

public final class GameManager {
    private static boolean initialized = false;
    private static ArrayList<Faction> factions;
    private static ArrayList<Ship> ships;

    private static final int cacheSize = 10;
    private static ArrayList<CannonBall> ballCache;
    private static int currentElement;

    private static JsonValue settings;

    private static WorldMap map;
    private static TileMapGraph mapGraph;

    public static void Initialize() {
        initialized = true;
        currentElement = 0;
        settings = new JsonReader().
                parse(Gdx.files.internal("GameSettings.json"));

        factions = new ArrayList<>();
        ships = new ArrayList<>();
        ballCache = new ArrayList<>(cacheSize);

        for (int i = 0; i < cacheSize; i++) {
            ballCache.add(new CannonBall());
        }

        for (JsonValue v : settings.get("factions")){
            String name = v.getString("name");
            String col = v.getString("colour");
            factions.add(new Faction(name, col));
        }
    }

    public  static void update() {
        QuestManager.checkCompleted();
    }

    public static Player getPlayer() {
        return (Player) ships.get(0);
    }

    /**
     * Creates player that belongs the faction with id 1
     */
    public static void CreatePlayer() {
        tryInit();
        Player p = new Player();
        p.setFaction(1);
        ships.add(p);
    }

    public static void CreateNPCShip(int factionId) {
        tryInit();
        NPCShip e = new NPCShip();
        e.setFaction(factionId);
        ships.add(e);
    }

    public static void CreateWorldMap(int mapId) {
        tryInit();
        map = new WorldMap(mapId);
        mapGraph = new TileMapGraph(map.getTileMap());
    }

    private static void tryInit() {
        if(!initialized){
            Initialize();
        }
    }

    public static Faction getFaction(int factionId) {
        tryInit();
        return factions.get(factionId - 1);
    }

    public static JsonValue getSettings() {
        tryInit();
        return settings;
    }

    public static void shoot(Ship p, Vector2 dir) {
        Vector2 pos = p.getComponent(Transform.class).getPosition().cpy();
        pos.add(dir.x * TILE_SIZE, (dir.y * TILE_SIZE));
        ballCache.get(currentElement++).fire(pos, dir, p);
        currentElement %= cacheSize;
    }

    public static QueueFIFO<Vector2> getPath(Vector2 loc, Vector2 dst) {
        return mapGraph.findOptimisedPath(loc, dst);
    }
}
