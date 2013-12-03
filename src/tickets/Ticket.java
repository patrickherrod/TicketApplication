package tickets;

/*
 * 	This class tracks information for a ticket in the ticket grading system. 
 */
public class Ticket {
	int iD = 0;
	double pV = 0.0;
	TicketCategory tickCat = null;
	
	/*
	 * 	This method creates a new ticket with the specified id and value in points.
	 * 	@param id - id of the new ticket
	 * 	@param pointvalue - point value of the new ticket
	 * 	@param category - the category of the new ticket
	 */
	public Ticket(TicketCategory category, double pointvalue, int id) {
		iD = id;
		pV = pointvalue;					//reassignment of the generic variables
		tickCat = category;
	}
	
	/*
	 * 	Returns the unique id of this ticket.
	 * 	@return id - id number
	 */
	public int getID() {
		return iD;
	}
	
	/*
	 *	Returns the value of this ticket in points.
	 *	@return pV - point value
	 */
	public double getValue() {
		return pV;		
	}
	
	/*
	 * 	Returns the category associated with this ticket.
	 * 	@return tickCat - ticket category
	 */
	public TicketCategory getCategory() {
		return tickCat;
	}

	/*
	 * 	Returns a String representation of this ticket with the following 
	 * 	format: "Ticket #TICKET_NUM: [CATEGORY_NAME/POINT_VALUE]" where 
	 * 	TICKET_NUM, CATEGORY_NAME, and POINT_VALUE are replaced with the 
	 * 	appropriate values.
	 * 	@return - the string of all the information of the ticket
	 */
	public String toString() {
		return "Ticket#:   " + getID() + "     " + "TicketCategory:   " + getCategory().getName()		//concatenation of the tickets fields
			+ "     " + "TicketValue:   " + getValue();
	}
	
}
