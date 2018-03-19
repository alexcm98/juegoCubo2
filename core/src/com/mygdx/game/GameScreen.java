package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.game.entities.FloorEntity;
import com.mygdx.game.entities.PlayerEntity;
import com.mygdx.game.entities.SpikeEntity;


import java.util.ArrayList;

/**
 * Created by alex on 27/02/2018.
 */

public class GameScreen extends  Inicio {

    private String puntos;
    private Stage stage;
    private World world;
    private PlayerEntity player;
    private ArrayList<FloorEntity> floorList = new ArrayList<FloorEntity>();

    private ArrayList<SpikeEntity> spikeList = new ArrayList<SpikeEntity>();
    private Music musica;
    private Sound salto,muerte;
    private int n;

    public GameScreen(final MyGdxGame game, final DataBaseCore dc) {
        super(game);
        n = game.num;
        stage = new Stage(new FillViewport(640,360));
        world = new World(new Vector2(0,-10),true);

        salto = game.getManager().get("Personaje/mario.mp3");
        muerte = game.getManager().get("Personaje/muerte.mp3");
        musica = game.getManager().get("Personaje/Cancion.mp3");


        world.setContactListener(new ContactListener() {

            private  boolean areCollided(Contact contact,Object userA,Object userB){

                return  (contact.getFixtureA().getUserData().equals(userA) && contact.getFixtureB().getUserData().equals(userB)) ||
                (contact.getFixtureA().getUserData().equals(userB) && contact.getFixtureB().getUserData().equals(userA));
            }

            @Override
            public void beginContact(Contact contact) {
                if(areCollided(contact,"player","suelo")){
                    player.setJumpin(false);
                    if(Gdx.input.isTouched()){
                        salto.play();
                        player.setMustJumpJumpin(true);
                        Gdx.graphics.setContinuousRendering(false);
                        dc.obtenerPuntuacion(player.getX()+"");
                       // System.out.print("Puntuacion"+player.getX());
                        Gdx.app.exit();


                    }
                }

                if (areCollided(contact,"player","pincho")){
                    System.out.print("Puntuacion"+player.getX());
                    System.out.print("Holaaaa");
                    if(player.isAlive()){
                        player.setAlive(false);
                        musica.stop();
                        muerte.play();
                        puntos = ""+ player.getX();
                        Gdx.graphics.setContinuousRendering(false);
                        dc.obtenerPuntuacion(player.getX()+"");
                       // System.out.print("Puntuacion"+player.getX());
                        Gdx.app.exit();


                    }

                }
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
    }

    @Override
    public void show() {

        Texture cuboTextur = null;
        if(n == 1){
             cuboTextur = game.getManager().get("Personaje/cub.png");
        }else  if(n ==2){
             cuboTextur = game.getManager().get("Personaje/cub2.png");
        }

        Texture cuboTexture = cuboTextur;

        Texture floorTexture = game.getManager().get("Personaje/suelo.png");
        Texture spikeTexture = game.getManager().get("Personaje/triangulo.png");
        Texture overfloorTexture = game.getManager().get("Personaje/overfloor.png");
        player = new PlayerEntity(world,cuboTexture,new Vector2(1.5f,1.5f));


        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,0,109,1));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,30, 42,2));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,34, 36,3));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,38, 28,4));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,42, 11,5));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,55, 8,5));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,73, 10,3));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,97, 2,2));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,113,8,1));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,124,7,1));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,133,100,1));

        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,139, 5,2));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,142, 5,3));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,146, 1,4));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,148, 4,3));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,152, 2,2));

        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,156, 8,2));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,160, 8,3));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,164, 8,4));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,168, 20,5));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,177,2,6));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,197, 100,4));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,197,100,5));

        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,236,5,1));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,244,4,1));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,252,10,1));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,263,3,1));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,268,4,1));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,276,4,1));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,280,2,1));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,284,2,1));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,287,1,1));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,290,2,1));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,294,4,1));
        floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,300,4,1));


        spikeList.add(new SpikeEntity(world,spikeTexture,46,5));
        spikeList.add(new SpikeEntity(world,spikeTexture,47,5));
        spikeList.add(new SpikeEntity(world,spikeTexture,54,5));
        spikeList.add(new SpikeEntity(world,spikeTexture,55,5));
        spikeList.add(new SpikeEntity(world,spikeTexture,80,1));
        spikeList.add(new SpikeEntity(world,spikeTexture,81,1));
        spikeList.add(new SpikeEntity(world,spikeTexture,82,1));
        spikeList.add(new SpikeEntity(world,spikeTexture,86,1));
        spikeList.add(new SpikeEntity(world,spikeTexture,86,1));
        spikeList.add(new SpikeEntity(world,spikeTexture,86,1));
        spikeList.add(new SpikeEntity(world,spikeTexture,86,1));
        spikeList.add(new SpikeEntity(world,spikeTexture,86,1));
        spikeList.add(new SpikeEntity(world,spikeTexture,100,1));
        spikeList.add(new SpikeEntity(world,spikeTexture,100,2));
        spikeList.add(new SpikeEntity(world,spikeTexture,172,5));
        spikeList.add(new SpikeEntity(world,spikeTexture,173,5));
        spikeList.add(new SpikeEntity(world,spikeTexture,180,5));
        spikeList.add(new SpikeEntity(world,spikeTexture,180,6));
        spikeList.add(new SpikeEntity(world,spikeTexture,186,6));
        spikeList.add(new SpikeEntity(world,spikeTexture,187,6));
        spikeList.add(new SpikeEntity(world,spikeTexture,188,6));
        spikeList.add(new SpikeEntity(world,spikeTexture,189,6));
        spikeList.add(new SpikeEntity(world,spikeTexture,190,6));
        spikeList.add(new SpikeEntity(world,spikeTexture,191,6));

       // floorList.add(new FloorEntity(world,floorTexture,overfloorTexture,168, 20,5));

        stage.addActor(player);

        for(FloorEntity f: floorList){
            stage.addActor(f);
        }
        for (SpikeEntity s:spikeList){
            stage.addActor(s);
        }

        salto.setVolume(1,0.5f);
        muerte.setVolume(1,0.75f);
        musica.setVolume(0.75f);
        musica.play();
    }

    @Override
    public void hide() {
        musica.stop();
        player.detach();
        player.remove();
        for(FloorEntity f: floorList){
            f.detach();
            f.remove();
        }
        for (SpikeEntity s:spikeList){
            s.detach();
            s.remove();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(player.getX() > 150 && player.isAlive()){

            stage.getCamera().translate(Constantes.VELOCIDAD_PLAYER*delta*Constantes.PIXELS_IN_METER,0,0);

        }

        if(Gdx.input.justTouched()){
            salto.play();
            player.jump();
        }

        stage.act();
        world.step(delta,6,2);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();
    }


}
