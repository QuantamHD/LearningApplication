package com.me.GUI.Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class SelectionBar extends Actor {

	public    ShapeRenderer renderer;
	protected boolean       acting = false;
	protected Array<String> text;
	protected BitmapFont    font;
	public 	  Rectangle     rectangle;

	public SelectionBar() {
		font     = new BitmapFont();
		text     = new Array<String>();
		renderer = new ShapeRenderer();
		setBounds(-320, 0, -320, Gdx.app.getGraphics().getHeight());
		rectangle = new Rectangle(getX(),getY(), -320, Gdx.app.getGraphics().getHeight());
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		drawRectangle(-320, new Color(0.316f, 0.607f, 0.741f, 1), batch);
		drawRectangle(getX() - 10, Gdx.app.getGraphics().getHeight() - 140,
		-300, 2, Color.WHITE, batch);
		rectangle.x = getX()+getWidth();
		rectangle.y = 0;
		rectangle.width = Math.abs(getWidth()); 
		rectangle.height = Gdx.app.getGraphics().getHeight();
		
	}

	public void drawRectangle(float width, Color color, SpriteBatch batch) {
		
		batch.end();
		renderer.setColor(color);
		renderer.setProjectionMatrix(batch.getProjectionMatrix());
		renderer.setTransformMatrix(batch.getTransformMatrix());
		renderer.begin(ShapeType.Filled);
		renderer.rect(getX(), 0, width, Gdx.app.getGraphics().getHeight());
		renderer.end();
		batch.begin();
		
	
		
	}

	public void drawRectangle(float x, float y, float width, float height,
	Color color, SpriteBatch batch) {
		
		batch.end();
		renderer.setColor(color);
		renderer.setProjectionMatrix(batch.getProjectionMatrix());
		renderer.setTransformMatrix(batch.getTransformMatrix());
		renderer.begin(ShapeType.Filled);
		renderer.rect(x, y, width, height);
		renderer.end();
		batch.begin();
		
	}
}
