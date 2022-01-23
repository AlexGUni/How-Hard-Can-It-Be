package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.mygdx.game.UI.PageManager;

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
