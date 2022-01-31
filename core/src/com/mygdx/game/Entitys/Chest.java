package com.mygdx.game.Entitys;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Components.Pirate;
import com.mygdx.game.Components.Renderable;
import com.mygdx.game.Components.Transform;
import com.mygdx.game.Managers.RenderLayer;
import com.mygdx.game.Managers.ResourceManager;

import static com.mygdx.utils.Constants.BUILDING_SCALE;

/**
 * Simple entity shown on locate quests origin
 */
public class Chest extends Entity{
    public Chest() {
        super(2);
        Transform t = new Transform();
        Renderable r = new Renderable(ResourceManager.getId("Chest.png"), RenderLayer.Transparent);
        addComponents(t, r);
    }

    public void setPosition(Vector2 pos) {
        getComponent(Transform.class).setPosition(pos);
    }
}
