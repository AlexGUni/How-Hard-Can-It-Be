package com.mygdx.game.Entitys;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Components.Pirate;
import com.mygdx.game.Components.Renderable;
import com.mygdx.game.Components.RigidBody;
import com.mygdx.game.Components.Transform;
import com.mygdx.game.Managers.ResourceManager;

import java.awt.datatransfer.Transferable;

public class Building extends Entity {
    private String buildingName;
    private static int atlas_id;
    Building() {
        super();
        Transform t = new Transform();
        Pirate p = new Pirate();
        Renderable r = new Renderable();
        addComponents(t, p, r);
    }

    public void create(Vector2 pos, String name) {
        Sprite s = ResourceManager.getSprite(atlas_id, name);
        getComponent(Renderable.class).setTexture(s);
        getComponent(Transform.class).setPosition(pos);
        buildingName = name;
    }
}
