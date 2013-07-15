package com.me.GUI.MultipleChoice;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.GUI.Main.MenuBar;
import com.me.screens.MultipleChoiceScreen;

public class LightTextField extends Actor {

	public ShapeRenderer renderer;
	private String text;
	public int cursor = 0;
	public BitmapFont font;


	public LightTextField() {
		renderer = MultipleChoiceScreen.renderer;
		text = "";
		font = MenuBar.generator.generateFont(24);
		font.setColor(Color.BLACK);
		
		addListener(new ClickListener(){
			@Override
			public boolean keyTyped (InputEvent event, char key) {
				System.out.println(key);
				if(key != 0 && key != 8 && key!= 127){
					text+= "" + key;
				}
				if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
					text+= "\n      ";
				}
				if(Gdx.input.isKeyPressed(Input.Keys.TAB)){
					text += "      ";
				}
				return true;
			}
		});
	}

	@Override
	public void draw(SpriteBatch batch, float alpha) {

		
		
		batch.end();
		renderer.setProjectionMatrix(batch.getProjectionMatrix());
		renderer.setTransformMatrix(batch.getTransformMatrix());
		renderer.begin(ShapeType.Filled);
		renderer.setColor(Color.WHITE);
		renderer.rect(getX(), getY(), getWidth(), getHeight());
		renderer.end();
		batch.begin();
		
		font.drawWrapped(batch, text, getX()+5, getY() + getHeight()-5, getWidth()-5);
		
		TextField text;
	}
	
	
	@Override
	public void act(float delta) {
		super.act(delta);
	}

	public void getTextTyped() {
		
	}

	public String getText() {
		return this.text;
	}
}
