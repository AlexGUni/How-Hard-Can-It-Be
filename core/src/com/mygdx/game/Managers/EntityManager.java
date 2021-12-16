package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Components.Component;
import com.mygdx.game.Components.ComponentEvent;
import com.mygdx.game.Entitys.Entity;

import java.util.ArrayList;

/**
 * Responsible for Managing the entity and component events. Entity's can be accessed by a String name
 */
public final class EntityManager {
    private static ArrayList<String> entityNames;
    private static ArrayList<Entity> entities;
    private static ArrayList<Component> components;
    private static boolean initialized = false;
    private static InputManager inpManager;

    public static void Initialize() {
        entityNames = new ArrayList<>();
        inpManager = new InputManager();
        entities = new ArrayList<>();
        components = new ArrayList<>();
        initialized = true;
        Gdx.input.setInputProcessor(inpManager);
    }

    public static InputManager getInputManager() {
        return inpManager;
    }

    public static void addComponent(Component c){
        tryInit();
        components.add(c);
    }

    public static void addEntity(Entity e){
        tryInit();
        entities.add(e);
        entityNames.add(e.getName());
    }

    public static Entity getEntity(String name) {
        return entities.get(entityNames.indexOf(name));
    }

    public static void changeName(String prev, String new_) {
        entityNames.set(entityNames.indexOf(prev), new_);
    }

    /**
     * raises the appropriate events for each entity's component. then renders after all entities have being processed
     * @param comps calls the events left to right
     */
    public static void raiseEvents(ComponentEvent... comps){
        tryInit();
        for (Entity e : entities){
            e.raiseEvents(comps);
        }

        RenderingManager.render();

        for (Entity e : entities){
            e.update();
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
    }

    private static void tryInit(){
        if(!initialized){
            Initialize();
        }
    }

    public static float getDeltaTime(){
        return Gdx.graphics.getDeltaTime();
    }
    public static int getFPS(){
        return Gdx.graphics.getFramesPerSecond();
    }
}
