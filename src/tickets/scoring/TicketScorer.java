package tickets.scoring;

import tickets.Student;
import tickets.TicketCategory;

/*
 *	Interface the requires classes to provide a method for scoring all
 *	of the tickets in a particular category for a particular student.
 */
public interface TicketScorer {
	
	/*
	 *	Returns the overall score for the indicated category, only
	 *	considering tickets associated with the indicated student.
	 */
	double scoreTickets(TicketCategory tc, Student s);			//required method

}
