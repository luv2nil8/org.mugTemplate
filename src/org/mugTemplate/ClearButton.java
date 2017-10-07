package org.mugTemplate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearButton implements ActionListener {
	
	Handler handler;
	
	public ClearButton(Handler handler){
		this.handler = handler;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		handler.getObject(ID.Agent).clear();
		handler.getObject(ID.House).clear();
		handler.getObject(ID.Background).clear();
	}

}
