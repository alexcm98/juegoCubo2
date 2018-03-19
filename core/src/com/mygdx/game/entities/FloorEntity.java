package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
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

public class FloorEntity extends Actor {

    private World world;
    private  Texture floor;
    private  Texture overfloor;
    private Body body,leftBody ;
    private Fixture fixture ,leftFixture;

    public  FloorEntity(World world, Texture floor,Texture overfloor,float x,float widht,float y){

        this.world =world;
        this.floor = floor;
        this.overfloor = overfloor;

        BodyDef def = new BodyDef();
        def.position.set(x+widht/2,y-0.5f);
        body = world.createBody(def);

        PolygonShape box = new PolygonShape();
        box.setAsBox(widht/2,0.5f);
        fixture = body.createFixture(box,1);
        fixture.setUserData("suelo");
        box.dispose();

        BodyDef leftDef = new BodyDef();
        leftDef.position.set(x,y-0.55f);
        leftBody = world.createBody(leftDef);
        PolygonShape leftBox = new PolygonShape();
        leftBox.setAsBox(0.02f,0.45f);
        leftFixture = leftBody.createFixture(leftBox,1);
        leftFixture.setUserData("pincho");
        leftBox.dispose();


        setSize(widht * Constantes.PIXELS_IN_METER, Constantes.PIXELS_IN_METER);
        setPosition(x*Constantes.PIXELS_IN_METER,(y-1)*Constantes.PIXELS_IN_METER);
    }

    @Override
    public  void draw(Batch batch,float parentAlpha){
        batch.draw(floor,getX(),getY(),getWidth(),getHeight());
    }


    public  void  detach(){
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }
}
