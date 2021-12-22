package com.mygdx.game.Components;

public class Living extends Component {
    protected boolean isAlive;
    protected float health;
    protected float attackDmg;

    public Living() {
        super();
        isAlive = true;
        health = 100;
        attackDmg = 10;
    }

    public void takeDamage(float dmg) {
        health -= dmg;
        if(health <= 0){
            health = 0;
            isAlive = false;
        }
    }
}
