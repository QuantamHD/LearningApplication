package com.me.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class LightTextButton extends Actor{
	
	BitmapFont font;
	public LightTextButton(){
		font = new BitmapFont();
		font.scale(3);
	}
	
	public void draw(SpriteBatch batch, float parentAlpha){
		//font.draw(batch, "CREATE", 120,Gdx.app.getGraphics().getHeight()-70);
	}
}
