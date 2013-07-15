package com.me.mygdxgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.me.screens.CreateScreen;
import com.me.screens.MainApp;
import com.me.screens.MultipleChoiceScreen;
import com.me.screens.ViewingScreen;

public class MainGame extends Game {
	
	
	
	public MainApp getMainApp() {
		return new MainApp(this);
	}

	public CreateScreen getCreateScreen() {
		return new CreateScreen(this);
	}

	public ViewingScreen getViewingScreen() {
		return new ViewingScreen(this);
	}
	
	public MultipleChoiceScreen getMultileChoiceScreen(){
		return new MultipleChoiceScreen(this);
	}

	public MainGame() {
	}

	@Override
	public void create() {
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

		if (getScreen() == null) {
			setScreen(getMainApp());
			
		}
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void setScreen(Screen screen) {
		super.setScreen(screen);
	}

}
