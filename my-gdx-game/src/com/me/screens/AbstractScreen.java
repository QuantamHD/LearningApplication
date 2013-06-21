package com.me.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.me.mygdxgame.MainGame;

public abstract class AbstractScreen implements Screen {

	protected final MainGame game;
	protected final BitmapFont font;
	protected final SpriteBatch batch;
	protected final Stage stage;

	public AbstractScreen(MainGame game) {
		this.game = game;
		this.font = new BitmapFont();
		this.batch = new SpriteBatch();
		this.stage = new Stage(0, 0, true);
	}

	protected String getName() {
		return getClass().getSimpleName();

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		stage.dispose();
		batch.dispose();
		font.dispose();
	}

}
