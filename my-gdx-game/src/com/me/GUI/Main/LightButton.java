package com.me.GUI.Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;

public class LightButton extends Actor {
	protected Texture       buttonImg     = null;
	protected boolean       drawBlue      = false;
	protected boolean       drawLightBlue = false;
	protected ShapeRenderer renderer      = null;
	protected SelectionBar  selectBar     = null;
	protected String        buttons       = null;
	protected Array<LightTextButton> lightTextButtons;
	

	public LightButton(String path,String buttons) {
		// selectBar = new SelectionBar();
		renderer  = new ShapeRenderer();//INITIALZES THE SHAPERENDERER
		buttonImg = new Texture(path);// GETS THE BUTTON IMAGE FOR THE PASSED PATH
		setBounds(getX(),//SETS THE BOUNDS TO ALLOW THE BUTTON TO BE CLICKED
				  getY(), 
				  buttonImg.getWidth(), 
				  buttonImg.getHeight());
		
		this.buttons = buttons;//A STRING CONTAING WORDS AND @ SYMBOLS WHICH IS WHAT THE BUTTONS ARE CREATED OUT OF
		this.lightTextButtons = new Array<LightTextButton>();
		
		addListener(new InputListener() {
			
			public boolean touchDown(				
					InputEvent event, 
					float x, float y,
					int pointer, int button) {
	
				
				return true;
			}

			public void touchUp(
					InputEvent event, 
					float x, float y,
					int pointer, int button) {
				
				
				MenuBar.selectBar.addAction(
						Actions.moveTo
					 (320 + 80, 0, 0.2f));
				
				drawBlue      = true;
				drawLightBlue = false;
			}

			public void enter(
					InputEvent event, 
					float x, float y, 
					int pointer,
					Actor fromActor) {
				
				
				if (!drawBlue)
					drawLightBlue = true;
			}

			public void exit(
					InputEvent event,
					float x, float y, 
					int pointer,
					Actor fromActor) {
				
				
				drawLightBlue = false;
			}
		});
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		drawButtonHighLights(batch);
		batch.draw(buttonImg, getX(), getY());
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		turnOffButton();// TURNS OF THE DARK HIGHLIGHT IF ONE OF THE BUTTONS IS DOWN AND CLICKED SOMEWHERE ELSE
		
	}
	
	public String getButtonNames(){
		return this.buttons;
	}
	
	public Array<LightTextButton> getSelectBarButtons(ShapeRenderer renderer, BitmapFont font){//could Be Modifed to work better
		this.lightTextButtons = new Array<LightTextButton>();
	
		String temp = "";
		for(int i = 0; i<buttons.length(); i++){
			if(buttons.substring(i, i+1).equals("@")){
				lightTextButtons.add(new LightTextButton(temp,font,renderer));
				temp = "";
			}
			else
				temp+=buttons.substring(i,i+1);
			
		}
		lightTextButtons.get(0).touchable(false);
		return this.lightTextButtons;
	}

	public void drawButtonHighLights(SpriteBatch batch){
		if (drawBlue||drawLightBlue) {
			
			if(drawBlue)
				renderer.setColor(new Color(0.316f, 0.607f, 0.741f, 1));//darkBlue
			if(drawLightBlue)
				renderer.setColor(new Color(0.525f, 0.772f, 0.909f, 1));//LightBlue
			
			batch.end();
			renderer.setProjectionMatrix(batch.getProjectionMatrix());
			renderer.setTransformMatrix(batch.getTransformMatrix());
			renderer.begin(ShapeType.Filled);
			renderer.rect(0, getY() - 7, 80, getHeight() + 14);
			renderer.end();
			batch.begin();
		}
	}
	
	public void turnOffButton(){
		if (Gdx.input.isTouched()) {
			float left1, left2;
			float right1, right2;
			float top1, top2;
			float bottom1, bottom2;
			float mx = Gdx.input.getX();
			float my = Gdx.input.getY() - Gdx.app.getGraphics().getHeight();

			left1 = getX();
			left2 = mx;
			right1 = getX() + getWidth();
			right2 = mx;
			top1 = getY();
			top2 = my;
			bottom1 = getY() + getHeight();
			bottom2 = my;
			
			boolean selectBarClicked = !MenuBar.selectBar.rectangle.contains(Gdx.input.getX(), Gdx.input.getY());
			
			
 
			if (bottom1 < top2&& selectBarClicked && MenuBar.goDown)
				drawBlue = false; 
			if (top1 > bottom2&& selectBarClicked && MenuBar.goDown)
				drawBlue = false; 
			if (right1 < left2&& selectBarClicked && MenuBar.goDown)
				drawBlue = false; 
			if (left1 > right2&& selectBarClicked && MenuBar.goDown)
				drawBlue = false; 
		} 
	}
	

}
