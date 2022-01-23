package com.mygdx.game.Managers;

import com.mygdx.game.Entitys.College;
import com.mygdx.game.Entitys.Player;
import com.mygdx.game.Quests.KillQuest;
import com.mygdx.game.Quests.Quest;

import java.util.ArrayList;
import java.util.Random;

public class QuestManager {
    private static boolean initialized = false;
    private static ArrayList<Quest> allQuests;

    public static void Initialize() {
        initialized = true;
        allQuests = new ArrayList<>();

        createRandomQuests();
    }

    public static void createRandomQuests() {
        tryInit();
        int primaryEnemyId = new Random().nextInt(4) + 2;
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
                return;
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

    public static Quest currentQuest() {
        tryInit();
        return allQuests.get(0);
    }
}
