package com.mygdx.game;

import com.badlogic.gdx.InputAdapter;


/**
 * Created by alex on 11/02/2018.
 */

public class Procesador extends InputAdapter {

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
      //  System.out.println("Has tocado en la posicion "+screenX+","+screenY);
        //System.out.println("Has utilizado el dedo "+pointer+" y el boton"+button);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }


    @Override
    public boolean keyDown(int keycode) {
        System.out.println("pulsando");

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        System.out.println("soltado");
        return true;
    }
}
