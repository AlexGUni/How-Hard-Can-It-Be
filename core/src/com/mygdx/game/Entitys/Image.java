package com.mygdx.game.Entitys;

import com.mygdx.game.Components.Renderable;
import com.mygdx.game.RenderLayer;

public class Image extends Entity {
    public Image() {
        super();
    }
    public Image(int texId, RenderLayer layer) {
        Renderable r = new Renderable(texId, layer);
        addComponent(r);
    }
}
