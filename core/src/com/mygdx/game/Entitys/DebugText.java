package com.mygdx.game.Entitys;

import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Components.Text;
import com.mygdx.game.Managers.EntityManager;

public class DebugText extends Entity {
    Text text;
    public DebugText() {
        super(1);
        text = new Text(new Vector3(1, 1, 1));
        text.setPosition(230, 0);
        text.setText("FPS: ");
        addComponent(text);

    }

    @Override
    public void update() {
        super.update();
        text.setText("FPS: " + EntityManager.getFPS());
    }
}
