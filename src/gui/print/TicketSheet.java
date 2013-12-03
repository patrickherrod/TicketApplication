package gui.print;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import tickets.Ticket;


@SuppressWarnings("serial")
public class TicketSheet extends JFrame {

	public static final int WIDTH = 4;
	public static final int HEIGHT = 6;
	
	/*
	 * 	Creates a ticket sheet with the tickets created by the user
	 * 	@param tickets - An arraylist of all the tickets to be created
	 */
	
	public TicketSheet(ArrayList<Ticket> tickets) {
		final JPanel content = new JPanel();
		final int width = WIDTH*(TicketLabel.WIDTH+10);
		final int height = HEIGHT*(TicketLabel.HEIGHT+10);
		for(Ticket t : tickets) 
		{
		    content.add(new TicketLabel(t));
		}
		setContentPane(content);
		JMenuBar bar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem save = new JMenuItem("Save as png image...");
		fileMenu.add(save);
		bar.add(fileMenu);
		final TicketSheet myself = this;
		save.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
			    BufferedImage image = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
			    content.paint(image.getGraphics());
			    try {

			    	final JFileChooser fc = new JFileChooser();
			    	int returnVal = fc.showOpenDialog(myself);
			    	if(returnVal == JFileChooser.APPROVE_OPTION) {
					    ImageIO.write(image, "png", fc.getSelectedFile());
			    	}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		this.setJMenuBar(bar);
		this.setSize(width,height);
	
	}
	
}