package com.me.resources;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.GUI.Main.QuestionCreatorBox;
import com.me.Tools.WriteableAction;

public class CustomActions {

	public static WriteableAction giveButtonAction(String text){
		if(text.equals("New Question")){
			return new WriteableAction(){
				QuestionCreatorBox createBox;
				boolean activated = false;

				@Override 
				public void act(float delta){
					
					super.act(delta);
					if(activated){
						if(createBox == null){
							createBox = new QuestionCreatorBox();
							
						}
						createBox.act(delta);
					}
					
				}
				@Override
				public void draw(SpriteBatch batch, float parentAlpha){
					
					if(activated){
						super.draw(batch, parentAlpha);
						if(createBox != null){
							createBox.draw(batch, parentAlpha);
						}
					}
					
				}
				
				@Override
				public void Actions(boolean a){
					activated = a;
					
				}
			};
		}
		return new WriteableAction();
	}
}
