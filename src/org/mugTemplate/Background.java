package org.mugTemplate;

import java.awt.Color;
import java.awt.Graphics;

public class Background extends CanvasImage{

	public Background(int x, int y, int Width, int Height, ID id) {
		super(x, y, Width, Height, id);
		
	}
	

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, Width, Height);
	}
	

}
