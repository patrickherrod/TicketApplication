package tickets;

import java.util.ArrayList;

import tickets.scoring.Maximum;
import tickets.scoring.TicketScorer;

/*
 * 	 This class represents a category for organizing tickets in the ticket grading system. 
 */
public class TicketCategory {
	String catName = "";
	public ArrayList<Ticket> ticks = new ArrayList<Ticket>();
	int numberOfTickets = 0;
	TicketScorer scorer;
	
	/*
	 *	Constructor creates a ticket category with the specified name, and
	 *	sets a Maximum scorer as default.
	 *	@param name - name of the new category
	 */
	public TicketCategory(String name) {
		catName = name;
		scorer = new Maximum();
		
	}
	
	/*
	 *	Constructor creates a ticket category with the specified name, and
	 *	and a desired scoring method.
	 *	@param name - name of the new category
	 *	@param scorer - desired scoring method
	 */
	public TicketCategory(String name, TicketScorer scorer) {
		catName = name;
		this.scorer = scorer;
	}
	
	/*
	 * 	Returns all the tickets created for this category.
	 *	@return ticks - the arraylist containing all the tickets in the category 
	 */
	public ArrayList<Ticket> getTickets() {
	return ticks;
	}
	
	/*
	 *	 Method adds a ticket to this category (if it is not already in this category).
	 */
	public void add(Ticket t) {
		if (!ticks.contains(t)) {
			ticks.add(t);
			numberOfTickets++;
		}
	}
	
	/*
	 *	This method determines whether or not the ticket is in this category.
	 *	return - a boolean
	 */
	public boolean contains(Ticket t) {
		return ticks.contains(t);
	}
	
	/*
	 *	Returns the name of this category.
	 *	@return catName - the name of the category
	 */
	public String getName() {
		return catName;
	}
	
	/*
	 * 	Returns the number of tickets in the category.
	 * 	@return numberOfTickets - counter of tickets in the category
	 */
	public int getNumTickets() {
		return numberOfTickets;	
	}

	/*
	 * 	Returns the score of the specified student based on type of
	 * 	scorer is used.
	 * 	@return scorer.scoreTickets(this, student) - the syntax for
	 * 	the calculated score
	 */
	public double getScore(Student student) {
		return scorer.scoreTickets(this, student);
	}
	/*
	 * 	Returns the TicketScorer or scoring method for this category.
	 * 	@return scorer - the TciketScorer object
	 */
	public TicketScorer getScoringMethod() {
		return scorer;
	}
	
	/*
	 * 	Changes the scoring method to that of the desired
	 * 	method through the parameter.
	 * 	@param scorer - the desired scoring method
	 */
	public void setScoringMethod(TicketScorer scorer) {
		this.scorer = scorer;
	}
}
