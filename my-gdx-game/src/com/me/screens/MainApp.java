package com.me.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.me.mygdxgame.MainGame;

public class MainApp extends AbstractScreen {

	private Texture test;
	Image spashImage;
	public boolean move = false;

	public MainApp(MainGame game) {
		super(game);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		if (Gdx.input.isTouched() && !move) {
			stage.getCamera().translate(-Gdx.input.getDeltaX(),
					Gdx.input.getDeltaY(), 0);
		}

		if (move) {
			spashImage.setX(spashImage.getX() + Gdx.input.getDeltaX());
			spashImage.setY(spashImage.getY() - Gdx.input.getDeltaY());
		}
		// System.out.println(mouseY);
	
		//System.out.println(stage.getCamera().position);
		spashImage.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				move = true;
				Gdx.app.getGraphics().setTitle("I am working");
				return true;

			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				move = false;
				System.out.println("up" + x);

			}
		});
	}

	@Override
	public void show() {
		test = new Texture("data/TestImage.png");
		spashImage = new Image(test);
		stage.addActor(spashImage);
		Gdx.input.setInputProcessor(stage); 
		game.setScreen(new CreateScreen(game));
	}

	public void dispose() {
		super.dispose();
	}
}
