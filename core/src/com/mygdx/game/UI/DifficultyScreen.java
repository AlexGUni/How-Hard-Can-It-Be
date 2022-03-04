package com.mygdx.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Managers.ResourceManager;
import com.mygdx.game.PirateGame;

import java.awt.*;

import static com.mygdx.utils.Constants.VIEWPORT_HEIGHT;


public class DifficultyScreen extends Page {
    public DifficultyScreen(PirateGame parent) {super(parent);}

    @Override
    protected void CreateActors() {
        Table t = new Table();
        t.setFillParent(true);

        float space = VIEWPORT_HEIGHT * 0.25f;

        Label l = new Label("Select difficulty:", parent.skin);
        l.setFontScale(2);
        t.add(l).top().spaceBottom(space * 0.5f);
        t.row();


        TextButton easy = new TextButton("Easy", parent.skin);
        easy.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.menu.difficulty = 0;
                parent.setScreen(parent.menu);
            }
        });
        t.add(easy).top().size(100, 25).spaceBottom(space);
        t.row();

        TextButton medium = new TextButton("Medium", parent.skin);
        medium.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.menu.difficulty = 1;
                parent.setScreen(parent.menu);
            }
        });
        t.add(medium).top().size(100, 25).spaceBottom(space);
        t.row();

        TextButton hard = new TextButton("Hard", parent.skin);
        hard.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.menu.difficulty = 2;
                parent.setScreen(parent.menu);
            }
        });
        t.add(hard).size(100, 25).top().spaceBottom(space);

        t.top();

        actors.add(t);
    }

    @Override
    public void show() {
        super.show();
    }


    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        Table t = (Table) actors.get(0);
        t.setBackground(new TextureRegionDrawable(ResourceManager.getTexture("menuBG.jpg"))); // prevent the bg being stretched
    }
}

