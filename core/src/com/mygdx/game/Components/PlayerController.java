package com.mygdx.game.Components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Entitys.Player;
import com.mygdx.game.Managers.EntityManager;
import com.mygdx.game.Managers.RenderingManager;

import static com.mygdx.utils.Constants.PHYSICS_DELTA_TIME;

/**
 * Responsible for the keyboard control of the player
 */
public class PlayerController extends Component {
    private Player player;
    private float speed;

    public PlayerController() {
        super();
    }

    public PlayerController(Player player, float speed) {
        this.player = player;
        this.speed = speed;
    }

    @Override
    public void update() {
        super.update();
        float s = speed * PHYSICS_DELTA_TIME;

        Vector2 pos = player.getPos();
        Vector2 deltaP = new Vector2(0, 0);

        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            deltaP.y += 1;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            deltaP.y -= 1;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            deltaP.x -= 1;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            deltaP.x += 1;
        }

        deltaP.scl(s);

        RigidBody rb = parent.getComponent(RigidBody.class);
        rb.setVelocity(deltaP);

        RenderingManager.getCamera().position.set(new Vector3(player.getPos(), 0.0f));
        RenderingManager.getCamera().update();
    }
}
