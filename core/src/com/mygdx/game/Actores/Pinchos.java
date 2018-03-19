package com.mygdx.game.Actores;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by alex on 26/02/2018.
 */

public class Pinchos extends Actor {

    private TextureRegion pinchos;

    public Pinchos(TextureRegion pinchos){
        this.pinchos = pinchos;
        setSize(pinchos.getRegionWidth(),pinchos.getRegionHeight());
    }

    @Override
    public void act(float delta) {
        float x = getX();
        setX(x-delta);
    }
    @Override
    public void draw(Batch batch,float parentAlpha){
        batch.draw(pinchos,getX(),getY());
    }


}
