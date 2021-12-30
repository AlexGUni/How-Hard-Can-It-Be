package com.mygdx.game.Entitys;

import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.AI.EnemyState;
import com.mygdx.game.Components.*;
import com.mygdx.game.Managers.EntityManager;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.Physics.CollisionCallBack;
import com.mygdx.game.Physics.CollisionInfo;

import java.util.Objects;

public class NPCShip extends Ship {
    public StateMachine<NPCShip, EnemyState> stateMachine;
    private static JsonValue AISettings;

    public NPCShip() {
        super();

        if (AISettings == null) {
            AISettings = GameManager.getSettings().get("AI");
        }

        stateMachine = new DefaultStateMachine<>(this, EnemyState.WANDER);

        setName("Enemy");

        Text text = new Text(new Vector3(1, 0, 0));
        text.setText(getName());

        AINavigation nav = new AINavigation();

        addComponents(nav, text);


        RigidBody rb = getComponent(RigidBody.class);

        JsonValue starting = GameManager.getSettings().get("starting");

        // agro trigger
        rb.addTrigger(tilesToSpace(starting.getFloat("argoRange_tiles")), "agro");

    }

    private Ship getTarget() {
        return getComponent(Pirate.class).getTarget();
    }

    @Override
    public void update() {
        super.update();
        stateMachine.update();
    }

    public void followTarget() {
        if(getTarget() == null){
            throw new RuntimeException("Cant follow null target");
        }
        AINavigation nav = getComponent(AINavigation.class);

        Arrive<Vector2> arrives = new Arrive<>(nav,
                getTarget().getComponent(Transform.class))
                .setTimeToTarget(AISettings.getFloat("accelerationTime"))
                .setArrivalTolerance(AISettings.getFloat("arrivalTolerance"))
                .setDecelerationRadius(AISettings.getFloat("slowRadius"));

        nav.setBehavior(arrives);
    }

    public void stopFollow() {
        AINavigation nav = getComponent(AINavigation.class);
        nav.setBehavior(null);
    }
}
