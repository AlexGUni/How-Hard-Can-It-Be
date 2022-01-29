package com.mygdx.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Entitys.Player;
import com.mygdx.game.Managers.GameManager;
import com.mygdx.game.PirateGame;

import static com.mygdx.utils.Constants.VIEWPORT_HEIGHT;

/**
 * Contains widgets defining the game end screen.
 */
public class EndScreen extends Page {
    Label wonText;
    Label playerStats;
    public EndScreen(PirateGame game) {
        super(game);
    }

    /**
     * Set game end screen status to report a win.
     */
    public void win() {
        wonText.setText("Congrats You Have Won");
    }

    /**
     * Create game end screen widgets, initialised to game loss status.
     */
    @Override
    protected void CreateActors() {
        Table t = new Table();
        Pixmap bgPixmap = new Pixmap(1, 1, Pixmap.Format.RGB565);
        bgPixmap.setColor(Color.RED);
        bgPixmap.fill();
        TextureRegionDrawable textureRegionDrawableBg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));

        float space = VIEWPORT_HEIGHT * 0.25f;

        t.setBackground(textureRegionDrawableBg);
        t.setFillParent(true);
        actors.add(t);
        wonText = new Label("You have lost", parent.skin);
        wonText.setFontScale(2);
        t.top();
        t.add(wonText).top().spaceBottom(space);
        t.row();
        playerStats = new Label("Player Stats:\n", parent.skin);
        t.add(playerStats).spaceBottom(space);
        t.row();
        TextButton b = new TextButton("Exit", parent.skin);
        b.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
                System.exit(0);
            }
        });
        t.add(b);
    }

    /**
     * Get player stats such as plunder etc. and display game end screen.
     */
    @Override
    public void show() {
        super.show();
        Player p = GameManager.getPlayer();
        String stats = String.format("Health: %s\nAmmo: %s\nPlunder: %s", p.getHealth(), p.getAmmo(), p.getPlunder());
        playerStats.setText(stats);
    }
}
