package com.me.Tools;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class CustomShapes {
	static ShapeRenderer renderer ;
	
	public static void roundedRect(float x ,float y, float width,float height, float radius){
		float internalWidth = width - radius;
		float internalHeight= height - radius;
		
		renderer.circle(x, y, radius);
		renderer.circle(x+width, y, radius);
		renderer.circle(x, y+height, radius);
		renderer.circle(x+width, y+height, radius);
		renderer.rect(x, y, width+radius, height+radius);
	}
	
	public static void setColor(Color color){
		renderer.setColor(color);
	}
	
	public static void setRenderer(ShapeRenderer rendererArg){
		renderer = rendererArg;
	}
}
