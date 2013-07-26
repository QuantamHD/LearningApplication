package com.me.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.me.GUI.Main.MenuBar;
import com.me.GUI.MultipleChoice.LightTextField;
import com.me.GUI.MultipleChoice.MultipleChoiceSkin;
import com.me.mygdxgame.MainGame;

public class MultipleChoiceScreen extends AbstractScreen {
	CreateScreen create;
	LightTextField field;
	MultipleChoiceSkin skin;
	public static ShapeRenderer renderer;
	
	public MultipleChoiceScreen(MainGame game,CreateScreen create) {
		super(game);
		this.create = create;
		renderer = new ShapeRenderer();
		skin = new MultipleChoiceSkin();
		
	}
	public MultipleChoiceScreen(MainGame game) {
		super(game);
	}	

	@Override
	public void render(float delta) {
		super.render(delta);
		for(LightTextField textF: skin.getTextFields()){
			if(textF.youShouldListenToMe())
				stage.setKeyboardFocus(textF);
		}
	}

	@Override
	public void show() {
		super.show();
		Gdx.input.setInputProcessor(stage);
		CreateScreen.goToMultipleChoice = false;
		MenuBar.goDown();  
		stage.addActor(skin);
		for(LightTextField textF: skin.getTextFields()){
			if(textF.youShouldListenToMe())
				stage.setKeyboardFocus(textF);
		}
		this.setColor(0.85882f, 0.26666f, 0.21568f, 1f);
	}

	@Override
	public void dispose() {
		this.dispose();
		stage.dispose();
	}
}
