package com.mygdx.game.Entitys;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ObjectMap;
import com.mygdx.game.Components.*;
import com.mygdx.game.Managers.RenderLayer;
import com.mygdx.game.Managers.ResourceManager;
import com.mygdx.game.Physics.PhysicsBodyType;

import java.util.Objects;

public class Ship extends Entity {
    private static int shipCount = 0;
    private static ObjectMap<Vector2, String> shipDirections;

    private final Vector2 currentDir;

    public Ship() {
        super(4);
        currentDir = new Vector2();
        setName("Ship (" + shipCount++ + ")");

        shipDirections = new ObjectMap<>();
        shipDirections.put(new Vector2(0, 1), "-up");
        shipDirections.put(new Vector2(0, -1), "-down");
        shipDirections.put(new Vector2(1, 0), "-right");
        shipDirections.put(new Vector2(-1, 0), "-left");
        shipDirections.put(new Vector2(1, 1), "-ur");
        shipDirections.put(new Vector2(-1, 1), "-ul");
        shipDirections.put(new Vector2(1, -1), "-dr");
        shipDirections.put(new Vector2(-1, -1), "-dl");

        Transform t = new Transform();
        t.setPosition(800, 800);
        Renderable r = new Renderable(3, "white-up", RenderLayer.Transparent);
        RigidBody rb = new RigidBody(PhysicsBodyType.Dynamic, r, t);
        Pirate p = new Pirate();

        addComponents(t, r, rb, p);
    }

    public void plunder(int money) {
        getComponent(Pirate.class).addPlunder(money);
    }

    public void setFaction(int factionId) {
        getComponent(Pirate.class).setFactionId(factionId);
        setShipDirection("-up");
    }

    public String getShipDirection(Vector2 dir) {
        if (!currentDir.equals(dir) && shipDirections.containsKey(dir)){
            currentDir.set(dir);
            return shipDirections.get(dir);
        }
        return "";
    }

    private String getColour() {
        return getComponent(Pirate.class).getFaction().getColour();
    }

    public void setShipDirection(String direction) {
        if(Objects.equals(direction, "")) {
            return;
        }
        Renderable r = getComponent(Renderable.class);
        Sprite s = ResourceManager.getSprite(3, getColour() + direction);
        r.getSprite().setU(s.getU());
        r.getSprite().setV(s.getV());
        r.getSprite().setU2(s.getU2());
        r.getSprite().setV2(s.getV2());
    }

    public int getHealth() {
        return getComponent(Pirate.class).getHealth();
    }
    public int getPlunder() {
        return getComponent(Pirate.class).getPlunder();
    }

    public void shoot() {
        getComponent(Pirate.class).shoot(currentDir);
    }
}
