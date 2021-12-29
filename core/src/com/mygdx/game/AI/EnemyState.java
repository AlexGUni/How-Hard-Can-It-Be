package com.mygdx.game.AI;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.ai.msg.Telegram;
import com.mygdx.game.Entitys.Enemy;

public enum EnemyState implements State<Enemy> {
    /**
     * Picks random pos and travels to it
     */
    WANDER() {
        @Override
        public void update(Enemy e) {
            super.update(e);
            //System.out.println("WANDER");
            //e.wander();
        }
    },
    /**
     * Tries to get into attack range of the player
     */
    PURSUE() {
        @Override
        public void update(Enemy e) {
            super.update(e);
            //System.out.println("PURSUE");
            //e.followPlayer();
        }
    },
    /**
     * Actively looks for other enemies
     */
    HUNT() {
        @Override
        public void update(Enemy e) {
            super.update(e);
            //System.out.println("HUNT");
            //e.followPlayer();
        }
    },
    /**
     * Attempts to kill the enemy
     */
    ATTACK() {
        @Override
        public void update(Enemy e) {
            super.update(e);
            //System.out.println("ATTACK");
        }
    };

    @Override
    public void enter(Enemy e) {

    }

    @Override
    public void update(Enemy e) {
        StateMachine<Enemy, EnemyState> m = e.stateMachine;
        switch (m.getCurrentState()) {
            case WANDER:
                // if enter detection range pursue
                // if college attacked hunt
                break;
            case PURSUE:
                // if enter attack range attack
                // if leave detection range wander
                break;
            case HUNT:
                // if enter detection range pursue
                break;
            case ATTACK:
                // if leave attack range pursue
                break;
        }
    }

    @Override
    public void exit(Enemy e) {

    }

    @Override
    public boolean onMessage(Enemy e, Telegram telegram) {
        return false;
    }
}
