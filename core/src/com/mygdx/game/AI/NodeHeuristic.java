package com.mygdx.game.AI;

import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.math.Vector2;

public class NodeHeuristic implements Heuristic<Node> {
    @Override
    public float estimate(Node node, Node endNode) {
        Vector2 a = node.getPosition();
        Vector2 b = endNode.getPosition();
        return Vector2.dst2(a.x, a.y, b.x, b.y);
    }
}
