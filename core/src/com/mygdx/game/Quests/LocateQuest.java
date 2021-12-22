package com.mygdx.game.Quests;

public class LocateQuest extends Quest {
    public LocateQuest() {
        name = "Find a chest";
        description = "Find a chest at the TOP LEFT of the map";
        reward = 100;
    }
    @Override
    public boolean checkCompleted() {
        return false;
    }
}
