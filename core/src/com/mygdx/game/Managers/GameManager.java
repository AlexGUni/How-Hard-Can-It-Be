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
import com.mygdx.utils.Utilities;

import java.util.ArrayList;

import static com.mygdx.utils.Constants.TILE_SIZE;

public final class GameManager {
    private static boolean initialized = false;
    private static ArrayList<Faction> factions;
    private static ArrayList<Ship> ships;
    private static ArrayList<College> colleges;

    private static final int cacheSize = 20;
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
        colleges = new ArrayList<>();

        for (int i = 0; i < cacheSize; i++) {
            ballCache.add(new CannonBall());
        }

        for (JsonValue v : settings.get("factions")) {
            String name = v.getString("name");
            String col = v.getString("colour");
            Vector2 pos = new Vector2(v.get("position").getFloat("x"), v.get("position").getFloat("y"));
            pos = Utilities.tilesToDistance(pos);
            Vector2 spawn = new Vector2(v.get("shipSpawn").getFloat("x"), v.get("shipSpawn").getFloat("y"));
            spawn = Utilities.tilesToDistance(spawn);
            factions.add(new Faction(name, col, pos, spawn, factions.size() + 1));
        }
    }

    public static void update() {
        QuestManager.checkCompleted();
    }

    public static Player getPlayer() {
        return (Player) ships.get(0);
    }

    public static void SpawnGame(int mapId) {
        CreateWorldMap(mapId);
        CreatePlayer();
        final int cnt = settings.get("factionDefaults").getInt("shipCount");
        for (int i = 0; i < factions.size(); i++) {
            CreateCollege(i + 1);
            for (int j = 0; j < cnt; j++) {
                // prevents halifax from having shipcount + player
                if(i == 0 && j > cnt - 2){
                    break;
                }
                NPCShip s = CreateNPCShip(i + 1);
                s.getComponent(Transform.class).setPosition(getFaction(i + 1).getSpawnPos());
            }
        }
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

    public static NPCShip CreateNPCShip(int factionId) {
        tryInit();
        NPCShip e = new NPCShip();
        e.setFaction(factionId);
        ships.add(e);
        return e;
    }

    public static void CreateWorldMap(int mapId) {
        tryInit();
        map = new WorldMap(mapId);
        mapGraph = new TileMapGraph(map.getTileMap());
    }

    public static void CreateCollege(int factionId) {
        tryInit();
        College c = new College(factionId);
        colleges.add(c);
    }

    private static void tryInit() {
        if (!initialized) {
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

    public static College getCollege(int factionId) {
        tryInit();
        return colleges.get(factionId - 1);
    }

    public static void shoot(Ship p, Vector2 dir) {
        Vector2 pos = p.getComponent(Transform.class).getPosition().cpy();
        //pos.add(dir.x * TILE_SIZE * 0.5f, dir.y * TILE_SIZE * 0.5f);
        ballCache.get(currentElement++).fire(pos, dir, p);
        currentElement %= cacheSize;
    }

    public static QueueFIFO<Vector2> getPath(Vector2 loc, Vector2 dst) {
        return mapGraph.findOptimisedPath(loc, dst);
    }
}
