package com.mygdx.game.Managers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Components.Renderable;
import com.mygdx.game.Entitys.Chest;
import com.mygdx.game.Entitys.College;
import com.mygdx.game.Entitys.Player;
import com.mygdx.game.Quests.KillQuest;
import com.mygdx.game.Quests.LocateQuest;
import com.mygdx.game.Quests.Quest;
import com.mygdx.utils.Utilities;
import com.sun.tools.javac.util.ArrayUtils;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.util.stream.IntStream;

import static com.mygdx.utils.Constants.TILE_SIZE;
import static com.mygdx.utils.Constants.ZOOM;

public class QuestManager {
    private static boolean initialized = false;
    private static ArrayList<Quest> allQuests;
    private static Chest chest;

    public static void Initialize() {
        initialized = true;
        allQuests = new ArrayList<>();
        chest = new Chest();

        createRandomQuests();
    }

    private static int rndKillQuest(ArrayList<Integer> exclude) {
        int id = 0;
        College enemy;
        int i = 0;
        do {
            id = new Random().nextInt(4) + 2;
            enemy = GameManager.getCollege(id);
            i++;
        }
        while(Utilities.contains(exclude, id) && i < 5);
        if(i == 5) {
            return 0;
        }
        addQuest(new KillQuest(enemy));
        return id;
    }

    private static void rndLocateQuest() {
        final ArrayList<Float> locations = new ArrayList<>();
        for(float f : GameManager.getSettings().get("quests").get("locations").asFloatArray()) {
            locations.add(f);
        }
        Integer choice = -1;
        float x = Utilities.randomChoice(locations, choice);
        float y = 0;
        assert (choice > -1);
        if(choice == locations.size() - 1) {
            y = x;
            x = locations.get(choice - 1);
        }
        else {
            y = locations.get(choice + 1);
        }
        x *= TILE_SIZE;
        y *= TILE_SIZE;
        addQuest(new LocateQuest(new Vector2(x, y), 5 * TILE_SIZE));
    }

    private static void rndQuest(ArrayList<Integer> exclude) {
        if(new Random().nextFloat() >= 0) {
            rndLocateQuest();
        }
        else {
            exclude.add(rndKillQuest(exclude));
        }
    }

    private static void createRandomQuests() {
        // the last quest added is the final quest
        int primaryEnemyId = new Random().nextInt(4) + 2;
        ArrayList<Integer> exclude = new ArrayList<>();
        exclude.add(primaryEnemyId);
        for(int i = 0; i < GameManager.getSettings().get("quests").getInt("count"); i++) {
            rndQuest(exclude);
        }
        College enemy = GameManager.getCollege(primaryEnemyId);
        addQuest(new KillQuest(enemy));
    }

    public static void addQuest(Quest q) {
        tryInit();
        allQuests.add(q);
    }

    public static void checkCompleted() {
        tryInit();
        Player p = GameManager.getPlayer();
        for (Quest q : allQuests) {
            if (q.isCompleted()) {
                continue;
            }
            boolean completed = q.checkCompleted(p);
            if (completed) {
                p.plunder(q.getReward());
            }
            else if (q instanceof LocateQuest){
                chest.setPosition(((LocateQuest) q).getLocation());
                break;
            }
            else {
                chest.setPosition(new Vector2(-1000, -1000));
                break;
            }
        }
    }

    private static void tryInit() {
        if (!initialized) {
            Initialize();
        }
    }

    /**
     * Returns the next un-completed quest
     */
    public static Quest currentQuest() {
        tryInit();
        for(Quest q : allQuests) {
            if(!q.isCompleted()) {
                return q;
            }
        }
        return null;
    }

    public static boolean anyQuests() {
        tryInit();
        return currentQuest() != null;
    }
}
