package com.mygdx.game.UI;

import com.badlogic.gdx.Game;

public class PageManager extends Game {
    MenuScreen menu;
    GameScreen game;
    @Override
    public void create() {
        menu = new MenuScreen();
        game = new GameScreen();
        setScreen(game);
    }

    @Override
    public void dispose() {
        menu.dispose();
        game.dispose();
    }
}
