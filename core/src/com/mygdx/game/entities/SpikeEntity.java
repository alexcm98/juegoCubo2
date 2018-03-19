package com.mygdx.game.entities;

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
 * Created by alex on 04/03/2018.
 */

public class SpikeEntity extends Actor {

    private World world;
    private Texture texture;
    private Body body;
    private Fixture fixture;

    public SpikeEntity(World world,Texture texture,float x,float y){

        this.world = world;
        this.texture = texture;

        BodyDef def = new BodyDef();
        def.position.set(x,y+0.5f);
        body = world.createBody(def);

        PolygonShape box = new PolygonShape();
        Vector2[] vertices = new Vector2[3];
        vertices[0] = new Vector2(-0.5f,-0.5f);
        vertices[1] = new Vector2(0.5f,-0.5f);
        vertices[2] = new Vector2(0,0.5f);
        box.set(vertices);
        fixture = body.createFixture(box,1);
        fixture.setUserData("pincho");
        box.dispose();

        setPosition((x-0.5f)* Constantes.PIXELS_IN_METER,y*Constantes.PIXELS_IN_METER);
        setSize(Constantes.PIXELS_IN_METER,Constantes.PIXELS_IN_METER);

    }

    @Override
    public  void draw(Batch batch,float parentAlpha){
       /* PolygonShape box = new PolygonShape();
        Vector2[] vertices = new Vector2[3];
        vertices[0] = new Vector2(-0.5f,-0.5f);
        vertices[1] = new Vector2(0.5f,-0.5f);
        vertices[2] = new Vector2(0,0.5f);
        box.set(vertices);
        batch.begin();

        batch.end();*/
        batch.draw(texture,getX(),getY(),getWidth(),getHeight());
    }

    public  void detach(){
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }



}
