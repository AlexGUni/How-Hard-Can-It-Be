package com.mygdx.game.Quests;

import com.mygdx.game.Entitys.Player;

public abstract class Quest {
    protected String name;
    protected String description;
    protected int reward;
    protected boolean isCompleted;

    public Quest() {
        name = "";
        description = "";
        reward = 0;
        isCompleted = false;
    }

    public abstract boolean checkCompleted(Player p);

    public int getReward() {
        return reward;
    }
    public boolean isCompleted() {
        return isCompleted;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
