package com.me.GUI.MultipleChoice;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.me.GUI.Main.MenuBar;
import com.me.Tools.CustomShapes;
import com.me.screens.MultipleChoiceScreen;

public class LightTextField extends Actor {

	public ShapeRenderer renderer;
	private String text;
	private String preText = "";
	public int cursorX = 0;
	public int cursorY = 0;
	public int drawCursorX = 0;
	long cursorOn = 0;
	long cursorOff = 0;
	public BitmapFont font;
	boolean listenToMe = false;

	public LightTextField(String preText) {
		renderer = MultipleChoiceScreen.renderer;
		CustomShapes.setRenderer(renderer);
		text = "";
		font = MenuBar.generator.generateFont(24);
		font.setColor(Color.BLACK);

		cursorOff = TimeUtils.millis() + 320;
		cursorOn = TimeUtils.millis() + 900;

		this.setPreText(preText);

		addListener(new ClickListener() {

			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// listenToMe = true;
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				listenToMe = true;
				// return true;
			}

			@Override
			public boolean keyTyped(InputEvent event, char key) {
				if (listenToMe) {
					if (key != 0 && key != 8 && key != 127) {
						if (text.length() > 0) {
							if (!Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
								text = text.substring(0, cursorX)
										+ key
										+ text.substring(cursorX, text.length());
								drawCursorX++;
							} else {
								text = text.substring(0, cursorX)
										+ "\n"
										+ text.substring(cursorX, text.length());
								cursorY++;
								drawCursorX = 0;
							}
						} else {
							text = "" + key;
							drawCursorX++;
						}

						cursorX++;

					}
					if (Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) {
						if (cursorX > 0) {
							if (drawCursorX == 0) {
								if (cursorY - 1 > -1) {
									if (getLineOfText(cursorY).length() < 1)
										drawCursorX = getLineOfText(cursorY - 1)
												.length();
									else
										drawCursorX = getLineOfText(cursorY - 1)
												.length() - 1;

									cursorY--;
								}
							} else {
								drawCursorX--;
							}

							text = text.substring(0, cursorX - 1)
									+ text.substring(cursorX, text.length());
							cursorX--;
						}
					}

					if (Gdx.input.isKeyPressed(Input.Keys.TAB)) {
						text += "      ";
						cursorX += 6;
					}
					if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
						drawCursorX--;
						if (cursorX == 0 && cursorY > 0) {
							cursorY--;
						}

						else {
							cursorX--;
							if (cursorX < 0)
								cursorX = 0;
						}

					}
					if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
						if (text.length() >= cursorX + 1)
							cursorX++;
						drawCursorX++;
					}

				}
				return true;
			}
		});

	}

	public boolean youShouldListenToMe() {
		return this.listenToMe;
	}

	public String getLineOfText(int line) {
		// if(line < 0){
		// throw new
		// IllegalArgumentException("Line Number Must Be Greater Than Zero You Entered: "
		// + line);
		// }

		int nextLine = 0;
		int lineCounter = 0;
		float initalHeight = font.getBounds("O").height;

		// System.out.println(font.getWrappedBounds(text, getWidth()-5).height);
		if (initalHeight == font.getWrappedBounds(text, getWidth() - 5).height
				&& line == 0) {
			return text.substring(0, text.length()).replace("\n", "");
		}

		if (line == 0) {
			for (int i = 0; i <= text.length(); i++) {
				if (font.getWrappedBounds(text.subSequence(0, i),
						getWidth() - 5).height > initalHeight) {
					return text.substring(0, i).replace("\n", "");
				}
			}
		}

		for (int i = 0; i <= text.length(); i++) {
			// System.out.println(nextLine);
			if (font.getWrappedBounds(text.substring(0, i), getWidth()).height > initalHeight) {
				initalHeight = font.getWrappedBounds(text.substring(0, i),
						getWidth() - 5).height;
				lineCounter++;

				if (lineCounter == line) {
					for (int j = nextLine; j <= text.length(); j++) {
						if (font.getWrappedBounds(text.substring(nextLine, j),
								getWidth()).height > font.getBounds("O").height) {

							return text.substring(nextLine, j)
									.replace("\n", "");
						}
					}
					return text.substring(nextLine, text.length()).replace(
							"\n", "");
				}
			} else {
				nextLine = i;
			}

		}
		return "";
	}

	@Override
	public void draw(SpriteBatch batch, float alpha) {

		batch.end();
		renderer.setProjectionMatrix(batch.getProjectionMatrix());
		renderer.setTransformMatrix(batch.getTransformMatrix());
		renderer.begin(ShapeType.Filled);
		if (this.listenToMe) {
			renderer.setColor(Color.BLACK);
			renderer.rect(getX() - 3, getY() - 3, getWidth() + 6,
					getHeight() + 6);
		}
		renderer.setColor(Color.WHITE);
		//renderer.rect(getX(), getY(), getWidth(), getHeight());
		CustomShapes.roundedRect(getX(), getY(), getWidth(), getHeight(), 5);
		renderer.setColor(Color.BLACK);

		if (listenToMe) {
			if (text.length() > 0 && cursorX != 0) {
				if (cursorOn > TimeUtils.millis()) {
					String s = this.getLineOfText(cursorY);
					// System.out.println(s);
					renderer.rect(
							getX()
									+ font.getBounds(s
											.substring(0, drawCursorX)).width
									+ 4,
							getY() - (cursorY * font.getLineHeight())
									+ getHeight() - font.getBounds("O").height
									- 5, 1, font.getBounds("O").height + 2);
					cursorOff = TimeUtils.millis() + 320;
				} else {
					if (cursorOff < TimeUtils.millis()) {
						cursorOn = TimeUtils.millis() + 800;
					}
				}
			} else {
				if (cursorOn > TimeUtils.millis()) {
					renderer.rect(getX() + 5,
							getY() + getHeight() - 5
									- font.getBounds("O").height, 1,
							font.getBounds("O").height + 2);
					cursorOff = TimeUtils.millis() + 320;
				} else {
					if (cursorOff < TimeUtils.millis()) {
						cursorOn = TimeUtils.millis() + 800;
					}
				}
			}
		}

		renderer.end();
		batch.begin();

		if (text.length() < 1) {
			font.setColor(Color.LIGHT_GRAY);
			font.drawWrapped(batch, preText, getX() + 5, getY() + getHeight()
					- 5, getWidth() - 5);
		}

		font.setColor(Color.BLACK);
		font.drawWrapped(batch, text, getX() + 5, getY() + getHeight() - 5,
				getWidth() - 5);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		String s = "Hello World My Name is Steve\n";
		this.getLineOfText(cursorY - 1).length();

		if (Gdx.input.isTouched() && !(this.hit(getX(), getY(), true) == this)) {
			this.listenToMe = false;
			System.out.println("Hey I am tots");
		}

	}

	public String getTextTyped() {
		return this.text;
	}

	public void setPreText(String s) {
		this.preText = s;
	}

	public String getText() {
		return this.text;
	}

}
