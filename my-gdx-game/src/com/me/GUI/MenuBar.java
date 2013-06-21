package com.me.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class MenuBar extends Group {
	protected ShapeRenderer renderer;
	public static SelectionBar selectBar;
	LightTextButton create;
	private int count = 0;

	public MenuBar() {
		renderer = new ShapeRenderer();
		selectBar = new SelectionBar();
		this.setBounds(getX(), getY(), 80, Gdx.app.getGraphics().getHeight());
		create = new LightTextButton();
		//this.addActor(selectBar);
		
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		
		selectBar.draw(batch, parentAlpha);
		create.draw(batch, parentAlpha);
		batch.end();
		renderer.setProjectionMatrix(batch.getProjectionMatrix());
		renderer.setTransformMatrix(batch.getTransformMatrix());
		renderer.setColor(new Color(0.447f, 0.694f, 0.831f, 1));
		renderer.begin(ShapeType.Filled);
		renderer.rect(0, 0, 80, Gdx.app.getGraphics().getHeight());
		renderer.end();
		batch.begin();
		for (Actor actor : getChildren()) {
			if (actor instanceof LightButton)
				actor.draw(batch, parentAlpha);
		}
	}

	public void addButton(LightButton button) {
		this.addActor(button);
	}

	@Override
	public void act(float delta) {
		selectBar.act(delta);
		
		for (Actor actor : getChildren()) {
			actor.setX(8);
			actor.setY(Gdx.app.getGraphics().getHeight() - (count * (64 + 35))
					- 100);
			if (actor instanceof LightButton)
				count++;
			actor.act(delta);
		}
		if(noActorsSelected()&&selectBar.getX()>1){
			selectBar.addAction(Actions.moveTo(0, 0, 0.15f));
		}
		
		count = 0;
	}

	public boolean noActorsSelected(){
		for(Actor act: getChildren()){
			LightButton button = (LightButton)(act);
			if(button.drawBlue){
				return false;
			}
		}
		return true;
	}
}
