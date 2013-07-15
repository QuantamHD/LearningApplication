package com.me.mygdxgame;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "";
		cfg.useGL20 = false;
		cfg.width = 1280;
		cfg.height = 720;
		cfg.addIcon("data/Icon128.png", FileType.Internal);
		cfg.addIcon("data/Icon32.png", FileType.Internal);
		cfg.addIcon("data/Icon16.png", FileType.Internal);
		
		new LwjglApplication(new MainGame(), cfg);
	}
}
