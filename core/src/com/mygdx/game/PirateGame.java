package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import static com.mygdx.utils.Constants.BACKGROUND_COLOUR;

public class PirateGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background_img;
	Texture p_t;
	Sprite p;
	Player player;
	@Override
	public void create () {
		batch = new SpriteBatch();
		background_img = new Texture("background.png");
		player = new Player("ship.png");
		p_t = new Texture("ship.png");
		p = new Sprite(p_t);
	}

	@Override
	public void render () {
		ScreenUtils.clear(BACKGROUND_COLOUR.x, BACKGROUND_COLOUR.y, BACKGROUND_COLOUR.z, 1);
		batch.begin();
		// batch.draw(background_img, 0, 0);
		player.draw(batch);
		// p.draw(batch);
		batch.end();


		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			Gdx.app.exit();
			System.exit(0);
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		background_img.dispose();
		p_t.dispose();
		player.cleanUp();
	}
}
