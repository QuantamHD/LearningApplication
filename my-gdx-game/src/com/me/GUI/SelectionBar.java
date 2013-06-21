package com.me.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class SelectionBar extends Actor {

	ShapeRenderer renderer;
	protected boolean acting = false;
	protected Array<String> text;
	protected BitmapFont font;

	public SelectionBar() {
		font = new BitmapFont();
		text = new Array<String >();
		renderer = new ShapeRenderer();
		this.setBounds(-320, 0, -320, Gdx.app.getGraphics().getHeight());
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.end();
		renderer.setProjectionMatrix(batch.getProjectionMatrix());
		renderer.setTransformMatrix(batch.getTransformMatrix());
		renderer.setColor(new Color(0.316f, 0.607f, 0.741f, 1));
		renderer.begin(ShapeType.Filled);
		renderer.rect(getX(), 0, -320, Gdx.app.getGraphics().getHeight());
		renderer.setColor(Color.WHITE);
		renderer.rect(getX() - 10, Gdx.app.getGraphics().getHeight() - 140,
				-300, 2);
		renderer.end();
		batch.begin();
		this.setX(getX());
	}

	public void act(float delta) {
		super.act(delta);

	}

	public void setActing(boolean a) {
		this.acting = a;
	}

	public void drawTitles(SpriteBatch batch) {
		for (int i = 0; i < text.size; i++) {
			if(i == 0){
				font.setScale(2);
				font.draw(batch, text.get(i),getX(), Gdx.app.getGraphics().getHeight()-142);
				font.setScale(1);
			}
			
			
			
		}
	}
}
