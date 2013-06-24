package com.me.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class MenuBar extends Group {
	protected     ShapeRenderer renderer   = null;
	public static SelectionBar selectBar   = null;
	private       BitmapFont font          = null;
	private       LightTextButton a        = null;
	private       int count                = 0;
	public static boolean menuBarClicked   = false;

	public MenuBar() {
		renderer  = new ShapeRenderer();
		selectBar = new SelectionBar();

		setBounds(
				getX(), getY(), 
				80, 
				Gdx.app.getGraphics().getHeight());

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
				Gdx.files.internal
				("data/Kelson.ttf"));

		font = generator.generateFont(30);
		generator.dispose();

		a = new LightTextButton("Hello World", font, renderer);
		a.setX(80);
		a.setY(200);
		
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {

		selectBar.draw(batch, parentAlpha);

		batch.end();
		renderer.setProjectionMatrix(batch.getProjectionMatrix());
		renderer.setTransformMatrix(batch.getTransformMatrix());
		renderer.setColor(new Color(0.447f, 0.694f, 0.831f, 1));
		renderer.begin(ShapeType.Filled);
		renderer.rect(0, 0, 80, Gdx.app.getGraphics().getHeight());
		renderer.end();
		batch.begin();
		drawChildren(batch, parentAlpha);
		a.draw(batch, parentAlpha);
	}

	public void addButton(LightButton button) {
		this.addActor(button);
	}

	@Override
	public void act(float delta) {
		selectBar.act(delta);
		a.act(delta);

		for (Actor actor : getChildren()) {
			actor.setX(8);
			actor.setY(Gdx.app.getGraphics().getHeight() - (count * (64 + 35))
					- 100);
			if (actor instanceof LightButton)
				count++;
			actor.act(delta);
		}
		if (Gdx.input.isTouched()) {

			if (selected()) {
				menuBarClicked = true;
			} else
				menuBarClicked = false;
		} 
		if (noActorsSelected() && selectBar.getX() > 1 && menuBarClicked) {
			selectBar.addAction(Actions.moveTo(0, 0, 0.2f));
		}

		count = 0;
	}

	public boolean noActorsSelected() {
		for (Actor act : getChildren()) {
			if(act instanceof LightButton){
				LightButton button = (LightButton) (act);
				if (button.drawBlue) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean selected() {

		float left1, left2;
		float right1, right2;
		float top1, top2;
		float bottom1, bottom2;
		float mx = Gdx.input.getX();
		float my = -Gdx.input.getY() + Gdx.app.getGraphics().getHeight();

		left1 = selectBar.getX();
		left2 = mx;
		right1 = selectBar.getX() + 320;
		right2 = mx;
		top1 = 0;
		top2 = my;
		bottom1 = 720;
		bottom2 = my;

		if (bottom1 < top2)
			return false; // no collision

		if (top1 > bottom2)
			return false;

		if (right1 < left2)
			return false; // no collision

		if (left1 > right2)
			return false; // no collision

		System.out.println("I am true");
		return true;
	}
	public void drawChildren(SpriteBatch batch, float parentAlpha){
		for (Actor actor : getChildren()) {
			if (actor instanceof LightButton)
				actor.draw(batch, parentAlpha);
		}
	}
}
