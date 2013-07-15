package com.me.GUI.Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

public class ButtonGroup extends Group {
	Array<Actor> actors;//HOLDS THE LIST OF BUTTONS FOR THE MENUBAR CLASS THE LEFT MOST PART OF THE SCREEN

	public ButtonGroup() {
		actors = new Array<Actor>();//INITALIZING THE ARRAY(NOT A JAVA ARRAY)
		setX(80);//SETTING THE GROUP ITSELF TO 80 PX TO THE RIGHT
		setY(Gdx.app.getGraphics().getHeight());//ALWAYS STARTS FROM THE TOP OF THE SCREEN
	}

	public void addButton(Actor actor) {//TAKES ANY ACTOR AND WILL ADD IT TO THE ARRAY LIST
		this.addActor(actor);
	}

	public void addButton(Array<LightTextButton> actor) {
		for (Actor act : actor) {//USES ENCHANCE FOR LOOP TO RUN THROUH ALL THE ACTORS IN THE GIVEN PARAMTER AND ADD THEM
			this.addActor(act);
		}
	}

	public void draw(SpriteBatch batch, float parentAlpha) {
		drawButtons(batch, parentAlpha);//DRAWS THE ACTORS IN THEIR FINAL LOCATIONS
	}

	public void act(float delta) {
		super.act(delta);//SUPER.ACT NEEDS TO BE CALLED FOR EVERY ACTOR THAT OVERRIDES ACT ALSO GROUP IS AN ACTOR THAT HAS OTHER ACTORS
		placeButtons(delta);//USES THE PLACEBUTTON METHOD TO PLACE THE BUTTONS IN THE RIGHT X AND Y LOCATION
	}

	/**
	 * DONT USE THIS METHOD IT MAY END IN A NULL POINTER NEEDS TO BE REWRITTEN
	 *
	 */
	public void flushButtons() {
		for (Actor actor : getChildren()){//THIS DEFINITLY WILL END IN A NULL POINTER
			removeActor(actor);
		}
	}

	public void placeButtons(float delta) {//MEANT TO PLACE THE BUTTONS IN THE CORRECT ORDER GOING VERTICAL DOWN
		for (int i = 0; i < getChildren().size; i++) {//GOES THROUGHT THE ARRAY TO PLACE THE BUTTONS IN THE CORRECT VISUAL ORDER

			Actor actor = (getChildren().get(i));//NOT REALLY AN IMPORTANT LINE
			if (i == 0) {//CHECKS TO SEE IF THIS IS THE FIRST ACTOR TO PLACE AT THE TOP OF THE LINE IN THE SELECTION BAR
				actor.setX(getX()+((320-actor.getWidth())/2));//PLACES THE BUTTONS IN THE MIDDLE OF THE SELECTION BAR 
				actor.setY(Gdx.app.getGraphics().getHeight()-135);//MOVES THE ACTOR DOWN 135 PX
			}
			else{
				actor.setX(getX()+((320-actor.getWidth())/2));//THE REST OF THE ACTORS ARE PLACE IN THE MIDDLE OF THE SLECTION BAR
				actor.setY((Gdx.app.getGraphics().getHeight() - (i * (2*actor.getHeight()))) - 150);//MOVES THE ACTORS DOWN AN EQUAL DISTANCE
			}
			actor.act(delta);//MAKES ANY CHILD IN THIS GROUP ACT
		}
	}

	public void drawButtons(SpriteBatch batch, float parentAlpha) {
		for (Actor actor : getChildren()) {
			if(!(actor.getX() == 0)&&!(actor.getY() == 0))// THIS LINE IS TO PREVENT ANY FLASHING BUTTONS DUE TO BEING FLUSHED FROM THE MENU BAR
				actor.draw(batch, parentAlpha);//DRAWS THE ACTOR
		}
	}
}