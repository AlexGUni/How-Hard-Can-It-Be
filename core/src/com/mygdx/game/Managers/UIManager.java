package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.Entitys.Player;

public final class UIManager {
    private static boolean initialized = false;
    private static Stage stage;
    private static Skin skin;
    private static Label healthLabel;
    private static Label dosh;


    public static void Initialize() {
        initialized = true;

        stage = new Stage();

        createSkin();

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

        // final TextButton button = new TextButton("Click me!", skin);
        // table.add(button).top().left();

        table.add(new Image(skin.getDrawable("stick"))).top().left();
        healthLabel = new Label("N/A", skin);
        table.add(healthLabel).top().left().size(24);

        table.row();

        table.add(new Image(skin.getDrawable("coin"))).top().left();
        dosh = new Label("N/A", skin);
        table.add(dosh).top().left().size(24);

        // button.addListener(new ChangeListener() {
        //     public void changed (ChangeEvent event, Actor actor) {
        //         System.out.println("Clicked! Is checked: " + button.isChecked());
        //         button.setText("Good job!");
        //     }
        // });

        table.add(new Image(skin.getDrawable("key"))).size(128);

        table.top().left();
        table.setDebug(true);
    }

    public static void update() {
        tryInit();

        stage.act(EntityManager.getDeltaTime());
        Player p = GameManager.getPlayer();

        healthLabel.setText(String.valueOf(p.getHealth()));
        dosh.setText(String.valueOf(p.getPlunder()));
    }

    public static void render() {
        tryInit();
        stage.draw();
    }

    public static void resize(int width, int height) {
        tryInit();
        stage.getViewport().update(width, height, true);
    }

    private static void createSkin() {
        skin = new Skin(Gdx.files.internal("UISkin/skin.json"));
    }

    private static void tryInit() {
        if(!initialized) {
            Initialize();
        }
    }

    public static void cleanUp() {
        tryInit();
        stage.dispose();
        skin.dispose();
    }
}
