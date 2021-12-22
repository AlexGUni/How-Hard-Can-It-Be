package com.mygdx.game.Managers;

import com.mygdx.game.Entitys.Player;
import com.mygdx.game.Entitys.Ship;

import java.util.ArrayList;

public final class GameManager {
    private static boolean initialized = false;
    private static ArrayList<String> factionNames;
    private static ArrayList<Ship> ships;

    public static void Initialize() {
        initialized = true;
        factionNames = new ArrayList<>();
        factionNames.add("Halifax");
        factionNames.add("Constantine");
        factionNames.add("Langwidth");
        factionNames.add("Goodrick");
        factionNames.add("Derwent");

        ships = new ArrayList<>();
    }

    /**
     * Creates player that belongs the faction with id 1
     */
    public static void CreatePlayer() {
        Player p = new Player();
        ships.add(p);
    }

    public static void CreateEnemy(String faction) {
        CreateEnemy(factionNames.indexOf(faction) + 1);
    }

    public static void CreateEnemy(int factionId) {

    }

    private static void tryInit() {
        if(!initialized){
            Initialize();
        }
    }
}
