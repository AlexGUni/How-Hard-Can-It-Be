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
    private int health;
    private final int attackDmg;

    /**
     * The enemy that is being targeted
     */
    private Ship target;
    public boolean canAttack;

    public Pirate() {
        super();
        canAttack = false;
        target = null;
        type = ComponentType.Pirate;
        plunder = GameManager.getSettings().get("starting").getInt("plunder");
        factionId = 1;
        isAlive = true;
        JsonValue starting = GameManager.getSettings().get("starting");
        health = starting.getInt("health");
        attackDmg = starting.getInt("damage");
    }

    public void setTarget(Ship target) {
        this.target = target;
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

    public void shoot(Vector2 dir) {
        GameManager.shoot((Ship) parent, dir);
    }

    public int getHealth() {
        return health;
    }

    public boolean canAttack() {
        if (target != null) {
            final Ship p = (Ship) parent;
            final Vector2 pos = p.getPosition();
            final float dst = pos.dst(target.getPosition());
            // withing attack range
            return dst < Ship.getAttackRange();
        }
        return false;
    }
    public boolean isAgro() {
        if (target != null) {
            final Ship p = (Ship) parent;
            final Vector2 pos = p.getPosition();
            final float dst = pos.dst(target.getPosition());
            // withing attack range
            return dst >= Ship.getAttackRange();
        }
        return false;
    }

    public Ship getTarget() {
        return target;
    }
}
