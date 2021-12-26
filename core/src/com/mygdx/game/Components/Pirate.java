package com.mygdx.game.Components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Entitys.Ship;
import com.mygdx.game.Faction;
import com.mygdx.game.Managers.GameManager;

public class Pirate extends Component {
    private int factionId;
    private int plunder;
    protected boolean isAlive;
    protected int health;
    protected int attackDmg;

    public Pirate() {
        super();
        plunder = GameManager.getSettings().get("starting").getInt("plunder");
        factionId = 1;
        isAlive = true;
        JsonValue starting = GameManager.getSettings().get("starting");
        health = starting.getInt("health");
        attackDmg = starting.getInt("damage");
    }

    public int getPlunder() {
        return plunder;
    }

    public void addPlunder(int money) {
        plunder += money;
    }

    public Faction getFaction() {
        return GameManager.getFaction(factionId);
    }

    public void setFactionId(int factionId) {
        this.factionId = factionId;
    }

    public void takeDamage(float dmg) {
        health -= dmg;
        if(health <= 0){
            health = 0;
            isAlive = false;
        }
    }

    public void shoot() {
        GameManager.shoot((Ship) parent, new Vector2(0, 1));
    }

    public int getHealth() {
        return health;
    }
}
