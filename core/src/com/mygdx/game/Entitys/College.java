package com.mygdx.game.Entitys;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Components.Pirate;
import com.mygdx.game.Components.Renderable;
import com.mygdx.game.Components.Transform;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.Managers.ResourceManager;
import com.mygdx.utils.Utilities;

import java.util.ArrayList;

public class College extends Entity {
    private static ArrayList<String> buildingNames;
    private final ArrayList<Building> buildings;
    public College() {
        super();
        buildings = new ArrayList<>();
        Transform t = new Transform();
        addComponent(t);
    }

    private void spawn() {
        JsonValue collegeSettings = GameManager.getSettings().get("college");
        float radius = collegeSettings.getFloat("spawnRadius");
        Vector2 origin = getComponent(Transform.class).getPosition();
        for (int i = 0; i < collegeSettings.getInt("numBuildings"); i++) {
            Building b = new Building();
            buildings.add(b);
            Vector2 pos = Utilities.randomPos(-radius, radius).add(origin);
            String b_name = Utilities.randomChoice(buildingNames);
            b.create(pos, b_name);
        }
        Building flag = new Building();
        buildings.add(flag);
        flag.create(origin, "flag_");
    }
}
