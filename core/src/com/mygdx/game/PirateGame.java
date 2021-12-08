package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Components.ComponentEvent;
import com.mygdx.game.Entitys.Player;
import com.mygdx.game.Entitys.WorldMap;
import com.mygdx.utils.ResourceManager;

import static com.mygdx.utils.Constants.*;

public class PirateGame extends ApplicationAdapter {
	OrthographicCamera camera;
	Player player;
	WorldMap worldMap;

	@Override
	public void create () {
		INIT_CONSTANTS();

		int id_ship = ResourceManager.addTexture("ship.png");

		ResourceManager.loadAssets();

		worldMap = new WorldMap("Map.tmx");
		player = new Player(id_ship, 10);

		camera = new OrthographicCamera();
		camera.viewportWidth  = VIEWPORT_WIDTH;
		camera.viewportHeight = VIEWPORT_HEIGHT;
		camera.update();

		EntityManager.setCamera(camera);


		EntityManager.raiseEvents(ComponentEvent.Awake, ComponentEvent.Start);
	}

	@Override
	public void render () {
		ScreenUtils.clear(BACKGROUND_COLOUR.x, BACKGROUND_COLOUR.y, BACKGROUND_COLOUR.z, 1);

		EntityManager.raiseEvents(ComponentEvent.Update, ComponentEvent.Render);

		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			Gdx.app.exit();
			System.exit(0);
		}
	}
	
	@Override
	public void dispose () {
		ResourceManager.cleanUp();
		EntityManager.cleanUp();
	}
}
