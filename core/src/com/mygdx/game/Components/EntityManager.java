package com.mygdx.game.Components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entitys.Entity;

import java.util.ArrayList;

/**
 * Responsible for Managing the entity and component events
 */
public final class EntityManager {
    private static ArrayList<Entity> entities;
    private static ArrayList<Component> components;
    private static boolean inited = false;
    private static SpriteBatch primaryBatch;

    public static void Initialize() {
        primaryBatch = new SpriteBatch();
        primaryBatch.enableBlending();
        entities = new ArrayList<>();
        components = new ArrayList<>();
        inited = true;
    }

    public static SpriteBatch getBatch() {
        return primaryBatch;
    }

    public static void addComponent(Component c){
        tryInit();
        components.add(c);
    }

    public static void addEntity(Entity e){
        tryInit();
        entities.add(e);
    }

    /**
     * raises the appropriate events for each entity's component
     * @param comps calls the events left to right
     */
    public static void raiseEvents(ComponentEvent... comps){
        for (Entity e : entities){
            e.raiseEvents(comps);
        }
    }

    /**
     * Cleans up all entities and components. Disposes of the primary sprite batch
     */
    public static void cleanUp(){
        tryInit();
        for (Entity e : entities){
            e.cleanUp();
        }
        for (Component c : components){
            c.cleanUp();
        }
        primaryBatch.dispose();
    }

    private static void tryInit(){
        if(!inited){
            Initialize();
        }
    }
}
