package net.mvw.delta.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Panda extends Sprite {

    private Bamboo bamboo;
    public boolean toBeRemoved = false;

    public Panda(Texture texture) {
        super(texture);
        this.setOrigin(0,0);
    }


    public Bamboo getBamboo() {
        return bamboo;
    }

    public void setBamboo(Bamboo bamboo) {
        this.bamboo = bamboo;
    }
}
