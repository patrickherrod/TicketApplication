package gui.print;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.linear.twoOfFive.Int2of5Barcode;
import net.sourceforge.barbecue.output.OutputException;
import tickets.Ticket;

/*
 * 	This class creates a ticket label for each ticket
 */

@SuppressWarnings("serial")
public class TicketLabel extends JLabel {

	public static final int WIDTH = 210;
	public static final int HEIGHT = 120;
	
	/*
	 * 	Creates a ticket label for the ticket
	 * 	@param t - the ticket for which the label was created for
	 */
	
	public TicketLabel(Ticket t) {
	    BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	    Graphics g = image.getGraphics();
		Color c = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
		g.setColor(c);
	    g.fillRect(0,0,WIDTH, HEIGHT);
		this.setIcon(new ImageIcon(image));
	    g.setColor(Color.WHITE);
	    g.fillRect(0,0,WIDTH, HEIGHT);
	    g.setColor(Color.BLACK);
	    String sid = "" + t.getID();
	    while(sid.length() < 10) sid = "0" + sid;
	    Barcode b = null;
		try {
			b = new Int2of5Barcode(sid);
			b.draw((Graphics2D) g, 5 ,20);
		} catch (BarcodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OutputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    g.drawString(t.getCategory().getName(), 5, 12);
		g.setColor(Color.black);
	    g.drawRect(0,0,WIDTH, HEIGHT);

	}
}