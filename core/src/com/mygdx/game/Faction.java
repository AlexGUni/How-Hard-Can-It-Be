package com.mygdx.game;

public class Faction {
    private String name;
    private String shipColour;
    private boolean isAlive;
    public Faction() {
        name = "Faction not named";
        shipColour = "";
    }
    public Faction(String name, String colour) {
        this();
        this.name = name;
        this.shipColour = colour;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public String getColour() {
        return shipColour;
    }
}
