package com.mygdx.game.Components;

import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Managers.GameManager;

public class Living extends Component {
    protected boolean isAlive;
    protected int health;
    protected int attackDmg;

    public Living() {
        super();
        isAlive = true;
        JsonValue starting = GameManager.getSettings().get("starting");
        health = starting.getInt("health");
        attackDmg = starting.getInt("damage");
    }

    public void takeDamage(float dmg) {
        health -= dmg;
        if(health <= 0){
            health = 0;
            isAlive = false;
        }
    }

    public int getHealth() {
        return health;
    }
}
