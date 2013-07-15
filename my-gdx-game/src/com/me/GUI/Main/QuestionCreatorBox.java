package com.me.GUI.Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class QuestionCreatorBox extends Actor{
	private Rectangle       rectangle   = null;
	private ShapeRenderer   renderer    = null;
	private BitmapFont      font;
	private boolean			touch	    = false;
	private boolean		    touchisDown = false;
	private boolean 		enterIsOn   = false;
	private float    		mouseY      = 0;
	private SpriteBatch     batch       = null;
	private QuestionSelectButton button = null;
	private float widthS  				= 0;
	private float heightS               = 0;
	
	public QuestionCreatorBox(){
		widthS = Gdx.graphics.getWidth();
		heightS = Gdx.graphics.getHeight();
		renderer = new ShapeRenderer();
		font = MenuBar.generator.generateFont(55);
		font.setColor(Color.WHITE);
		rectangle = new Rectangle(getX(),getY(),580,376);
		button = new QuestionSelectButton();
		this.setScaleX(0);
		this.setScaleY(0);
		this.addAction(Actions.scaleTo(1, 1, 0.8f));
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha){
		if(this.batch == null){
			this.batch = batch;
		}
		
		batch.end();
		renderer.setColor(new Color(0.2352f,0.8352f,0.6862f,1));
		renderer.setProjectionMatrix(batch.getProjectionMatrix());
		renderer.setTransformMatrix(batch.getTransformMatrix());
		renderer.begin(ShapeType.Filled);
		renderer.rect(getX(), getY(), 580*getScaleX(),376*getScaleY());
		renderer.setColor(Color.WHITE);
		renderer.rect(getX()+10, getY()+350-font.getBounds("What Kind Of Question").height-5, 560*getScaleX(),2);
		renderer.end();
		batch.begin();
		
		if(this.getActions().size == 0){
			drawText("What Kind Of Question?");
			button.draw(batch, parentAlpha);
		}
	}
	
	public void drawText(String text){
		if(this.getActions().size < 1)
			font.drawWrapped(batch, text,getX()+10, getY()+350, 580);
	}
	
	@Override
	public void act(float delta){
		super.act(delta);
		convertMouseAxis();
		centerOpener();
		inputListners();
		if(!enter())
			MenuBar.goDown();
		button.act(delta);
		button.setX(getX() + 79);
		button.setY(getY() + 179);
	}
	
	public void inputListners(){
		enter();
		exit();
		touchDown();
		touchUp();
	}
	
	public void centerOpener(){
		if(this.getActions().size > 0){
			this.setX((Gdx.app.getGraphics().getWidth()/2)-((578*getScaleX())/2));
			this.setY((Gdx.app.getGraphics().getHeight()/2)-((376*getScaleY())/2));
		}
		
		if(heightS != Gdx.graphics.getHeight() || widthS != Gdx.graphics.getWidth()){
			this.setX((Gdx.app.getGraphics().getWidth()/2)-((578*getScaleX())/2));
			this.setY((Gdx.app.getGraphics().getHeight()/2)-((376*getScaleY())/2));
			heightS = Gdx.graphics.getHeight();
			widthS = Gdx.graphics.getWidth();
		}
		
		rectangle.x = getX();
		rectangle.y = getY();
		rectangle.width = 580*getScaleX();
		rectangle.height = 376*getScaleY();
		
	}
	
	public void convertMouseAxis(){
		mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
	}
	
	public boolean enter(){
		if(rectangle.contains(Gdx.input.getX(), mouseY)){
			return true;
		}
		return false;
	}

	public boolean exit(){
		if(!rectangle.contains(Gdx.input.getX(), mouseY) && enterIsOn){
			enterIsOn = false;
			return true;
		}
		return false;
	}

	public boolean touchDown(){
		if(rectangle.contains(Gdx.input.getX(), mouseY) && Gdx.input.isTouched()&&!touchisDown){
			this.touchisDown = true;
			this.touch = true;
			MenuBar.dontGoDown();
			return true;
		}
		return false;
	}

	public boolean touchUp(){
		if(rectangle.contains(Gdx.input.getX(), mouseY) && !Gdx.input.isTouched() && touch){
			touchisDown = false;
			this.touch = false;
			MenuBar.dontGoDown();
			return true;
		}
		return false;
	}
}
