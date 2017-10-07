package org.mugTemplate;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	LinkedList<CanvasImage> object = new LinkedList<CanvasImage>();
	public void tick(){
		for(int i = 0; i < object.size(); i++){
			CanvasImage tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	public void render(Graphics g){
		for(int i = 0; i < object.size(); i++){
			CanvasImage tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(CanvasImage object){
		this.object.add(object);
	}
	public void removeObject(CanvasImage object){
		this.object.remove(object);
	}
	
	public CanvasImage getObject(ID id){
		CanvasImage object = null;
		for(int i = 0; i < this.object.size(); i++){
			CanvasImage tempObject = this.object.get(i);
			if(tempObject.id == id){
				object = tempObject;
			}
		}
		return object;
	}

}
