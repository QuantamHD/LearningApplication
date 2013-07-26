package com.me.Function;

import java.lang.reflect.Method;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.me.GUI.Main.LightTextButton;
import com.me.GUI.Main.MenuBar;

public class MultipleChoiceQuestion extends Question {
	private Array<String> QuestionAndAnswers;
	private Array<LightTextButton> buttons;
	String correctAnswer;
	
	public MultipleChoiceQuestion(Array<String> fromCreation){
		this.QuestionAndAnswers = fromCreation;// These are the Questions and Answers
		buttons = new Array<LightTextButton>();// These will be the Selection Buttons for the Questions
		int beginingIndex = 'a';
		String s = QuestionAndAnswers.get(1);
		for(int i = 0; i < buttons.size -1; i++){
			int character = beginingIndex + i;
			char temp = (char) character;
			String StringTemp = String.valueOf(temp);
			buttons.set(i, new LightTextButton(StringTemp,
					MenuBar.generator.generateFont(20),//Generating a font of size 20
					new ShapeRenderer()));// must Give Text A shapeRenderer
			
			this.addActor(buttons.get(i));
		}
	}
	
	@Override
	public void act(float delta){
		super.act(delta);
		
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha){
		
	}
}
