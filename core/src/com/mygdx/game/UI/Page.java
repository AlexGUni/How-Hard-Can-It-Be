package com.mygdx.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.PirateGame;

import java.util.ArrayList;

import static com.mygdx.utils.Constants.UPDATE_VIEWPORT;

public abstract class Page extends ScreenAdapter {
    PirateGame parent;


    protected ArrayList<Actor> actors;

    public Page(PirateGame parent) {
        this.parent = parent;
        actors = new ArrayList<>();
        CreateActors();
    }

    protected abstract void CreateActors();

    @Override
    public void show() {
        // button.addListener(new ChangeListener() {
        //     public void changed (ChangeEvent event, Actor actor) {
        //         System.out.println("Clicked! Is checked: " + button.isChecked());
        //         button.setText("Good job!");
        //     }
        // });
        super.show();
        Gdx.input.setInputProcessor(parent.stage);
        for (Actor a : actors) {
            parent.stage.addActor(a);
        }
    }

    @Override
    public void render(float delta) {
        update();
        super.render(delta);
        parent.stage.act(delta);
        parent.stage.draw();
    }

    @Override
    public void hide() {
        super.hide();
        Gdx.input.setInputProcessor(null);
        parent.stage.clear();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        UPDATE_VIEWPORT(width, height);
        parent.stage.getCamera().viewportWidth = width;
        parent.stage.getCamera().viewportHeight = height;
        parent.stage.getViewport().update(width, height, true);
    }

    protected void update() {

    }
}
