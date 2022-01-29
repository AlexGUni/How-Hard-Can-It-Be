package com.mygdx.game.Components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Entitys.Player;
import com.mygdx.game.Entitys.Ship;
import com.mygdx.game.Managers.EntityManager;
import com.mygdx.game.Managers.RenderingManager;

import static com.mygdx.utils.Constants.HALF_DIMENSIONS;

/**
 * Responsible for the keyboard control of the player
 */
public class PlayerController extends Component {
    private Player player;
    private float speed;

    public PlayerController() {
        super();
        type = ComponentType.PlayerController;
        setRequirements(ComponentType.RigidBody);
    }

    public PlayerController(Player player, float speed) {
        this();
        this.player = player;
        this.speed = speed;
    }

    @Override
    public void update() {
        super.update();
        final float s = speed;

        Vector2 dir = new Vector2(0, 0);

        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) {
            dir.y += 1;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)) {
            dir.y -= 1;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) {
            dir.x -= 1;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) {
            dir.x += 1;
        }

        ((Ship) parent).setShipDirection(dir);

        dir.scl(s);

        RigidBody rb = parent.getComponent(RigidBody.class);
        rb.setVelocity(dir);

        RenderingManager.getCamera().position.set(new Vector3(player.getPosition(), 0.0f));
        RenderingManager.getCamera().update();

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            int x = Gdx.input.getX() - 45;
            int y = Gdx.input.getY() + 45;

            // in range 0 to VIEWPORT 0, 0 bottom left
            Vector2 delta = new Vector2(x, y);
            delta.sub(HALF_DIMENSIONS); // center 0, 0
            delta.nor();
            delta.y *= -1;
            // unit dir to fire
            ((Ship) parent).shoot(delta);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            // unit dir to fire
            ((Ship) parent).shoot();
        }
    }
}
