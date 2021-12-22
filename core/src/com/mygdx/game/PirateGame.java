package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Components.ComponentEvent;
import com.mygdx.game.Entitys.DebugText;
import com.mygdx.game.Entitys.Enemy;
import com.mygdx.game.Entitys.Player;
import com.mygdx.game.Entitys.WorldMap;
import com.mygdx.game.Managers.EntityManager;
import com.mygdx.game.Managers.PhysicsManager;
import com.mygdx.game.Managers.RenderingManager;
import com.mygdx.game.Managers.ResourceManager;

import static com.mygdx.utils.Constants.*;

public class PirateGame extends ApplicationAdapter {
	@Override
	public void create () {
		INIT_CONSTANTS();
		PhysicsManager.Initialize(false);

		int id_ship = ResourceManager.addTexture("ship.png");
		int id_map = ResourceManager.addTileMap("Map.tmx");
		int arial_gen_id = ResourceManager.addFontGenerator("arial.ttf");
		int arial_24_id = ResourceManager.createFont(arial_gen_id, 24);
		int atlas_id = ResourceManager.addTextureAtlas("Boats.txt");


		ResourceManager.loadAssets();

		WorldMap worldMap = new WorldMap(id_map);
		Player player = new Player(id_ship, 10000);
		Enemy e1 = new Enemy();
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
	}

	@Override
	public void resize(int width, int height) {
		UPDATE_VIEWPORT(width, height);
		OrthographicCamera cam = RenderingManager.getCamera();
		cam.viewportWidth = width;
		cam.viewportHeight = height;
		cam.update();
	}
}
