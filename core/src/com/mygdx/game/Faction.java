package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

/**
 * Represents a rival college as an in-game faction.
 */
public class Faction {
    private String name;
    private String shipColour;
    private Vector2 position;

    public Faction() {
        name = "Faction not named";
        shipColour = "";
    }

    /**
     * Creates a faction with the specified name, colour, and in-game location.
     *
     * @param name name of faction
     * @param colour colour name (used as prefix to retrieve ship sprites)
     * @param pos 2D vector location
     */
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
