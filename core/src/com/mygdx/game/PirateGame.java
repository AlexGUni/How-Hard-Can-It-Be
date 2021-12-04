package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.utils.ResourceManager;

import static com.mygdx.utils.Constants.*;

public class PirateGame extends ApplicationAdapter {
	OrthographicCamera camera;
	SpriteBatch batch;
	Sprite p;
	Player player;
	Texture background_img;


	@Override
	public void create () {
		INIT_CONSTANTS();

		ResourceManager.addTexture("ship.png");
		ResourceManager.addTexture("background.png");

		ResourceManager.loadAssets();

		batch = new SpriteBatch();
		batch.enableBlending();

		background_img = ResourceManager.getTexture("background.png");

		player = new Player("ship.png");

		camera = new OrthographicCamera();
		camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
	}

	@Override
	public void render () {
		ScreenUtils.clear(BACKGROUND_COLOUR.x, BACKGROUND_COLOUR.y, BACKGROUND_COLOUR.z, 1);

		camera.position.set(new Vector3(player.getPos(), 0.0f));
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		batch.draw(background_img, 0, 0);
		player.draw(batch);

		batch.end();


		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			Gdx.app.exit();
			System.exit(0);
		}

		float speed = HALF_VIEWPORT_WIDTH * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.W)){
			Vector2 pos = player.getPos();
			pos.y += speed;
			player.setPos(pos);
		}

		if(Gdx.input.isKeyPressed(Input.Keys.S)){
			Vector2 pos = player.getPos();
			pos.y -= speed;
			player.setPos(pos);
		}

		if(Gdx.input.isKeyPressed(Input.Keys.A)){
			Vector2 pos = player.getPos();
			pos.x -= speed;
			player.setPos(pos);
		}

		if(Gdx.input.isKeyPressed(Input.Keys.D)){
			Vector2 pos = player.getPos();
			pos.x += speed;
			player.setPos(pos);
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();

		player.cleanUp();

		ResourceManager.cleanUp();
	}
}
