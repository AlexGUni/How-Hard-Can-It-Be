package com.mygdx.game.AI;

import com.badlogic.gdx.math.Vector2;

public class Node {
    private final Vector2 position;
    public float cost;

    public Node(float x, float y) {
        position = new Vector2(x, y);
        cost = -1;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void set(float x, float y) {
        position.set(x, y);
    }
}
