package org.mugTemplate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonInput implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent button) {

	System.out.println(button.getActionCommand());
	}

}
