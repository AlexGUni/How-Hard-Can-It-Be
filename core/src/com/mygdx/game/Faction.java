package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Faction {
    private String name;
    private String shipColour;
    private Vector2 position;
    public Faction() {
        name = "Faction not named";
        shipColour = "";
    }
    public Faction(String name, String colour, Vector2 pos) {
        this();
        this.name = name;
        this.shipColour = colour;
        this.position = pos;
    }

    public String getName() {
        return name;
    }

    public String getColour() {
        return shipColour;
    }

    public Vector2 getPosition() {
        return position;
    }
}
