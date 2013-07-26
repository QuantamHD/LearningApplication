package com.me.GUI.MultipleChoice;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.me.GUI.Main.MenuBar;

public class MultipleChoiceSkin extends Group {

	Array<LightTextField> fields;
	LightTextField QuestionEntry;
	Array <LightTextField> answerFields;
	
	BitmapFont font;

	public MultipleChoiceSkin() {
		init();
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		updateUI();
	}

	public void updateUI() {
		QuestionEntry.setX(50);
		QuestionEntry.setY(Gdx.graphics.getHeight() - QuestionEntry.getHeight()
				- 60);
		QuestionEntry.setWidth(Gdx.graphics.getWidth() - 100);
		QuestionEntry.setHeight(300);
	}

	@Override
	public void draw(SpriteBatch batch, float alpha) {
		super.draw(batch, alpha);

		font.draw(batch, "Multiple Choice Question",
				(Gdx.graphics.getWidth() - font
						.getBounds("Multiple Choice Question").width) / 2,
				Gdx.graphics.getHeight() - 10);
		
		for(int i = 0; i<4; i++){
			answerFields.get(i).setY((Gdx.graphics.getHeight()) - 100 - 277-6-50 - (i*65));
			answerFields.get(i).setWidth(Gdx.graphics.getWidth() - 100);
			answerFields.get(i).setHeight(answerFields.get(i).font.getBounds("O").height+8);
		}
	}

	public Array<LightTextField> getTextFields() {
		return fields;
	}

	public void init() {
		fields = new Array<LightTextField>();
		QuestionEntry = new LightTextField("Enter Question Here.");
		QuestionEntry.setX(50);
		QuestionEntry.setY(Gdx.graphics.getHeight() - 75);
		QuestionEntry.setWidth(Gdx.graphics.getWidth() - 100);
		QuestionEntry.setHeight(300);
		fields.add(QuestionEntry);
		this.addActor(QuestionEntry);
		
		answerFields = new Array<LightTextField>();
		
		for(int i = 0; i<4; i++){
			answerFields.add(new LightTextField("Answer " + (i+1) + " Put Correct Answer Here."));
			answerFields.get(i).setX(50);
			answerFields.get(i).setY((Gdx.graphics.getHeight()) - 100 - 277-6-50 - (i*65));
			answerFields.get(i).setWidth(Gdx.graphics.getWidth() - 100);
			answerFields.get(i).setHeight(answerFields.get(i).font.getBounds("O").height+8);
			fields.add(answerFields.get(i));
			this.addActor(answerFields.get(i));
		}

		font = MenuBar.generator.generateFont(48);
	}
}
