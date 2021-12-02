package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.PirateGame;
import static com.mygdx.utils.Constants.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = VIEWPORT_TITLE;
		cfg.width = VIEWPORT_WIDTH;
		cfg.height = VIEWPORT_HEIGHT;
		cfg.fullscreen = true;
		new LwjglApplication(new PirateGame(), cfg);
	}
}
