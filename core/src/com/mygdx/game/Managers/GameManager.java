package com.mygdx.game.Managers;

import com.mygdx.game.Entitys.Player;
import com.mygdx.game.Entitys.Ship;
import com.mygdx.game.Faction;

import java.util.ArrayList;

public final class GameManager {
    private static boolean initialized = false;
    private static ArrayList<Faction> factions;
    private static ArrayList<Ship> ships;

    public static void Initialize() {
        initialized = true;
        factions = new ArrayList<>();
        factions.add(new Faction("Halifax", "light-blue"));
        factions.add(new Faction("Constantine", "pink"));
        factions.add(new Faction("Langwidth", "yellow"));
        factions.add(new Faction("Goodrick", "green"));
        factions.add(new Faction("Derwent", "dark-blue"));

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
