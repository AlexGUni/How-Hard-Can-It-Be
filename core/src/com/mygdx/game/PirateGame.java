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
	Skin skin;
	Stage stage;

	@Override
	public void create () {
		INIT_CONSTANTS();
		PhysicsManager.Initialize(false);

		int id_ship = ResourceManager.addTexture("ship.png");
		int id_map = ResourceManager.addTileMap("Map.tmx");
		int arial_gen_id = ResourceManager.addFontGenerator("arial.ttf");
		// int arial_24_id = ResourceManager.createFont(arial_gen_id, 24);
		int atlas_id = ResourceManager.addTextureAtlas("Boats.txt");


		ResourceManager.loadAssets();

		WorldMap worldMap = new WorldMap(id_map);

		GameManager.CreatePlayer();
		GameManager.CreateEnemy(2);
		GameManager.CreateEnemy(3);

		//DebugText t = new DebugText();

		EntityManager.raiseEvents(ComponentEvent.Awake, ComponentEvent.Start);

		/*TileMapGraph g = new TileMapGraph(worldMap.getTileMap());

		QueueFIFO<Vector2> path = g.findOptimisedPath(10, 10, 13, 8);*/

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		// A skin can be loaded via JSON or defined programmatically, either is fine. Using a skin is optional but strongly
		// recommended solely for the convenience of getting a texture, region, etc as a drawable, tinted drawable, etc.
		skin = new Skin();

		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		// Store the default libGDX font under the name "default".
		skin.add("default", new BitmapFont());

		// Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);

		// Create a table that fills the screen. Everything else will go inside this table.
		Table table = new Table();
		table.setFillParent(true);
		stage.addActor(table);

		// Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
		final TextButton button = new TextButton("Click me!", skin);
		table.add(button);

		// Add a listener to the button. ChangeListener is fired when the button's checked state changes, eg when clicked,
		// Button#setChecked() is called, via a key press, etc. If the event.cancel() is called, the checked state will be reverted.
		// ClickListener could have been used, but would only fire when clicked. Also, canceling a ClickListener event won't
		// revert the checked state.
		button.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				System.out.println("Clicked! Is checked: " + button.isChecked());
				button.setText("Good job!");
			}
		});

		// Add an image actor. Have to set the size, else it would be the size of the drawable (which is the 1x1 texture).
		table.add(new Image(skin.newDrawable("white", Color.RED))).size(64);
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


		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();


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

		stage.dispose();
		skin.dispose();
	}

	@Override
	public void resize(int width, int height) {
		UPDATE_VIEWPORT(width, height);
		OrthographicCamera cam = RenderingManager.getCamera();
		cam.viewportWidth = width;
		cam.viewportHeight = height;
		cam.update();

		stage.getViewport().update(width, height, true);
	}
}
