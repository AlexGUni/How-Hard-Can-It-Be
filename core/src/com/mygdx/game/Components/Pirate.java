package com.mygdx.game.Components;

public class Pirate extends Component {
    private int factionId;
    private float plunder;

    public Pirate() {
        super();
    }

    public float getPlunder() {
        return plunder;
    }

    public void addPlunder(float money) {
        plunder += money;
    }

    public int getFactionId() {
        return factionId;
    }

    public void setFactionId(int factionId) {
        this.factionId = factionId;
    }
}
