package org.mugTemplate;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main extends Canvas implements Runnable{

	private static final long serialVersionUID = -3975188497908332218L;
	
	public static final int WIDTH = 1369, HEIGHT = 525;
	
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	
	
	public Main(){
		
		handler = new Handler();
		
		new FileDrop(this, new FileDrop.Listener() {
			public void filesDropped(java.io.File[] images) {

				try {
					int Ax = handler.getObject(ID.Agent).getX();
					int Ay = handler.getObject(ID.Agent).getY();
					int Aw = handler.getObject(ID.Agent).Width;
					int Ah = handler.getObject(ID.Agent).Height;
					int Hx = handler.getObject(ID.House).getX();
					int Hy = handler.getObject(ID.House).getY();
					int Hw = handler.getObject(ID.House).Width;
					int Hh = handler.getObject(ID.House).Height;

					if(
							getMousePosition().getX() >= Ax 
						&& 	getMousePosition().getY() >= Ay 
						&&	getMousePosition().getX() <= Ax+Aw
						&&  getMousePosition().getY() <= Ay+Ah)
					{
						
						handler.getObject(ID.Agent).addAgent((images[0]));
					}else if(
							getMousePosition().getX() >= Hx 
						&& 	getMousePosition().getY() >= Hy 
						&&	getMousePosition().getX() <= Hx+Hw
						&&  getMousePosition().getY() <= Hy+Hh)
					{
						handler.getObject(ID.House).fitImage(ImageIO.read(images[0]));
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		handler.addObject(new Background(0, 0, WIDTH, HEIGHT, ID.Background));
		handler.addObject(new Agent(50, 40, 300, 420, ID.Agent));
		handler.addObject(new House(WIDTH - 470 , 40, 420, 315, ID.House));
		new Window (WIDTH, HEIGHT, "Mug Template", this, handler);
		
		
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;

	}
	
	public synchronized void stop(){
		try{
			thread.join();	
			running = false;
		}catch(Exception e){
			
		}
	}
	public void run(){
        long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
		int frames = 0;
        while(running){
            long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: " + frames);
				//System.out.println(getMousePosition());
				frames = 0;
			}
		}
		stop();
	}
	private void tick(){
		handler.tick();
		
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
		handler.render(g);
		
		g.dispose();
		bs.show();
	}
	public static void main(String[] args) {
		new Main();
	}

}
