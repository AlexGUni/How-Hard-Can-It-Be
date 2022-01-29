package com.mygdx.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Components.ComponentEvent;
import com.mygdx.game.Entitys.Player;
import com.mygdx.game.Managers.*;
import com.mygdx.game.PirateGame;
import com.mygdx.game.Quests.Quest;

import static com.mygdx.utils.Constants.*;

/**
 * Contains widgets for game UI, loads game textures, basically boots up the game.
 */
public class GameScreen extends Page {
    private Label healthLabel;
    private Label dosh;
    private Label ammo;
    private Label questDesc; //TODO: was there any need for it to be final?

    /**
     * Boots up the actual game: starts PhysicsManager, GameManager, EntityManager,
     * loads texture atlases into ResourceManager. Draws quest and control info.
     *
     * @param parent PirateGame UI screen container
     */
    public GameScreen(PirateGame parent) {
        super(parent);
        INIT_CONSTANTS();
        PhysicsManager.Initialize(true);

        int id_ship = ResourceManager.addTexture("ship.png");
        int id_map = ResourceManager.addTileMap("Map.tmx");
        int atlas_id = ResourceManager.addTextureAtlas("Boats.txt");
        int extras_id = ResourceManager.addTextureAtlas("UISkin/skin.atlas");
        int buildings_id = ResourceManager.addTextureAtlas("Buildings.txt");

        ResourceManager.loadAssets();

        GameManager.SpawnGame(id_map);
        //QuestManager.addQuest(new KillQuest(c));

        EntityManager.raiseEvents(ComponentEvent.Awake, ComponentEvent.Start);

        //TODO: extracted methods - why tf does game crash if we put them in CreateActors?
        drawQuestHint(parent);
        drawControlsHint(parent);
    }

    /**
     * Draw current quest info box.
     *
     * @param parent PirateGame UI screen container
     */
    private void drawQuestHint(PirateGame parent) {
        //final Label questDesc;
        Window questWindow = new Window("Current Quest", parent.skin);

        Quest q = QuestManager.currentQuest();
        Table t = new Table();
        t.add(new Label(q.getName(), parent.skin));
        t.row();
        questDesc = new Label(q.getDescription(), parent.skin);

        t.add(questDesc).left();
        questWindow.add(t);
        actors.add(questWindow);
    }

    /**
     * Draw hint screen to remind player of keybindings.
     *
     * @param parent PirateGame UI screen container
     */
    private void drawControlsHint(PirateGame parent) {
        Table t1 = new Table();
        t1.top().right();
        t1.setFillParent(true);
        actors.add(t1);

        Window tutWindow = new Window("Controls", parent.skin);
        Table table = new Table();
        tutWindow.add(table);
        t1.add(tutWindow);

        table.add(new Label("Move with", parent.skin)).top().left();
        table.add(new Image(parent.skin, "key-w"));
        table.add(new Image(parent.skin, "key-s"));
        table.add(new Image(parent.skin, "key-a"));
        table.add(new Image(parent.skin, "key-d"));
        table.row();
        table.add(new Label("Shoot in direction of mouse", parent.skin));
        table.add(new Image(parent.skin, "space"));
        table.row();
        table.add(new Label("Quit", parent.skin)).left();
        table.add(new Image(parent.skin, "key-esc"));
    }

    private float accumulator;

    //TODO: I think I get it but I don't really get it...
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

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
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

    /**
     * Resize camera, effectively setting the viewport to display game assets
     * at pixel ratios other than 1:1.
     *
     * @param width of camera viewport
     * @param height of camera viewport
     */
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

    /**
     * Update the UI with new values for health, quest status, etc.
     */
    @Override
    protected void update() {
        super.update();
        Player p = GameManager.getPlayer();

        healthLabel.setText(String.valueOf(p.getHealth()));
        dosh.setText(String.valueOf(p.getPlunder()));
        ammo.setText(String.valueOf(p.getAmmo()));
        if (QuestManager.currentQuest().isCompleted()) {
            questDesc.setText("Completed");
            parent.end.win();
            parent.setScreen(parent.end);
        }
    }

    /**
     * Draw UI elements showing player health, plunder, and ammo.
     */
    @Override
    protected void CreateActors() {
        Table table = new Table();
        table.setFillParent(true);
        actors.add(table);

        table.add(new Image(parent.skin.getDrawable("heart"))).top().left().size(1.25f * TILE_SIZE);
        healthLabel = new Label("N/A", parent.skin);
        table.add(healthLabel).top().left().size(50);

        table.row();
        table.setDebug(true);

        table.add(new Image(parent.skin.getDrawable("coin"))).top().left().size(1.25f * TILE_SIZE);
        dosh = new Label("N/A", parent.skin);
        table.add(dosh).top().left().size(50);

        table.row();

        table.add(new Image(parent.skin.getDrawable("ball"))).top().left().size(1.25f * TILE_SIZE);
        ammo = new Label("N/A", parent.skin);
        table.add(ammo).top().left().size(50);

        table.top().left();
    }
}
