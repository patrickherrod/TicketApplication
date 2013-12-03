package tickets.scoring;

import tickets.Student;
import tickets.Ticket;
import tickets.TicketCategory;

/*
 * 	Class for a TicketScorer that provides an average score for all
 * 	of the tickets in a category for a student.
 */
public class Average extends Object implements TicketScorer {
	
	/*
	 *	Constructor for an Average TicketScorer object.
	 */
	public Average() {
		
	}

	/*
	 * 	Returns the average score for the indicated ticket category, only
	 *	considering tickets associated with the indicated student.
	 *	@param tc - the ticketCatgory of tickets to score
	 *	@param s - the student whose tickets are to be scored
	 *	@return score - the average score of all the tickets considered
	 */
	public double scoreTickets(TicketCategory tc, Student s) {
		double score = 0;
		int ticketCounter = 0;
		for (Ticket t : s.getTickets()) {				// loop to grab each ticket the student has
			if ((t.getCategory().getName()).equals(tc.getName())){				// if the grabbed ticket matches the expressed TicketCategory
				score += t.getValue();					// add ticket value to running total
				ticketCounter++;
			}
		}
		return score/ticketCounter;						// total divided by # of tickets or average		
	}
}
