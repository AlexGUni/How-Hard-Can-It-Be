package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Components.ComponentEvent;
import com.mygdx.game.Entitys.DebugText;
import com.mygdx.game.Managers.*;

import static com.mygdx.utils.Constants.*;

public class PirateGame extends ApplicationAdapter {
	Box2DDebugRenderer b2d;
	@Override
	public void create () {
		b2d = new Box2DDebugRenderer(true, true, true, true, true, true);

		INIT_CONSTANTS();
		PhysicsManager.Initialize(false);

		int id_ship = ResourceManager.addTexture("ship.png");
		int id_map = ResourceManager.addTileMap("Map.tmx");
		int atlas_id = ResourceManager.addTextureAtlas("Boats.txt");
		int extras_id = ResourceManager.addTextureAtlas("UISkin/skin.atlas");
		int buildigns_id = ResourceManager.addTextureAtlas("Buildings.txt");


		ResourceManager.loadAssets();



		GameManager.SpawnGame(id_map);
		//QuestManager.addQuest(new KillQuest(c));

		DebugText t = new DebugText();

		EntityManager.raiseEvents(ComponentEvent.Awake, ComponentEvent.Start);
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

		b2d.render(PhysicsManager.box2DWorld, RenderingManager.getCamera().combined);


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

		b2d.dispose();
	}

	@Override
	public void resize(int width, int height) {
		UPDATE_VIEWPORT(width, height);
		OrthographicCamera cam = RenderingManager.getCamera();
		cam.viewportWidth = width / ZOOM;
		cam.viewportHeight = height / ZOOM;
		cam.update();

		UIManager.resize(width, height);
	}
}
