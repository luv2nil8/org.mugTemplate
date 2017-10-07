package org.mugTemplate;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class Print
    implements Printable {

	BufferedImage BI;
	
	public Print(BufferedImage g){
		this.BI= g;
	}
	
  public int print(Graphics g, PageFormat pf, int page)
      throws PrinterException {
	  

    // We have only one page, and 'page'
    // is zero-based
    if (page > 0) {
         return NO_SUCH_PAGE;
    }

    // User (0,0) is typically outside the
    // imageable area, so we must translate
    // by the X and Y values in the PageFormat
    // to avoid clipping.
    Graphics2D g2d = (Graphics2D)g;
    g2d.translate(pf.getImageableX(), pf.getImageableY());

    // Now we perform our rendering
    g.drawImage(BI, 0, 0, null);

    // tell the caller that this page is part
    // of the printed document
    return PAGE_EXISTS;
  }
}
