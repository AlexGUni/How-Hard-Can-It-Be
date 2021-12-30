package com.mygdx.game.Quests;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Entitys.Player;

public class LocateQuest extends Quest {
    private final Vector2 loc;
    private float radius;

    public LocateQuest() {
        super();
        name = "Find a chest";
        description = "Find a chest at the TOP LEFT of the map";
        reward = 100;
        loc = new Vector2();
        radius = -1;
    }

    public LocateQuest(Vector2 pos, float r) {
        this();
        loc.set(pos);
        radius = r * r;
    }

    @Override
    public boolean checkCompleted(Player p) {
        if(radius == -1) {
            return false;
        }
        Vector2 delta = p.getPosition().cpy();
        delta.sub(loc);
        float l = delta.len2();
        isCompleted = l < radius;
        return isCompleted;
    }

}
