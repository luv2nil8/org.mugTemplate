package org.mugTemplate;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class PrintButton implements ActionListener {
	Handler handler;
	CanvasImage background;
	CanvasImage agent;
	CanvasImage house;
	Background BG;
	Agent A;
	House H;
	
	public PrintButton(Handler handler){
		this.handler = handler;
		background = handler.getObject(ID.Background);
		agent = handler.getObject(ID.Agent);
		house = handler.getObject(ID.House);
		BG = new Background(0,0,2369,1050, ID.PrintBG);
		A = new Agent(100,80,600,840, ID.PrintA);
		H = new House(house.x*2,house.y*2,840,630, ID.PrintH);

		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		BG.fitImage(background.getImage());
		A.fitImage(agent.getImage());
		H.fitImage(house.getImage());
		BufferedImage bimage = new BufferedImage(2369, 1050, BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        
        bGr.drawImage(BG.scaled, 0, 0, null);
        bGr.drawImage(A.scaled, A.x, A.y, null);
        bGr.drawImage(H.scaled, H.x, H.y, null);
        print(bimage);
        bGr.dispose();

        // Return the buffered image
	}
	private void print(BufferedImage g){
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(new Print(g));

		
		boolean doPrint = job.printDialog();
		
		if (doPrint) {
		    try {
		        job.print();
		    } catch (PrinterException e) {
		        // The job did not successfully
		        // complete
		    }
		}
	}

}
