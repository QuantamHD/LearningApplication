package com.me.GUI.Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.Tools.WriteableAction;
import com.me.resources.CustomActions;

public class LightTextButton extends Actor {

	private BitmapFont    	font	  = null;
	private String        	text	  = null;
	private boolean       	drawLight = false;
	private boolean       	drawDark  = false;
	private boolean			touch	  = false;
	private boolean		    touchisDown = false;
	private boolean 		enterIsOn = false;
	private ShapeRenderer 	renderer  = null;
	public 	Rectangle 		rectangle = null;
	public  WriteableAction enter     = null;
	public  WriteableAction exit 	  = null;
	public  WriteableAction touchDown = null;
	public  WriteableAction touchUp   = null;
	public  float           mouseY    = 0;
	public  boolean 		touchable = true;


	public LightTextButton(String text, BitmapFont font,ShapeRenderer renderer) {
		this.font     = font;
		this.text     = text;
		this.renderer = renderer;
		this.enter = new WriteableAction();
		this.exit = new WriteableAction();
		this.touchDown = new WriteableAction();
		this.touchUp = CustomActions.giveButtonAction(text);
		rectangle = new Rectangle();
		float x = getX();
		float y = getY();
		this.setBounds(
				x,
				y, 
				getTextBounds().width, 
				getTextBounds().height); 
		mouseY = Gdx.app.getGraphics().getHeight()-Gdx.input.getY();
	}

	public void draw(SpriteBatch batch, float parentAlpha) {
		drawTextHighLights(batch);
		font.draw(
				batch,//SpriteBatch 
				text, //String "TEXT PASSED TO CLASS"
				getX(), getY()+getTextBounds().height);//THE X AND Y LOCATION OF THE ACTOR
		touchUp.draw(batch, parentAlpha);


	}

	public void touchable(boolean touchable){
		this.touchable = touchable;
	}

	public void act(float delta){
		super.act(delta);
		this.setBounds(getX(), getY(), getTextBounds().width, getTextBounds().height);

		mouseY = Gdx.app.getGraphics().getHeight() - Gdx.input.getY();

		rectangle.x      = getX();
		rectangle.y      = getY();
		rectangle.width  = getTextBounds().width;
		rectangle.height = getTextBounds().height;

		if(touchable){
			if(this.enter()    ) this.enter.Actions();
			if(this.exit()     ) this.exit.Actions();
			if(this.touchDown()) this.touchDown.Actions();
			if(this.touchUp()  ) this.touchUp.Actions(true);
		}
		
		touchUp.act(delta);
	}

	public void drawTextHighLights(SpriteBatch batch){
		float y      = getY();// Y LOCATION OF THE MENU BAR
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
					getX()-20 , y+(getTextBounds().height+20),//   SETS THE X ANY Y LOCAITION OF THE RECTANGLE TO
					getTextBounds().width+40, // SETS THE WIDTH OF THE RECTANGLE 
					-height-40);// SETS THE HEIGHT OF THE RECTANGLE 

			//IMPORTANT IMPORTANT IMPORTANT IMPORTANT IMPORTANT IMPORTANT IMPORTANT IMPORTANT IMPORTANT IMPORTANT IMPORTANT IMPORTANT 

			renderer.end();//   ENDS THE SHAPER RENDERER
			batch.begin();// BEGINS THE SPRITE BATCH TO RESUME NORMAL FUNCTIONALITY
		}
	}

	public TextBounds getTextBounds() {
		return font.getBounds(text);// RETURNS THE BOUNDS OF THE GIVEN TEXT FEILD
	}

	public boolean enter(){

		if(rectangle.contains(Gdx.input.getX(), mouseY) && !enterIsOn){
			enterIsOn = true;
			drawLight = true;
			return true;
		}
		return false;
	}

	public boolean exit(){
		if(!rectangle.contains(Gdx.input.getX(), mouseY) && enterIsOn){
			enterIsOn = false;
			drawLight = false;
			return true;
		}
		return false;
	}

	public boolean touchDown(){
		if(rectangle.contains(Gdx.input.getX(), mouseY) && Gdx.input.isTouched()&&!touchisDown){
			this.touchisDown = true;
			this.touch = true;
			return true;
		}
		return false;
	}

	public boolean touchUp(){
		if(rectangle.contains(Gdx.input.getX(), mouseY) && !Gdx.input.isTouched() && touch){
			touchisDown = false;
			this.touch = false;
			return true;
		}
		
		return false;
	}

	public void addExitAction(WriteableAction action){
		this.exit = action;
	}

	public void addEnterAction(WriteableAction action){
		this.enter = action;
	}

	public void addTouchDownAction(WriteableAction action){
		this.touchDown = action;
	}

	public void addTouchUpDown(WriteableAction action){
		this.touchUp = action;
	}
}