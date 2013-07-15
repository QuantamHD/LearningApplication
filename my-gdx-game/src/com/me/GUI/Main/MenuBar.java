package com.me.GUI.Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class MenuBar extends Group {
	protected     ShapeRenderer renderer   = null;
	public static SelectionBar selectBar   = null;
	private       BitmapFont font          = null;
	public static FreeTypeFontGenerator generator;
	private       int count                = 0;
	public static boolean menuBarClicked   = false;
	private 	  ButtonGroup selectBarButtons = null;
	private		  LightButton currentInstance;
    static boolean goDown = true;

	

	public MenuBar() {
		renderer  = new ShapeRenderer();
		selectBar = new SelectionBar();
		
		
		setBounds(
				getX(), getY(), 
				80, 
				Gdx.app.getGraphics().getHeight());

		generator = new FreeTypeFontGenerator(
				Gdx.files.internal
				("data/Kelson.ttf"));
		font = generator.generateFont(42);
		selectBarButtons = new ButtonGroup();
		this.addActor(selectBarButtons);


	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {		
		selectBar.draw(batch, parentAlpha);

		batch.end();
		renderer.setProjectionMatrix(batch.getProjectionMatrix());
		renderer.setTransformMatrix(batch.getTransformMatrix());
		renderer.setColor(new Color(0.447f, 0.694f, 0.831f, 1));
		renderer.begin(ShapeType.Filled);
		renderer.rect(0, 0, 80, Gdx.app.getGraphics().getHeight());
		renderer.end();
		batch.begin();
		drawChildren123(batch, parentAlpha);
		

	}

	public void addButton(LightButton button) {
		this.addActor(button);
	}
	
	public static void dontGoDown(){
		goDown = false;
	}
	
	public static void goDown(){
		goDown = true;
	}

	@Override
	public void act(float delta) {
		selectBar.act(delta);
		selectBarButtons.act(delta);
		positionButtons(delta);
		if(goDown)
		menuBarControls();
		chageMenuButtons();
	}

	public void menuBarControls(){
		if (Gdx.input.isTouched()) {
			if (noActorsSelected() && selectBar.getX() > 1 && SelectBarChecked()) {
				selectBar.addAction(Actions.moveTo(0, 0, 0.2f));
			}
		}
	}
	

	

	
	public boolean SelectBarChecked(){
		return !selectBar.rectangle.contains(Gdx.input.getX(), Gdx.input.getY());
	}
	
	public void positionButtons(float delta){
		for (Actor actor : getChildren()) {
			if (actor instanceof LightButton){
				actor.setX(8);
				actor.setY(Gdx.app.getGraphics().getHeight() - (count * (actor.getHeight()*1.45f))
						- 100);
				count++;
			}

			actor.act(delta);
		}

		count = 0;
	}

	public boolean noActorsSelected() {
		for (Actor act : getChildren()) {
			if(act instanceof LightButton){
				LightButton button = (LightButton)(act);
				if (button.drawBlue) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean selected() {

		float left1, left2;
		float right1, right2;
		float top1, top2;
		float bottom1, bottom2;
		float mx = Gdx.input.getX();
		float my = Gdx.app.getGraphics().getHeight() - Gdx.input.getY();

		left1 = selectBar.getY();
		left2 = mx;
		right1 = selectBar.getY() + selectBar.getHeight();
		right2 = mx;
		top1 = selectBar.getX();
		top2 = my;
		bottom1 =selectBar.getX() - Gdx.app.getGraphics().getWidth();
		bottom2 = my;

		if (bottom1 < top2)
			return false; // no collision

		if (top1 > bottom2)
			return false;

		if (right1 < left2)
			return false; // no collision

		if (left1 > right2)
			return false; // no collision

		return true;
	}

	public void drawChildren123(SpriteBatch batch, float parentAlpha){
		for (Actor actor : getChildren()) {
			actor.draw(batch, parentAlpha);
		}
	}

	public void chageMenuButtons(){
		for(Actor actor: getChildren()){
			if(actor instanceof LightButton){
				LightButton button = (LightButton)(actor);
				if(button.drawBlue){

					if(!(currentInstance == button)){
						this.selectBarButtons.flushButtons();
						this.selectBarButtons.addButton(button.getSelectBarButtons(renderer, font));
					}

					currentInstance = button;
				}
			}
		}
		if(noActorsSelected()){
			this.selectBarButtons.flushButtons();
			currentInstance = null;
		}
	}
}