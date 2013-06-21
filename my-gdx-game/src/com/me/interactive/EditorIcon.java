package com.me.interactive;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.me.questionTypes.QuestionType;

public class EditorIcon extends Actor {
	protected boolean buttonUp = true;
	protected Texture up;
	protected Texture down;
	protected float scale;
	protected ShapeRenderer renderer;

	public EditorIcon() {
		up = new Texture("data/EditorIcons/EssayIcon_U.png");
		down = new Texture("data/EditorIcons/EssayIcon_D.png");
		scale = 1.0f;
		renderer = new ShapeRenderer();
	}

	public void draw(SpriteBatch batch, float parentAlpha) {
		if (buttonUp)
			batch.draw(up, getX(), getY(), up.getWidth() * scale,
					up.getHeight() * scale);
		else
			batch.draw(down, getX(), getY(), down.getWidth() * scale,
					down.getHeight() * scale);
		

	}

	public void Up() {
		buttonUp = true;
	}

	public void Down() {
		buttonUp = false;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
}
