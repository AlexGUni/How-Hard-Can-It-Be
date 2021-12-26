package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Components.ComponentEvent;
import com.mygdx.game.Entitys.DebugText;
import com.mygdx.game.Entitys.WorldMap;
import com.mygdx.game.Managers.*;

import static com.mygdx.utils.Constants.*;

public class PirateGame extends ApplicationAdapter {

	@Override
	public void create () {
		INIT_CONSTANTS();
		PhysicsManager.Initialize(false);

		int id_ship = ResourceManager.addTexture("ship.png");
		int id_map = ResourceManager.addTileMap("Map.tmx");
		int atlas_id = ResourceManager.addTextureAtlas("Boats.txt");
		int extras_id = ResourceManager.addTextureAtlas("UISkin/skin.atlas");


		ResourceManager.loadAssets();

		new WorldMap(id_map);

		GameManager.CreatePlayer();
		GameManager.CreateEnemy(2);
		GameManager.CreateEnemy(3);
		GameManager.CreateEnemy(4);
		GameManager.CreateEnemy(5);

		DebugText t = new DebugText();

		EntityManager.raiseEvents(ComponentEvent.Awake, ComponentEvent.Start);

		/*TileMapGraph g = new TileMapGraph(worldMap.getTileMap());

		QueueFIFO<Vector2> path = g.findOptimisedPath(10, 10, 13, 8);*/
	}

	private float accumulator;
	@Override
	public void render () {
		ScreenUtils.clear(BACKGROUND_COLOUR.x, BACKGROUND_COLOUR.y, BACKGROUND_COLOUR.z, 1);

		EntityManager.raiseEvents(ComponentEvent.Update, ComponentEvent.Render);

		accumulator += EntityManager.getDeltaTime();

		while (accumulator >= PHYSICS_TIME_STEP) {
			PhysicsManager.update();
			accumulator -= PHYSICS_TIME_STEP;
		}

		GameManager.update();

		UIManager.update();
		UIManager.render();


		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			Gdx.app.exit();
			System.exit(0);
		}
	}
	
	@Override
	public void dispose () {
		ResourceManager.cleanUp();
		EntityManager.cleanUp();
		RenderingManager.cleanUp();
		PhysicsManager.cleanUp();
		UIManager.cleanUp();
	}

	@Override
	public void resize(int width, int height) {
		UPDATE_VIEWPORT(width, height);
		OrthographicCamera cam = RenderingManager.getCamera();
		cam.viewportWidth = width;
		cam.viewportHeight = height;
		cam.update();

		UIManager.resize(width, height);
	}
}
