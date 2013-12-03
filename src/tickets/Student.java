package tickets;

import java.util.ArrayList;
import tickets.TicketCategory;

/*
 * 	This class tracks the tickets associated with a student.
 */
public class Student {
	String studName = "";
	public ArrayList<Ticket> studTicks = new ArrayList<Ticket>();
	int numberOfTickets = 0;
	
	/*
	 * 	Constructor creates a student record with the specified username.
	 * 	@param userName - the name of the new student
	 */
	public Student(String userName) {
		studName = userName;
	}
	
	/*
	 *	Returns the username of this student.
	 *	@return studName - the name of the student
	 */
	public String getUserName() {
		return studName;
	}
	
	/*
	 * 	Associates the ticket with this student.
	 * 	@param t - ticket to be associated
	 */
	public void associateTicket(Ticket t) {
		studTicks.add(t);
		numberOfTickets++;
	}
	
	/*
	 * 	Returns all tickets associated with this student.
	 * 	@return studTicks - arraylist of all the student's tickets
	 */
	public ArrayList<Ticket> getTickets() {
		return studTicks;
	}
	
	/*
	 * 	Returns all tickets from the indicated category that are 
	 * 	associated with this student.
	 * 	@param category - category of tickets wanted
	 * 	@return catTicks - arraylist of student's tickets in specified category 
	 */
	public ArrayList<Ticket> getTickets(TicketCategory category) {
		ArrayList<Ticket> catTicks = new ArrayList<Ticket>();		//arraylist to hold the found tickets 
		for (Ticket x : studTicks) 
			if (x.getCategory() == category){
				catTicks.add(x);
			}
		return catTicks;
	}
	
	/*
	 * 	Returns whether or not the ticket is in the student's list of tickets.
	 * 	@param t - ticket wanted
	 * 	@return - boolean
	 */
	public boolean contains(Ticket t) {
		return studTicks.contains(t);
	}

	/*
	 * 	Returns the number of tickets that a student has acquired.
	 * 	@return numberOfTickets - counter for the tickets in studTicks array
	 */
	public int getNumTicks() {
		return numberOfTickets;
	}
	
	/*
	 * 	Returns the username of the student
	 * 	@return getUserName - the user name of the student
	 */
	
	public String toString() {
		return getUserName();
	}
	
	
}
