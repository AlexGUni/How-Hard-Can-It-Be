package com.mygdx.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.Managers.UIManager;

import java.util.ArrayList;

import static com.mygdx.utils.Constants.UPDATE_VIEWPORT;

public abstract class Page extends ScreenAdapter {
    protected static Stage mainStage;
    protected static Skin skin;
    protected ArrayList<Actor> actors;
    public Page() {
        mainStage = UIManager.getStage();
        skin = UIManager.getSkin();
        actors = new ArrayList<>();
        CreateActors();
    }

    protected abstract void CreateActors();

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(mainStage);
        for (Actor a : actors) {
            mainStage.addActor(a);
        }
    }
    @Override
    public void render(float delta) {
        update();
        super.render(delta);
        mainStage.act(delta);
        mainStage.draw();
    }

    @Override
    public void hide() {
        super.hide();
        Gdx.input.setInputProcessor(null);
        mainStage.clear();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        UPDATE_VIEWPORT(width, height);
        UIManager.resize(width, height);
    }

    protected void update() {

    }
}
