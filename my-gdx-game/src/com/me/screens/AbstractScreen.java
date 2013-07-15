package com.me.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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
	int screenWidth;
	int screenHeight;
	protected Color color;


	public AbstractScreen(MainGame game) {
		this.game = game;
		this.font = new BitmapFont();
		this.batch = new SpriteBatch();
		color = new Color(Color.WHITE);
		this.stage = new Stage(0, 0, true);
	}

	protected String getName() {
		return getClass().getSimpleName();

	}

	public void setColor(Color color){
		this.color.a = color.a;
		this.color.b = color.b;
		this.color.g = color.g;
		this.color.r = color.r;
	}
	
	public void setColor(float r, float g, float b, float a){
		this.color.r = r;
		this.color.g = g;
		this.color.b = b;
		this.color.a = a;
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(color.r,color.b,color.g,color.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(Gdx.input.isKeyPressed(Input.Keys.F11) && !Gdx.graphics.isFullscreen()){
			this.screenHeight = Gdx.graphics.getHeight();
			this.screenWidth  = Gdx.graphics.getWidth();
			Gdx.graphics.setDisplayMode(
					Gdx.graphics.getDesktopDisplayMode().width, 
					Gdx.graphics.getDesktopDisplayMode().height,
					true);
		}
		if((Gdx.input.isKeyPressed(Input.Keys.F11) || Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) && Gdx.graphics.isFullscreen()){
			Gdx.graphics.setDisplayMode(screenWidth,screenHeight,false);
		}
		
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
