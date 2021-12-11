package com.mygdx.game.Entitys;

import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Components.BoundingBox;
import com.mygdx.game.Components.Renderable;
import com.mygdx.game.Components.Text;
import com.mygdx.game.Components.Transform;
import com.mygdx.game.Managers.RenderLayer;

public class Enemy extends Entity {
    public Enemy() {
        super(4);

        Transform t = new Transform();
        t.setPosition(32, 32);
        addComponent(t);

        Renderable r = new Renderable(1, RenderLayer.Five);
        addComponent(r);

        BoundingBox bb = new BoundingBox(r, t);
        addComponent(bb);

        Text text = new Text(4, new Vector3(1, 0, 0));
        text.setText("Beta Male No.1");
        addComponent(text);
    }
}
