package com.mygdx.game.Components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Entitys.Player;
import com.mygdx.game.Managers.EntityManager;
import com.mygdx.game.Managers.RenderingManager;

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
        float s = speed * EntityManager.getDeltaTime();

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
        player.setPos(pos.add(deltaP.scl(s)));
        RenderingManager.getCamera().position.set(new Vector3(player.getPos(), 0.0f));
        RenderingManager.getCamera().update();
    }
}
