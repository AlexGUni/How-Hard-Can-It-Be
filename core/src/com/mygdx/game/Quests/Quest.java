package com.mygdx.game.Quests;

public abstract class Quest {
    protected String name;
    protected String description;
    protected float reward;

    public Quest() {
        name = "";
        description = "";
        reward = 0;
    }

    public abstract boolean checkCompleted();

    public float getReward() {
        return reward;
    }
}
