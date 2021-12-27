package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.PirateGame;
import static com.mygdx.utils.Constants.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		INIT_CONSTANTS();

		Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
		cfg.setTitle(VIEWPORT_TITLE);

		if(FULLSCREEN){
			cfg.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
		}
		else{
			cfg.setWindowedMode(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
		}

		cfg.useVsync(false);
		cfg.setForegroundFPS(0);

		new Lwjgl3Application(new PirateGame(), cfg);
	}
}
