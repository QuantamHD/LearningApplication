package com.me.GUI.MultipleChoice;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

public class MultipleChoiceSkin extends Group{
	
	Array<LightTextField> fields;
	LightTextField QuestionEntry;
	
	
	public MultipleChoiceSkin(){
		 fields = new Array<LightTextField>();
		 QuestionEntry = new LightTextField();
		 QuestionEntry.setX(50);
		 QuestionEntry.setY(Gdx.graphics.getHeight() - 50);
		 QuestionEntry.setWidth(Gdx.graphics.getWidth()-100);
		 QuestionEntry.setHeight(300);
		 fields.add(QuestionEntry);
		 this.addActor(QuestionEntry);
	}
	
	@Override
	public void act(float delta){
		super.act(delta);
		 QuestionEntry.setX(50);
		 QuestionEntry.setY(Gdx.graphics.getHeight() - QuestionEntry.getHeight() - 50);
		 QuestionEntry.setWidth(Gdx.graphics.getWidth()-100);
		 QuestionEntry.setHeight(300);
	}
	
	@Override
	public void draw(SpriteBatch batch, float alpha){
		super.draw(batch, alpha);
		
	}
	
	public Array<LightTextField> getTextFields(){
		return fields;
	}
}
