package com.me.questionTypes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.me.resources.Images;

public class QuestionType {
	Image img;

	public QuestionType(Image img) {
		this.img = img;
	}

	public static QuestionType getMultipleChoice() {
		return new QuestionType(Images.getMultipleChoice());
	}

	public static QuestionType getFreeResponse() {
		return new QuestionType(Images.getFreeResponse());
	}

	public static QuestionType getMatchingResponse() {
		return new QuestionType(Images.getMatchingResponse());
	}

	public static QuestionType getEssayResponse() {
		return new QuestionType(Images.getEssayResponse());
	}

	public static QuestionType getMathResponse() {
		return new QuestionType(Images.getMathResponse());
	}

	public Image getImage() {
		return this.img;
	}
}
