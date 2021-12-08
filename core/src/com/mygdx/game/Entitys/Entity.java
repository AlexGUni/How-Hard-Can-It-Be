package com.mygdx.game.Entitys;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Components.Component;
import com.mygdx.game.Components.ComponentEvent;
import com.mygdx.game.Components.ComponentType;
import com.mygdx.game.Components.EntityManager;

import java.util.ArrayList;

/**
 * The base class for all entities in the game.
 * I am calling an entity pretty much anything that the user sees or interacts with except the UI.
 * However, there is over head with this class so in some cases it's better to just use raw sprites
 */
public class Entity {
    private final ArrayList<Component> components;

    public Entity(){
        components = new ArrayList<>();
        EntityManager.addEntity(this);

    }
    public Entity(int numComponents){
        components = new ArrayList<>(numComponents);
        EntityManager.addEntity(this);
    }
    public void addComponent(Component component) {
        components.add(component);
    }
    public Component getComponent(ComponentType type){
        for (Component c : components){
            if(c.getType() == type){
                return c;
            }
        }
        return null;
    }

    /**
     * Gets the components that is of the same type as T
     * @param type [T].class
     * @param <T> the type of the desired component
     * @return the component cast to the appropriate type
     */
    public <T> T getComponent(Class<T> type){
        for(Component c : components){
            if(type.isInstance(c)) {
                return (T)c;
            }
        }
        return null;
    }

    /**
     * Raises the appropriate events on each component
     */
    public final void raiseEvents(ComponentEvent... events){
        for(ComponentEvent e : events){
            if(e == ComponentEvent.Render){
                EntityManager.getBatch().setProjectionMatrix(EntityManager.getCamera().combined);
                EntityManager.getBatch().begin();
            }
            for(Component c : components){
                switch (e){
                    case Awake:
                        c.awake();
                        break;
                    case Start:
                        c.start();
                        break;
                    case Update:
                        c.update();
                        break;
                    case Render:
                        c.render();
                        break;
                    case OnKeyboard:
                        break;
                    case OnMouse:
                        break;
                }
            }
            if(e == ComponentEvent.Render){
                EntityManager.getBatch().end();
            }
        }
    }

    public void cleanUp() {

    }

}
