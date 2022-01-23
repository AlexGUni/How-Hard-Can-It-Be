package com.mygdx.game.UI;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PageManager extends Game {
    public MenuScreen menu;
    public GameScreen game;
    public Stage stage;
    public Skin skin;

    public  PageManager() {
    }

    @Override
    public void create() {
        stage = new Stage(new ScreenViewport());
        createSkin();
        menu = new MenuScreen(this);
        game = new GameScreen(this);
        setScreen(menu);
    }

    @Override
    public void dispose() {
        menu.dispose();
        game.dispose();
        stage.dispose();
        skin.dispose();
    }

    private void createSkin() {
        skin = new Skin(Gdx.files.internal("UISkin/skin.json"));
    }
}
