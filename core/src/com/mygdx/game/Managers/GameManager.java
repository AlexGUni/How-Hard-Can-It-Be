package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Entitys.Enemy;
import com.mygdx.game.Entitys.Player;
import com.mygdx.game.Entitys.Ship;
import com.mygdx.game.Faction;

import java.util.ArrayList;

public final class GameManager {
    private static boolean initialized = false;
    private static ArrayList<Faction> factions;
    private static ArrayList<Ship> ships;

    private static JsonValue settings;


    public static void Initialize() {
        initialized = true;
        settings = new JsonReader().
                parse(Gdx.files.internal("GameSettings.json"));

        factions = new ArrayList<>();
        ships = new ArrayList<>();

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

    public static void CreateEnemy(int factionId) {
        tryInit();
        Enemy e = new Enemy();
        e.setFaction(factionId);
        ships.add(e);
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
}
