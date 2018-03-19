package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by alex on 26/02/2018.
 */

public class Box2dScreen extends Inicio {

    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;
    private Body cubitoBody,sueloBody,pinchoBody;
    private Fixture cubitoFixture,sueloFixture,pinchoFixture;
    private  boolean debeSaltar,cubitoSaltando,cuboVivo;

    public Box2dScreen(MyGdxGame game) {
        super(game);
    }



    @Override
    public void show() {
        world = new World(new Vector2(0,-10),true);
        renderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(16,9);
        camera.translate(2,1);
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA(),fixtureB =contact.getFixtureB();

                if((fixtureA.getUserData().equals("PLAYER") && fixtureB.getUserData().equals("FLOOR"))||
                        (fixtureA.getUserData().equals("FLOOR")&& fixtureB.getUserData().equals("PLAYER"))){
                    if(Gdx.input.isTouched()){
                        debeSaltar = true;
                    }
                    cubitoSaltando = false;
                }


                if((fixtureA.getUserData().equals("PLAYER") && fixtureB.getUserData().equals("SPIKE"))||
                        (fixtureA.getUserData().equals("SPIKE")&& fixtureB.getUserData().equals("PLAYER"))){
                  cuboVivo = false;
                }

            }

            @Override
            public void endContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA(),fixtureB =contact.getFixtureB();
                if(fixtureA == cubitoFixture && fixtureB == sueloFixture){
                    cubitoSaltando = true;
                }
                if(fixtureA == sueloFixture && fixtureB == cubitoFixture){
                    cubitoSaltando = true;
                }
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

        cubitoBody = world.createBody(createCubitoBodyDef());
        sueloBody = world.createBody(createSueloBodyDef());
        pinchoBody = world.createBody(createPinchoBodyDef(1));


        PolygonShape cubitoShape = new PolygonShape();
        cubitoShape.setAsBox(1,1);
        cubitoFixture = cubitoBody.createFixture(cubitoShape,1);
        cubitoShape.dispose();


        PolygonShape sueloShape = new PolygonShape();
        sueloShape.setAsBox(500,1);
        sueloFixture = sueloBody.createFixture(sueloShape,1);
        sueloShape.dispose();

        pinchoFixture = createPinchoFixture(pinchoBody);

        cubitoFixture.setUserData("PLAYER");
        sueloFixture.setUserData("FLOOR");
        pinchoFixture.setUserData("SPIKE");
    }

    private Fixture createPinchoFixture(Body pinchoBody){
        Vector2[] vertices = new Vector2[3];
        vertices[0] = new Vector2(-0.5f,-0.5f);
        vertices[1] = new Vector2(0.5f,-0.5f);
        vertices[2] = new Vector2(0,0.5f);

        PolygonShape shape = new PolygonShape();
        shape.set(vertices);
        Fixture fix = pinchoBody.createFixture(shape,1);
        shape.dispose();
        return  fix;
    }

    private BodyDef createPinchoBodyDef(float x){
        BodyDef def = new BodyDef();
        def.position.set(x,0.5f);
        return def;
    }

    private BodyDef createSueloBodyDef(){
        BodyDef def = new BodyDef();
        def.position.set(0,-1);
        return def;

    }

    private BodyDef createCubitoBodyDef(){
        BodyDef def = new BodyDef();
        def.position.set(0,10);
        def.type = BodyDef.BodyType.DynamicBody;
        return def;

    }

    @Override
    public void dispose() {
        sueloBody.destroyFixture(sueloFixture);
        cubitoBody.destroyFixture(cubitoFixture);
        pinchoBody.destroyFixture(pinchoFixture);
        world.destroyBody(cubitoBody);
        world.destroyBody(sueloBody);
        world.destroyBody(pinchoBody);
        world.dispose();
        renderer.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(debeSaltar){
              debeSaltar = false;
              saltar();
        }

        if(Gdx.input.justTouched() || !cubitoSaltando){
            debeSaltar = true;

        }

        if(cuboVivo) {
            float velocidad = cubitoBody.getLinearVelocity().y;
            cubitoBody.setLinearVelocity(8, velocidad);
        }
        world.step(delta,6,2);
        camera.update();
        renderer.render(world,camera.combined);
    }


    private void saltar(){
       Vector2 position = cubitoBody.getPosition();
        cubitoBody.applyLinearImpulse(0,20,position.x,position.y,true);
    }

}
