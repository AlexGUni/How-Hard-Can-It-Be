package com.mygdx.game.Components;

import com.mygdx.game.Faction;
import com.mygdx.game.Managers.GameManager;

public class Pirate extends Component {
    private int factionId;
    private int plunder;

    public Pirate() {
        super();
        plunder = GameManager.getSettings().get("starting").getInt("plunder");
        factionId = 1;
    }

    public int getPlunder() {
        return plunder;
    }

    public void addPlunder(int money) {
        plunder += money;
    }

    public Faction getFaction() {
        return GameManager.getFaction(factionId);
    }

    public void setFactionId(int factionId) {
        this.factionId = factionId;
    }
}
