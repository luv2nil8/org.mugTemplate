package org.mugTemplate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class HouseButton implements ActionListener {
	
	Handler handler;
	
	public HouseButton(Handler handler){
		this.handler = handler;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "JPG & PNG Images", "jpg","JPG","jpeg" ,"PNG", "png");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showDialog(null, "Choose House Picture");
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       System.out.println("You chose to open this file: " +
	            chooser.getSelectedFile().getName());
	       
		    //check if it's a valid image
		    try 
		    {
				handler.getObject(ID.House).fitImage(ImageIO.read(chooser.getSelectedFile()));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    }
	}

}
