package org.mugTemplate;

import java.util.LinkedList;

public class ButtonHandler {
	
	LinkedList<MenuButton> object = new LinkedList<MenuButton>();

	
	public void addObject(MenuButton object){
		this.object.add(object);
	}
	public void removeObject(MenuButton object){
		this.object.remove(object);
	}
	
	public MenuButton getObject(ID id){
		MenuButton object = null;
		for(int i = 0; i < this.object.size(); i++){
			MenuButton tempObject = this.object.get(i);
			if(tempObject.id == id){
				object = tempObject;
			}
		}
		return object;
	}

}
