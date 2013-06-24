package com.me.GUI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class LightTextButton extends Actor {

	private BitmapFont    font;
	private String        text;
	private boolean       drawLight;
	private boolean       drawDark;
	private ShapeRenderer renderer;

	public LightTextButton(String text, BitmapFont font,ShapeRenderer renderer) {
		this.font     = font;
		this.text     = text;
		this.renderer = renderer;
		float x = getX();
		float y = getY();
		this.setBounds(
				x,
				y, 
				getTextBounds().width, 
				getTextBounds().height);

		addListener(new InputListener() {
		
			public boolean touchDown(
					InputEvent event,
					float x, float y,
					int pointer, int button) {
			System.out.println("hello World");	
				return true;
			}

			public void touchUp(
					InputEvent event, 
					float x, float y,
					int pointer, int button) {
				
				System.out.println("hello World");
				drawDark  = true;
				drawLight = false;
			}

			@Override
			public void enter(
					InputEvent event, 
					float x, float y, 
					int pointer,
					Actor fromActor) {
				System.out.println("Hello World");
				drawLight = true;
			}

			public void exit(
					InputEvent event, 
					float x, float y, 
					int pointer,
					Actor fromActor) {
				
				
				drawLight = false;
			}
		});
	}

	public void draw(SpriteBatch batch, float parentAlpha) {
		drawTextHighLights(batch);
		font.draw(
				batch,//SpriteBatch 
				text, //String "TEXT PASSED TO CLASS"
				getX(), getY()+getTextBounds().height);//THE X AND Y LOCATION OF THE ACTOR
	}
	
	public void act(float delta){
		super.act(delta);
		this.setBounds(getX(), getY(), getTextBounds().width, getTextBounds().height);
	}
	
	public void drawTextHighLights(SpriteBatch batch){
		float x      = getX();// X LOCATION OF THE MENU BAR
		float y      = getY();// Y LOCATION OF THE MENU BAR
		float width  = getTextBounds() . width;//  WIDTH  OF THE MENU BAR 
		float height = getTextBounds() . height;// HEIGHT OF THE MENU BAR
		
		if(drawDark||drawLight){// CHECKS IF EITHER THE DRAW LIGHT HIGHLIGHT OR DARK HIGHLIGHT IS TRUE
			batch.end();// ENDS THE CURRENT SPRITEBATCH TO ALLOW SHAPERENDERER TO DO ITS JOB
			
			if (drawLight)// CHECKS TO SEE IF DRAW LIGHT COLOR IS ON 
				renderer.setColor(new Color(0.447f, 0.694f, 0.831f, 1));// CHANGES THE COLOR TO A LIGHTER COLOR THAN THE MENU
			
			
			if (drawDark)// CHECKS TO SEE IF DRAW DARK COLOR IS ON
				renderer.setColor(new Color(0.407f, 0.654f, 0.791f, 1));//CHANGES THE COLOR TO ONE DARKER THAN THE MENU BAR
			
			renderer.setProjectionMatrix( batch.getProjectionMatrix());// PART OF SHAPE RENDERER
			renderer.setTransformMatrix ( batch.getTransformMatrix() );//   PART OF SHAPE RENDERER
			renderer.begin(ShapeType.Filled);// SETS THE RENDERER TO FILLED INSTEAD OF OUTLINE
			
			//IMPORTANT IMPORTANT IMPORTANT IMPORTANT IMPORTANT IMPORTANT IMPORTANT IMPORTANT IMPORTANT IMPORTANT IMPORTANT IMPORTANT
			
			renderer.rect(
					x , y+getTextBounds().height,//   SETS THE X ANY Y LOCAITION OF THE RECTANGLE TO BE DRAWN
					width, // SETS THE WIDTH OF THE RECTANGLE 
					-height);// SETS THE HEIGHT OF THE RECTANGLE 
			
			//IMPORTANT IMPORTANT IMPORTANT IMPORTANT IMPORTANT IMPORTANT IMPORTANT IMPORTANT IMPORTANT IMPORTANT IMPORTANT IMPORTANT 
			
			renderer.end();//   ENDS THE SHAPER RENDERER
			batch.begin();// BEGINS THE SPRITE BATCH TO RESUME NORMAL FUNCTIONALITY
		}
	}
	
	public TextBounds getTextBounds() {
		return font.getBounds(text);// RETURNS THE BOUNDS OF THE GIVEN TEXT FEILD
	}
}
