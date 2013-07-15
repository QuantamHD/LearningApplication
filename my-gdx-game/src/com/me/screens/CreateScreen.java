package com.me.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.me.GUI.Main.LightButton;
import com.me.GUI.Main.MenuBar;
import com.me.mygdxgame.MainGame;

public class CreateScreen extends AbstractScreen {
	public static MenuBar     bar          = null;
	LightButton createButton = null;
	LightButton saveButton   = null;
	LightButton openButton   = null;
	public static ShapeRenderer renderer;
	public static boolean goToMultipleChoice = false;;
	

	public CreateScreen(MainGame game) {
		super(game);
		renderer = new ShapeRenderer();
		
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		
		
		if(goToMultipleChoice){
			this.game.setScreen(new MultipleChoiceScreen(game,this));
		}
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		
		bar          = new MenuBar    (                  );
		createButton = new LightButton( "data/Create.png","Create A@New Question@New Project@");
		saveButton   = new LightButton( "data/Save.png","Save@Save@Save As@");
		openButton   = new LightButton( "data/Open.png" ,"Open A@Project@Recent Project@");
		
		bar.addButton(createButton);
		bar.addButton(openButton  );
		bar.addButton(saveButton  );
		
		stage.addActor(bar);
		
	}
	@Override
	public void dispose(){
		this.dispose();
		stage.dispose();
	}
}
