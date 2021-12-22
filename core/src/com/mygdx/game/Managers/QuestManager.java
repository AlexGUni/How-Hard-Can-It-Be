package com.mygdx.game.Managers;

import com.mygdx.game.Quests.Quest;

import java.util.ArrayList;

public class QuestManager {
    private static boolean initialized = false;
    private static ArrayList<Quest> allQuests;

    public static void Initialize() {
        initialized = true;
    }

    public void createRandomQuests() {
        tryInit();
    }

    public void checkCompleted() {
        for(Quest q : allQuests) {
            boolean completed = q.checkCompleted();
            if(completed) {
                GameManager.getPlayer().plunder(q.getReward());
            }
        }
    }


    public void tryInit() {
        if(!initialized) {
            Initialize();
        }
    }
}
