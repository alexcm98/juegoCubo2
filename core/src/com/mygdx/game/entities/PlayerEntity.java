package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Constantes;

/**
 * Created by alex on 27/02/2018.
 */

public class PlayerEntity extends Actor {

    private Texture texture;
    private World world;
    private Body body;
    private Fixture fixture;
    private  boolean alive = true,jumpin = false, mustJump = false;


    public PlayerEntity(World world, Texture texture, Vector2 position){
        this.world = world;
        this.texture = texture;

        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);

        PolygonShape cubito = new PolygonShape();
        cubito.setAsBox(0.25f,0.25f);
        fixture = body.createFixture(cubito,1);
        fixture.setUserData("player");
        cubito.dispose();

        setSize(Constantes.PIXELS_IN_METER,Constantes.PIXELS_IN_METER);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x -0.5f)* Constantes.PIXELS_IN_METER,
                (body.getPosition().y-0.5f)*Constantes.PIXELS_IN_METER);
        batch.draw(texture,getX(),getY(),getWidth(),getHeight());
    }

    @Override
    public void act(float delta){
        //Saltar si tocamos la pantalla
        if(  mustJump){
            mustJump = false;
            jump();
        }


        if(alive){
            float velocidad = body.getLinearVelocity().y;
            body.setLinearVelocity(Constantes.VELOCIDAD_PLAYER, velocidad);
        }

        if(jumpin){
            body.applyForceToCenter(0,-Constantes.IMPULSO*1.25F,true);
        }

    }


    public  void  jump(){
        if(!jumpin && alive){
            jumpin = true;
            Vector2 position = body.getPosition();
            body.applyLinearImpulse(0,Constantes.IMPULSO,position.x,position.y,true);
        }

    }

    public  void detach(){

        body.destroyFixture(fixture);
        world.destroyBody(body);
    }


    public  boolean isAlive(){
        return  alive;
    }

    public  void setAlive(boolean alive){
        this.alive = alive;
    }

    public  boolean isJumpin(){
        return  jumpin;
    }

    public  void setJumpin(boolean jumpin){
        this.jumpin = jumpin;
    }

    public  boolean isMustJumpin(){
        return  mustJump;
    }

    public  void setMustJumpJumpin(boolean mustJump){
        this.mustJump = mustJump;
    }
}
