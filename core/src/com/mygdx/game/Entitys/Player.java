package com.mygdx.game.Entitys;

import com.mygdx.game.Components.PlayerController;
import com.mygdx.game.Managers.GameManager;

public class Player extends Ship {

    private Player(float speed) {
        super();

        PlayerController pc = new PlayerController(this, speed);
        addComponent(pc);

        setName("Player");
    }

    public Player() {
        this(GameManager.getSettings().get("starting").getFloat("playerSpeed"));
    }

    @Override
    public void cleanUp() {
        super.cleanUp();
    }
}
