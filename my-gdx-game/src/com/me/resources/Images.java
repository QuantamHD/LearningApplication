package com.me.resources;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Images {
	private static Image multipleChoice = null;
	private static Image matching       = null;
	private static Image freeResponse   = null;
	private static Image essayResponse  = null;
	private static Image mathResponse   = null;

	public static Image getMultipleChoice() {
		if (multipleChoice == null)
			
			multipleChoice = new Image(
					new Texture(""));
		
		return multipleChoice;
	}

	public static Image getMatchingResponse() {
		if (matching == null)
			
			matching = new Image(
					new Texture(""));
		
		return matching;
	}

	public static Image getFreeResponse() {
		if (freeResponse == null)
			
			freeResponse = new Image(
					new Texture(""));
		
		return freeResponse;
	}

	public static Image getEssayResponse() {
		if (essayResponse == null)
			
			essayResponse = new Image(
					new Texture(""));
		
		return essayResponse;
	}

	public static Image getMathResponse() {
		if (mathResponse == null)
			
			mathResponse = new Image(
					new Texture(""));
		
		return mathResponse;
	}
}
