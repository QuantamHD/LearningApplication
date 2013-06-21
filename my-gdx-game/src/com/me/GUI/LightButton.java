package com.me.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class LightButton extends Actor {
	protected Texture buttonImg;
	protected boolean drawBlue = false;
	protected boolean drawLightBlue = false;
	protected ShapeRenderer renderer;
	protected SelectionBar selectBar;

	public LightButton(String path) {
		// selectBar = new SelectionBar();
		renderer = new ShapeRenderer();
		setTouchable(Touchable.enabled);
		buttonImg = new Texture(path);
		setWidth(buttonImg.getWidth());
		setHeight(buttonImg.getHeight());
		setBounds(getX(), getY(), getWidth(), getHeight());

		addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("down"); 
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				MenuBar.selectBar.addAction(Actions.moveTo(320 + 80, 0, 0.2f));
				
				System.out.println("up");
				drawBlue = true;
				drawLightBlue = false;
			}

			public void enter(InputEvent event, float x, float y, int pointer,
					Actor fromActor) {
				if (!drawBlue)
					drawLightBlue = true;
			}

			public void exit(InputEvent event, float x, float y, int pointer,
					Actor fromActor) {
				drawLightBlue = false;
			}

		});
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// selectBar.draw(batch, parentAlpha);
		if (drawBlue||drawLightBlue) {
			batch.end();
			renderer.setProjectionMatrix(batch.getProjectionMatrix());
			renderer.setTransformMatrix(batch.getTransformMatrix());
				renderer.setColor(new Color(0.316f, 0.607f, 0.741f, 1));
			if(drawLightBlue)
				renderer.setColor(new Color(0.525f, 0.772f, 0.909f, 1));
			renderer.begin(ShapeType.Filled);
			renderer.rect(0, getY() - 7, 80, getHeight() + 14);
			renderer.end();
			batch.begin();
		}
		batch.draw(buttonImg, getX(), getY());
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		// selectBar.act(delta);
		if (Gdx.input.isTouched()) {
			float left1, left2;
			float right1, right2;
			float top1, top2;
			float bottom1, bottom2;
			float mx = Gdx.input.getX();
			float my = Gdx.input.getY() - Gdx.app.getGraphics().getHeight();

			left1 = getX();
			left2 = mx;
			right1 = getX() + getWidth();
			right2 = mx;
			top1 = getY();
			top2 = my;
			bottom1 = getY() + getHeight();
			bottom2 = my;

			if (bottom1 < top2)
				drawBlue = false; // no collision
			if (top1 > bottom2)
				drawBlue = false; // no collision

			if (right1 < left2)
				drawBlue = false; // no collision
			if (left1 > right2)
				drawBlue = false; // no collision
		}
	}

	public boolean isClicked() {
		return drawBlue;
	}

}
