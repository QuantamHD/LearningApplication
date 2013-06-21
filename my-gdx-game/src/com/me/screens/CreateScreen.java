package com.me.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.me.GUI.LightButton;
import com.me.GUI.MenuBar;
import com.me.mygdxgame.MainGame;

public class CreateScreen extends AbstractScreen {
	MenuBar bar;
	LightButton createButton;
	LightButton saveButton;
	LightButton openButton;

	public CreateScreen(MainGame game) {
		super(game);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		
	
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		bar = new MenuBar();
		createButton = new LightButton("data/Create.png");
		createButton.setTouchable(Touchable.enabled);
		saveButton = new LightButton("data/Save.png");
		openButton = new LightButton("data/Open.png");
		bar.addButton(createButton);
		bar.addButton(openButton);
		bar.addButton(saveButton);
		stage.addActor(bar);
		
	}
}
