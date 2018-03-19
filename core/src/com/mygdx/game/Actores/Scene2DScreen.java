package com.mygdx.game.Actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Actores.Cubito;
import com.mygdx.game.Actores.Pinchos;
import com.mygdx.game.Inicio;
import com.mygdx.game.MyGdxGame;

/**
 * Created by alex on 12/02/2018.
 */
/*
dfdfgdfgdgfdgdfg
 */
public class Scene2DScreen extends Inicio {

    private Stage stage;
    private Cubito jugador;
    private Pinchos obstaculo;
    private Texture textJug, textPin;

    public Scene2DScreen(MyGdxGame game) {
        super(game);
        textJug = new Texture("Personaje/cub.png");
        textPin = new Texture("Personaje/pincho.png");
        regionpincho = new TextureRegion(textPin,0,64,128,64);
    }


    private TextureRegion regionpincho;


    @Override
    public void show() {

       stage = new Stage();
        stage.setDebugAll(true);

        jugador = new Cubito(textJug);
        obstaculo = new Pinchos(regionpincho);
        stage.addActor(jugador);
        stage.addActor(obstaculo);

        jugador.setPosition(100,100);
        obstaculo.setPosition(400,100);

    }


    @Override
    public void hide() {
        stage.dispose();

    }

    @Override
    public void dispose() {
        textJug.dispose();
        textPin.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        comprobarColision();
        stage.draw();

        obstaculo.act(100);
    }


    private  void comprobarColision(){
        if(jugador.isAlive() && (jugador.getX()+jugador.getWidth()) > obstaculo.getX()){
            System.out.println("Colision");
            jugador.setAlive(false);
        }
    }
}
