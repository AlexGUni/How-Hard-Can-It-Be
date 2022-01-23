package com.mygdx.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Components.ComponentEvent;
import com.mygdx.game.Entitys.DebugText;
import com.mygdx.game.Entitys.Player;
import com.mygdx.game.Managers.*;
import com.mygdx.game.PirateGame;

import static com.mygdx.utils.Constants.*;

public class GameScreen extends Page {
    private Label healthLabel;
    private Label dosh;
    public GameScreen(PirateGame parent) {
        super(parent);
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
    public void render(float delta) {
        ScreenUtils.clear(BACKGROUND_COLOUR.x, BACKGROUND_COLOUR.y, BACKGROUND_COLOUR.z, 1);

        EntityManager.raiseEvents(ComponentEvent.Update, ComponentEvent.Render);

        accumulator += EntityManager.getDeltaTime();

        while (accumulator >= PHYSICS_TIME_STEP) {
            PhysicsManager.update();
            accumulator -= PHYSICS_TIME_STEP;
        }

        GameManager.update();

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
            System.exit(0);
        }
        super.render(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
        ResourceManager.cleanUp();
        EntityManager.cleanUp();
        RenderingManager.cleanUp();
        PhysicsManager.cleanUp();
    }

    @Override
    public void resize(int width, int height) {
        //((Table) actors.get(0)).setFillParent(false);
        super.resize(width, height);
        OrthographicCamera cam = RenderingManager.getCamera();
        cam.viewportWidth = width / ZOOM;
        cam.viewportHeight = height / ZOOM;
        cam.update();

       // ((Table) actors.get(0)).setFillParent(true);
    }

    @Override
    protected void update() {
        super.update();
        Player p = GameManager.getPlayer();

        healthLabel.setText(String.valueOf(p.getHealth()));
        dosh.setText(String.valueOf(p.getPlunder()));
    }

    @Override
    protected void CreateActors() {
        Table table = new Table();
        table.setFillParent(true);
        actors.add(table);


        table.add(new Image(parent.skin.getDrawable("stick"))).top().left().size(1.25f * TILE_SIZE);
        healthLabel = new Label("N/A", parent.skin);
        table.add(healthLabel).top().left().size(50);

        table.row();

        table.add(new Image(parent.skin.getDrawable("coin"))).top().left().size(1.25f * TILE_SIZE);
        dosh = new Label("N/A", parent.skin);
        table.add(dosh).top().left().size(50);

        // button.addListener(new ChangeListener() {
        //     public void changed (ChangeEvent event, Actor actor) {
        //         System.out.println("Clicked! Is checked: " + button.isChecked());
        //         button.setText("Good job!");
        //     }
        // });

        table.add(new Image(parent.skin.getDrawable("key"))).size(128);

        table.top().left();
    }
}
