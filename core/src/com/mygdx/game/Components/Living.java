package com.mygdx.game.Components;

import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Managers.GameManager;

public class Living extends Component {
    protected boolean isAlive;
    protected float health;
    protected float attackDmg;

    public Living() {
        super();
        isAlive = true;
        JsonValue starting = GameManager.getSettings().get("starting");
        health = starting.getFloat("health");
        attackDmg = starting.getFloat("damage");
    }

    public void takeDamage(float dmg) {
        health -= dmg;
        if(health <= 0){
            health = 0;
            isAlive = false;
        }
    }
}
