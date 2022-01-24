package com.mygdx.game.Entitys;

import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.AI.EnemyState;
import com.mygdx.game.Components.*;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.utils.QueueFIFO;
import com.mygdx.utils.Utilities;

public class NPCShip extends Ship {
    public StateMachine<NPCShip, EnemyState> stateMachine;
    private static JsonValue AISettings;
    private final QueueFIFO<Vector2> path;

    public NPCShip() {
        super();
        path = new QueueFIFO<>();

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
        rb.addTrigger(Utilities.tilesToDistance(starting.getFloat("argoRange_tiles")), "agro");

    }

    private Ship getTarget() {
        return getComponent(Pirate.class).getTarget();
    }

    @Override
    public void update() {
        super.update();
        stateMachine.update();

        // follows path
        /*if (!path.isEmpty()) {
            Vector2 goTo = Utilities.tilesToDistance(path.peek()); // goto offset in world space
            goTo.add(getPosition()); // translated to player pos
            float radius = GameManager.getSettings().get("starting").getFloat("attackRange_tiles") + 0.5f; // in tile space
            // radius = 1;
            radius = Utilities.tilesToDistance(radius); // in world space
            boolean proximity = Utilities.checkProximity(goTo, getPosition(), radius);

            // has reached target point so remove point
            if (proximity) {
                path.pop();
            } else {
                RigidBody rb = getComponent(RigidBody.class);
                rb.setVelocity(path.peek().cpy().scl(GameManager.getSettings().get("starting").getFloat("playerSpeed")));
            }
        }*/
    }

    public void goToTarget() {
        /*path = GameManager.getPath(
                Utilities.distanceToTiles(getPosition()),
                Utilities.distanceToTiles(getTarget().getPosition()));*/
    }

    public void followTarget() {
        if (getTarget() == null) {
            stopMovement();
            return;
        }
        AINavigation nav = getComponent(AINavigation.class);

        Arrive<Vector2> arrives = new Arrive<>(nav,
                getTarget().getComponent(Transform.class))
                .setTimeToTarget(AISettings.getFloat("accelerationTime"))
                .setArrivalTolerance(AISettings.getFloat("arrivalTolerance"))
                .setDecelerationRadius(AISettings.getFloat("slowRadius"));

        nav.setBehavior(arrives);
    }

    public void stopMovement() {
        AINavigation nav = getComponent(AINavigation.class);
        nav.setBehavior(null);
        nav.stop();
    }

    public void wander() {

    }
}
