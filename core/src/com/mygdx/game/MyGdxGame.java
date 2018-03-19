package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class MyGdxGame extends Game {

	private AssetManager manager;

	public GameScreen gameScreen;

	static int num;
	static  DataBaseCore dcc;


	public MyGdxGame(int op,DataBaseCore dc) {
		 num = op;
		dcc = dc;
	}

	public MyGdxGame() {

	}



	public AssetManager getManager() {
		return manager;
	}

	@Override
	public void create () {

		if(num == 1){

			manager = new AssetManager();
			manager.load("Personaje/suelo.png",Texture.class);
			manager.load("Personaje/overfloor.png",Texture.class);
			manager.load("Personaje/triangulo.png",Texture.class);
			manager.load("Personaje/cub.png",Texture.class);
			manager.load("Personaje/mario.mp3", Sound.class);
			manager.load("Personaje/muerte.mp3", Sound.class);
			manager.load("Personaje/Cancion.mp3",Music.class);
			manager.load("Personaje/gameOver.png",Texture.class);
			manager.finishLoading();

		}else if(num == 2){

			manager = new AssetManager();
			manager.load("Personaje/suelo.png",Texture.class);
			manager.load("Personaje/overfloor.png",Texture.class);
			manager.load("Personaje/triangulo.png",Texture.class);
			manager.load("Personaje/cub2.png",Texture.class);
			manager.load("Personaje/mario.mp3", Sound.class);
			manager.load("Personaje/muerte.mp3", Sound.class);
			manager.load("Personaje/Cancion.mp3",Music.class);
			manager.load("Personaje/gameOver.png",Texture.class);
			manager.finishLoading();

		}



		gameScreen = new GameScreen(this,dcc);



		setScreen( gameScreen);



	}


}
