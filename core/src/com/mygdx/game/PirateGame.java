package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Components.ComponentEvent;
import com.mygdx.game.Entitys.DebugText;
import com.mygdx.game.Managers.*;
import com.mygdx.game.UI.GameScreen;
import com.mygdx.game.UI.MenuScreen;
import com.mygdx.game.UI.PageManager;

import static com.mygdx.utils.Constants.*;

public class PirateGame extends ApplicationAdapter {
	PageManager g;
	@Override
	public void create () {
		g = new PageManager();
		g.create();
	}

	@Override
	public void render () {
		g.render();
	}
	
	@Override
	public void dispose () {
		g.dispose();
	}
}
