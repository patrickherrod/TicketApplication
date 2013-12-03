package tickets.scoring;

import tickets.Student;
import tickets.Ticket;
import tickets.TicketCategory;

/*
 * 	Class for a TicketScorer that provides the cumulative or total
 *  score of all of the tickets in a category for a student.
 */
public class Cumulative extends Object implements TicketScorer {
	
	final double MAX;
	boolean MaxFlag = false;
	
	/*
	 * 	Constructor for a Cumulative TicketScorer object, that sets
	 * 	max at 0.0 to get it out of the way.
	 */
	public Cumulative() {
		MAX = 0.0;
	}
	
	/*
	 * 	Constructor for a Cumulative TicketScorer that sets
	 * 	a desired maximum total score.
	 * 	@param max - the desired maximum value
	 */
	public Cumulative(double max){
		MAX = max;
		MaxFlag = true;
	}
	
	/*
	 * 	Returns the cumulative score for the indicated ticket category, only
	 *	considering tickets associated with the indicated student.
	 *	@param tc - the ticketCatgory of tickets to score
	 *	@param s - the student whose tickets are to be scored
	 *	@return score - the cumulative score of all the tickets considered
	 */
	public double scoreTickets(TicketCategory tc, Student s) {
		double score = 0;
		for (Ticket t : s.getTickets()) {			// loop to grab each ticket the student has
			if ((t.getCategory().getName()).equals(tc.getName())){			// if the grabbed ticket matches the expressed TicketCategory
				score += t.getValue();				// add ticket value to running total
			}
		}
		if (MaxFlag && score > MAX)
			return MAX;								// return whichever is less MAX or score
		return score;
	}

	/*
	 *	Method returns the maximum value this category can attain
	 *	for a student, or -1 if no maximum is set.
	 *	@return MAX - the set max value
	 *	@return -1 - the value if no max is set  
	 */
	public double getMax() {
		if(MaxFlag)
			return MAX;								// return MAX if it was set
		return -1;
	}
}
