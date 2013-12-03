package tickets;

import static org.junit.Assert.*;

import org.junit.Test;


public class TicketTest {
	
	@Test public void constructorTest() {
		TicketCategory tc = new TicketCategory("participation-week-1");
		Ticket t = new Ticket(tc,2.0,812);
		
		assertTrue(t != null);
	}
	
	@Test public void getIdTest() {
		TicketCategory tc = new TicketCategory("participation-week-1");
		Ticket t = new Ticket(tc,2.0,812);
		
		assertEquals(812,t.getID());
	}

	@Test public void getValueTest() {
		TicketCategory tc = new TicketCategory("participation-week-1");
		Ticket t = new Ticket(tc,2.0,812);
		
		assertEquals(2.0,t.getValue(),0);
	}
	
	@Test public void getCategoryTest() {
		TicketCategory tc = new TicketCategory("participation-week-1");
		Ticket t = new Ticket(tc,2.0,812);
		
		assertEquals(tc,t.getCategory());
	}
}
