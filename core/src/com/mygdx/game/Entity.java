package com.mygdx.game;

import com.mygdx.game.Components.Component;
import com.mygdx.game.Components.ComponentType;

import java.util.ArrayList;

/**
 * The base class for all entities in the game.
 * I am calling an entity pretty much anything that the user sees or interacts with except the UI.
 * However, there is over head with this class so in some cases it's better to just use raw sprites
 */
public class Entity {
    private ArrayList<Component> components;

    public Entity(){
        components = new ArrayList<>();
    }
    public Entity(int numComponents){
        components = new ArrayList<>(numComponents);
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


    public void cleanUp() {

    }

}
