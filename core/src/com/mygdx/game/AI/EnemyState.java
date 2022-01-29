package com.mygdx.game.AI;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.ai.msg.Telegram;
import com.mygdx.game.Components.Pirate;
import com.mygdx.game.Entitys.NPCShip;

public enum EnemyState implements State<NPCShip> {
    /**
     * Picks random pos and travels to it
     */
    WANDER() {
        @Override
        public void enter(NPCShip e) {
            e.stopMovement();
            e.wander();
        }

        @Override
        public void update(NPCShip e) {
            super.update(e);
            //System.out.println("WANDER");
        }
    },
    /**
     * Tries to get into attack range of the player
     */
    PURSUE() {
        @Override
        public void enter(NPCShip e) {
            e.followTarget();
            // e.goToTarget();
        }

        @Override
        public void update(NPCShip e) {
            super.update(e);
            //System.out.println("PURSUE");
        }
    },
    /**
     * Actively looks for other enemies
     */
    HUNT() {
        @Override
        public void update(NPCShip e) {
            super.update(e);
        }
    },
    /**
     * Attempts to kill the enemy
     */
    ATTACK() {
        @Override
        public void enter(NPCShip e) {
            e.stopMovement();
        }

        @Override
        public void update(NPCShip e) {
            super.update(e);
            //System.out.println("ATTACK");
        }
    };

    @Override
    public void enter(NPCShip e) {

    }

    @Override
    public void update(NPCShip e) {
        StateMachine<NPCShip, EnemyState> m = e.stateMachine;
        Pirate p = e.getComponent(Pirate.class);
        switch (m.getCurrentState()) {
            case WANDER:
                if (p.isAgro()) {
                    m.changeState(PURSUE);
                } else if (p.canAttack()) {
                    m.changeState(ATTACK);
                }
                // if college attacked hunt
                else if (false) {
                    m.changeState(HUNT);
                }
                break;
            case PURSUE:
                // if enter attack range attack
                if (p.canAttack()) {
                    m.changeState(ATTACK);
                }
                // if leave detection range wander
                if (!p.canAttack() && !p.isAgro()) {
                    m.changeState(WANDER);
                }
                break;
            case HUNT:
                // if enter detection range pursue
                if (p.isAgro()) {
                    m.changeState(PURSUE);
                }
                break;
            case ATTACK:
                // if leave attack range pursue
                if (p.isAgro() && !p.canAttack()) {
                    m.changeState(PURSUE);
                }
                // if target dead
                else if (!p.getTarget().isAlive()) {
                    m.changeState(WANDER);
                }
                break;
        }
    }

    @Override
    public void exit(NPCShip e) {

    }

    @Override
    public boolean onMessage(NPCShip e, Telegram telegram) {
        return false;
    }
}
