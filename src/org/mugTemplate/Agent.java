package org.mugTemplate;

import java.awt.Color;
import java.awt.Graphics;

public class Agent extends CanvasImage{
	
	public Agent(int x, int y, int Width, int Height, ID id) {
		super(x, y, Width, Height, id);
		
	}


	
	@Override
	public void tick() {
		
	}

	
	@Override
	public void render(Graphics g) {
		if(scaled == null){
		g.setColor(new Color(240,240,240));
		g.fillRect(x, y, Width, Height);
		}
		
		else{
			g.drawImage(scaled, x, y, null);
		}
		
	}
	

}
