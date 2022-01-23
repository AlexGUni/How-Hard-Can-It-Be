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
    private int ammo;
    private final int attackDmg;

    /**
     * The enemy that is being targeted not used for player
     */
    private Ship target;

    public Pirate() {
        super();
        target = null;
        type = ComponentType.Pirate;
        plunder = GameManager.getSettings().get("starting").getInt("plunder");
        factionId = 1;
        isAlive = true;
        JsonValue starting = GameManager.getSettings().get("starting");
        health = starting.getInt("health");
        attackDmg = starting.getInt("damage");
        ammo = starting.getInt("ammo");
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
        if(ammo == 0) {
            return;
        }
        ammo--;
        GameManager.shoot((Ship) parent, dir);
    }

    public void reload(int ammo) {
        this.ammo += ammo;
    }

    public int getHealth() {
        return health;
    }

    /**
     * if dst to target is less then attack range
     * target will be null if not in agro range
     */
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
    /**
     * if dst to target is >= attack range
     * target will be null if not in agro range
     */
    public boolean isAgro() {
        if (target != null) {
            final Ship p = (Ship) parent;
            final Vector2 pos = p.getPosition();
            final float dst = pos.dst(target.getPosition());
            // out of attack range
            return dst >= Ship.getAttackRange();
        }
        return false;
    }

    public Ship getTarget() {
        return target;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void kill() {
        health = 0;
        isAlive = false;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getAmmo() {
        return ammo;
    }
}
