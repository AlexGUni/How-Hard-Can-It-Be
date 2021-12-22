package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
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

        for (JsonValue v : settings.get("factions")){
            String name = v.getString("name");
            String col = v.getString("colour");
            factions.add(new Faction(name, col));
        }

        ships = new ArrayList<>();
    }

    /**
     * Creates player that belongs the faction with id 1
     */
    public static void CreatePlayer() {
        tryInit();
        Player p = new Player();
        ships.add(p);
    }

    public static void CreateEnemy(int factionId) {
        tryInit();

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
}
