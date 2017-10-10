package org.mugTemplate;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 

public class Window extends Canvas{

	private static final long serialVersionUID = 8437805062548940328L;

	public Window (int width, int height, String title, Main main, Handler handler){
		
		JFrame frame = new JFrame(title);
		
		JPanel menuPanel = new JPanel();
		
		frame.add(menuPanel, BorderLayout.NORTH);
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.X_AXIS));
		
		Box horizontalBox = Box.createHorizontalBox();
		menuPanel.add(horizontalBox);
		
		AgentBox agentBox = new AgentBox(handler);
		agentBox.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
		        String agentName = (String)cb.getSelectedItem();
		        System.out.println(agentName);
		          try {
						handler.getObject(ID.Agent).fitImage(CanvasImage.getImageFromKey(agentName));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}			
		});
		horizontalBox.add(agentBox);
		
		
		JButton clearButton = new JButton("Clear");
		horizontalBox.add(clearButton);
		clearButton.addActionListener(new ClearButton(handler));
		Component horizontalStrut0 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut0);
		
		JButton printButton = new JButton("Print");
		horizontalBox.add(printButton);
		printButton.addActionListener(new PrintButton(handler));

		Component horizontalStrut1 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut1);
		
		JButton houseButton = new JButton("Add House Photo");
		horizontalBox.add(houseButton);
		houseButton.addActionListener(new HouseButton(handler));

		Component horizontalStrut2 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut2);
		
		JButton templateButton = new JButton("Change Template");
		horizontalBox.add(templateButton);
		templateButton.addActionListener(new TemplateButton(handler));

		JPanel spacer = new JPanel();
		horizontalBox.add(spacer);
		
		
		
		JButton newAgentButton = new JButton("Add New Agent To Database");
		horizontalBox.add(newAgentButton);
		newAgentButton.addActionListener(new AgentButton(handler));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		frame.add(main);
		frame.setVisible(true);
		//System.out.println(menuPanel.getHeight());
		frame.setPreferredSize(new Dimension(width, height+menuPanel.getHeight()));
		frame.setMaximumSize(new Dimension(width, height+menuPanel.getHeight()));
		frame.setMinimumSize(new Dimension(width, height+menuPanel.getHeight()));
		frame.setLocationRelativeTo(null);
		main.start();
		
	}

}
