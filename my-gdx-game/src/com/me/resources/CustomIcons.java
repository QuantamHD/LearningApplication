package com.me.resources;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.me.GUI.Main.MenuBar;
import com.me.screens.CreateScreen;

public class CustomIcons {
	static BitmapFont font;
	static BitmapFont TitleFont;
	
	public static void MultipleChoice(SpriteBatch batch,float x, float y,boolean drawTitle,boolean drawDarker,float alpha){
		if(font == null){
			font = MenuBar.generator.generateFont(44);
			font.setColor(Color.WHITE);
		}
		if(TitleFont == null){
			TitleFont = MenuBar.generator.generateFont(35);
			TitleFont.setColor(Color.WHITE);
		}
		
		batch.end();
		CreateScreen.renderer.setColor(Color.WHITE);
		CreateScreen.renderer.setProjectionMatrix(batch.getProjectionMatrix());
		CreateScreen.renderer.setTransformMatrix(batch.getTransformMatrix());
		CreateScreen.renderer.begin(ShapeType.Filled);
		CreateScreen.renderer.rect(x, y, 105,105);
		
		if(drawTitle){
			CreateScreen.renderer.setColor(new Color(1,1,1,0));
			CreateScreen.renderer.rect(x-55, y-TitleFont.getBounds("MultipleChoice").height-10, TitleFont.getBounds("MultipleChoice").width+8, 2);
		}
		
		CreateScreen.renderer.setColor(new Color(0.2352f,0.8352f,0.6862f,1f));
		if(drawDarker)
			CreateScreen.renderer.setColor(new Color(0.2352f,0.8352f,0.6862f,1f).sub(0.12f, 0.12f, 0.12f, 0));
		
		CreateScreen.renderer.rect(x+10.0f, y+10.0f, 85, 85);
		CreateScreen.renderer.end();
		batch.begin();
		
		font.draw(batch, "A.", x+14.5f+1.5f, y+89);
		font.draw(batch, "B.", x+59.5f-2.5f, y+89);
		font.draw(batch, "C.", x+14.5f+1.5f, y+51);
		font.draw(batch, "D.", x+59.5f-2.5f, y+51);	
		 
		TitleFont.setColor(new Color(1,1,1,1*alpha));
		if(drawTitle)
			TitleFont.draw(batch, "Multiple Choice", x-55, y-3);
	}
}
