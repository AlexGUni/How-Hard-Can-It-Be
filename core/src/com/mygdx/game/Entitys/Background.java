package com.mygdx.game.Entitys;

import com.mygdx.game.Components.Renderable;

public class Background extends Entity {
    public Background() {
        super();
    }
    public Background(int id_bg){
        super(1);
        Renderable r = new Renderable(id_bg);
        addComponent(r);
    }
}
