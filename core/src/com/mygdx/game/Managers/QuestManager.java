package com.mygdx.game.Managers;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Entitys.Player;
import com.mygdx.game.Quests.LocateQuest;
import com.mygdx.game.Quests.Quest;

import java.util.ArrayList;

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
        LocateQuest q = new LocateQuest(new Vector2(100, 100), 50);
        allQuests.add(q);
    }

    public static void checkCompleted() {
        tryInit();
        Player p = GameManager.getPlayer();
        for(Quest q : allQuests) {
            if (q.isCompleted()) {
                return;
            }
            boolean completed = q.checkCompleted(p);
            if(completed) {
                System.out.println("locate quest completed");
                p.plunder(q.getReward());
            }
        }
    }


    private static void tryInit() {
        if(!initialized) {
            Initialize();
        }
    }
}
