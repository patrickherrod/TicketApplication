package gui.print;

import java.util.ArrayList;

import javax.swing.JFrame;

import tickets.Ticket;
import tickets.TicketCategory;

/*
 * 	Prints the tickets with the specifications the user has inputed
 */

public class PrintTickets {
	public static void main(String[] args) 
	{
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		TicketCategory tc = new TicketCategory("participation");
		for(int i=0; i < 30; i++) {
			tickets.add(new Ticket(tc, 2.0,(int)(Math.random()*10000)));
		}
		JFrame frame = new TicketSheet(tickets);
		frame.setVisible(true);
	}
}