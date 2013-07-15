package com.me.GUI.Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.me.resources.CustomIcons;
import com.me.screens.CreateScreen;

public class QuestionSelectButton extends Actor{
	Rectangle rectangle     = null;
	boolean   enterIsOn     = false;
	boolean   touchisDown   = false;
	boolean   touch 		= false;
	boolean   darken  		= false;
	boolean   drawTitle 	= false;
	float     mouseY        = 0;
	
	public QuestionSelectButton(){
		rectangle = new Rectangle();
		this.getColor().a = 0;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha){
		CustomIcons.MultipleChoice(batch, getX(), getY(),drawTitle, darken,this.getColor().a);
	}
	
	@Override
	public void act(float delta){
		super.act(delta);
		mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
		updateRectangle();
		inputListner();
	}
	
	public void updateRectangle(){
		rectangle.height = 105;
		rectangle.width  = 105;
		rectangle.x      = getX();
		rectangle.y      = getY();
	}
	
	public void inputListner(){
		enter();
		exit();
		touchDown();
		touchUp();
	}
	
	public boolean enter(){

		if(rectangle.contains(Gdx.input.getX(), mouseY) && !enterIsOn){
			enterIsOn = true;
			darken = true;
			drawTitle = true;
			this.addAction(Actions.color(new Color(0,0,0,1),0.8f));
			return true;
		}
		return false;
	}

	public boolean exit(){
		if(!rectangle.contains(Gdx.input.getX(), mouseY) && enterIsOn){
			enterIsOn = false;
			darken = false;
			drawTitle = false;
			this.addAction(Actions.color(new Color(0,0,0,0),0.8f));
			return true;
		}
		return false;
	}

	public boolean touchDown(){
		if(rectangle.contains(Gdx.input.getX(), mouseY) && Gdx.input.isTouched()&&!touchisDown){
			this.touchisDown = true;
			this.touch = true;
			CreateScreen.goToMultipleChoice = true;
			MenuBar.goDown();
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
}  
