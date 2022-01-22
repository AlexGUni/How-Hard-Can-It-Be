package com.mygdx.game.Entitys;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Components.Pirate;
import com.mygdx.game.Components.Transform;
import com.mygdx.game.Faction;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.utils.Utilities;
import org.graalvm.compiler.core.common.util.Util;

import java.util.ArrayList;

import static com.mygdx.utils.Constants.BUILDING_SCALE;
import static com.mygdx.utils.Constants.TILE_SIZE;

public class College extends Entity {
    private static ArrayList<String> buildingNames;
    private final ArrayList<Building> buildings;
    public College() {
        super();
        buildings = new ArrayList<>();
        buildingNames = new ArrayList<>();
        buildingNames.add("big");
        buildingNames.add("small");
        buildingNames.add("clock");
        Transform t = new Transform();
        Pirate p = new Pirate();
        addComponents(t, p);
    }

    public College(int factionId) {
        this();
        Faction f = GameManager.getFaction(factionId);
        Transform t = getComponent(Transform.class);
        t.setPosition(f.getPosition());
        Pirate p = new Pirate();
        addComponents(t, p);
        spawn(f.getColour());
    }

    private void spawn(String colour) {
        JsonValue collegeSettings = GameManager.getSettings().get("college");
        float radius = collegeSettings.getFloat("spawnRadius");
        // radius = Utilities.tilesToDistance(radius) * BUILDING_SCALE;
        final Vector2 origin = getComponent(Transform.class).getPosition();

        for (int i = 0; i < collegeSettings.getInt("numBuildings"); i++) {
            Building b = new Building();
            buildings.add(b);
            Vector2 pos = Utilities.randomPos(-radius, radius);
            pos = Utilities.floor(pos);

            pos = Utilities.tilesToDistance(pos).add(origin);
            String b_name = Utilities.randomChoice(buildingNames);

            b.create(pos, b_name);
        }
        Building flag = new Building(true);
        buildings.add(flag);
        flag.create(origin, colour);
    }

    public void isAlive() {
        boolean res = false;
        for(Building b : buildings) {
            if(b.isAlive()) {
                res = true;
            }
        }
        if (!res) {
           getComponent(Pirate.class).kill();
        }
    }

    @Override
    public void update() {
        super.update();
        isAlive();
    }
}
