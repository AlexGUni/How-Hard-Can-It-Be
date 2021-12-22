package com.mygdx.game.Quests;

import com.mygdx.game.Entitys.Player;

public abstract class Quest {
    protected String name;
    protected String description;
    protected float reward;
    protected boolean isCompleted;

    public Quest() {
        name = "";
        description = "";
        reward = 0;
        isCompleted = false;
    }

    public abstract boolean checkCompleted(Player p);

    public float getReward() {
        return reward;
    }
    public boolean isCompleted() {
        return isCompleted;
    }
}
