package com.mygdx.game.Managers;

import com.mygdx.game.Entitys.College;
import com.mygdx.game.Entitys.Player;
import com.mygdx.game.Quests.KillQuest;
import com.mygdx.game.Quests.Quest;
import com.mygdx.utils.Utilities;
import com.sun.tools.javac.util.ArrayUtils;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class QuestManager {
    private static boolean initialized = false;
    private static ArrayList<Quest> allQuests;

    public static void Initialize() {
        initialized = true;
        allQuests = new ArrayList<>();

        createRandomQuests();
    }

    private static int rndKillQuest(ArrayList<Integer> exclude) {
        int id = 0;
        College enemy;
        do {
            id = new Random().nextInt(4) + 2;
            enemy = GameManager.getCollege(id);
        }
        while(Utilities.contains(exclude, id));
        addQuest(new KillQuest(enemy));
        return id;
    }

    private static void createRandomQuests() {
        // the last quest added is the final quest
        int primaryEnemyId = new Random().nextInt(4) + 2;
        ArrayList<Integer> exclude = new ArrayList<>();
        exclude.add(primaryEnemyId);
        for(int i = 0; i < GameManager.getSettings().get("quests").getInt("count"); i++) {
            exclude.add(rndKillQuest(exclude));
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
                System.out.println("locate quest completed");
                p.plunder(q.getReward());
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
