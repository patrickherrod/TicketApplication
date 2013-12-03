package tickets.scoring;

import tickets.Student;
import tickets.Ticket;
import tickets.TicketCategory;

/*
 * 	Class for a TicketScorer that provides the maximum or highest
 *  score of all of the tickets in a category for a student.
 */
public class Maximum extends Object implements TicketScorer {
	
	/*
	 *	Constructor for a Maximum TicketScorer object. 
	 */
	public Maximum() {
		
	}
	/*
	 * 	Returns the maximum score for the indicated ticket category, only
	 *	considering tickets associated with the indicated student.
	 *	@param tc - the ticketCatgory of tickets to score
	 *	@param s - the student whose tickets are to be scored
	 *	@return score - the maximum score of all the tickets considered
	 */
	public double scoreTickets(TicketCategory tc, Student s) {
		double score = 0.0;
		for (Ticket t : s.getTickets()) {					// loop to grab each ticket the student ha
			if ((t.getCategory().getName()).equals(tc.getName()) && t.getValue() > score)	// if the grabbed ticket matches the expressed TicketCategory and the selected ticket has a greater value then the stored value
				score = t.getValue();						// overwrite the previous highest value
		}
		return score;										// return the highest value found
	}
}
