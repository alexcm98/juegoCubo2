package com.mygdx.game.Actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by alex on 12/02/2018.
 */

public class Cubito extends Actor {

    private Texture jugador;
    private  boolean alive;

    public boolean isAlive(){
        return alive;
    }

    public  void setAlive(boolean alive){
        this.alive = alive;
    }

    public Cubito(Texture jugador){
        this.jugador = jugador;
        this.alive = true;
        setSize(jugador.getWidth(),jugador.getHeight());
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(jugador,getX(),getY());
    }

}
