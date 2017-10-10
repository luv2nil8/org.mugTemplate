package org.mugTemplate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.JSONObject;

public class AgentButton extends MenuButton implements ActionListener {
	
	private Handler handler;
	
	public  AgentButton (Handler handler){
		this.handler = handler;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		handler.getObject(ID.Agent).addAgent(null);
		
	}
	
}
