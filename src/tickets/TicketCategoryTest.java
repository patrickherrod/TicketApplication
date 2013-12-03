package tickets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import tickets.scoring.Average;
import tickets.scoring.Cumulative;
import tickets.scoring.Maximum;
import tickets.scoring.TicketScorer;


public class TicketCategoryTest {
	

	
	@Test public void constructorTest() {
		TicketCategory tc = new TicketCategory("participation");
		
		TicketScorer ave = new Average();
		TicketCategory tkc = new TicketCategory("quiz",ave);
		
		assertTrue(tc != null);
		
		assertTrue(tkc != null);
	}
	
	@Test public void addTest() {
		TicketCategory tc = new TicketCategory("participation");
		
		tc.add(new Ticket(tc,3.0,234));
		tc.add(new Ticket(tc,3.0,987));
		tc.add(new Ticket(tc,3.0,134));
		
		assertEquals(3,tc.getNumTickets());
		//This also tests the getNumTickets method.
	}
	
	@Test public void getTicketsTest() {
		TicketCategory tc = new TicketCategory("participation");
		Ticket t = new Ticket(tc,3.0,234);
		Ticket z = new Ticket(tc,3.0,987);
		Ticket i = new Ticket(tc,3.0,134);
		tc.add(t);
		tc.add(z);
		tc.add(i);
		
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		tickets.add(t);
		tickets.add(z);
		tickets.add(i);
		
		assertEquals(tickets,tc.getTickets());
	}
	
	@Test public void containsTicketTest() {
		TicketCategory tc = new TicketCategory("participation");
		Ticket t = new Ticket(tc,3.0,234);
		tc.add(t);
		tc.add(new Ticket(tc,3.0,987));
		tc.add(new Ticket(tc,3.0,134));
		
		assertTrue(tc.contains(t));
	}
	
	@Test public void getNameTest() {
		TicketCategory tc = new TicketCategory("classwork");
		
		assertEquals("classwork",tc.getName());
	}

	@Test public void getScoringMethodTest() {
		TicketCategory tc = new TicketCategory("classwork");

		
		assertTrue(tc.getScoringMethod() instanceof Maximum);
	}
	
	@Test public void setScoringMethodTest() {
		TicketCategory tc = new TicketCategory("classwork");
		TicketScorer cum = new Cumulative();
		tc.setScoringMethod(cum);
		
		assertEquals(cum,tc.getScoringMethod());
	}
	

	@Test public void getScoreTest() {
		TicketCategory tc = new TicketCategory("classwork");

		Student s = new Student("Pjevach");
		Ticket t = new Ticket(tc,3.0,234);
		Ticket z = new Ticket(tc,4.0,987);
		Ticket i = new Ticket(tc,5.0,134);
		
		Ticket y = new Ticket(tc,8.3,99);
		
		s.associateTicket(t);
		s.associateTicket(z);
		s.associateTicket(i);
		tc.add(t);
		tc.add(z);
		tc.add(i);
		tc.add(y);

		
		assertEquals(5.0,tc.getScore(s),0);
		
		
		TicketScorer ave = new Average();
		tc.setScoringMethod(ave);
		
		assertEquals(4.0,tc.getScore(s),0);
		
		
		TicketScorer cum = new Cumulative();
		tc.setScoringMethod(cum);
		
		assertEquals(12.0,tc.getScore(s),0);
	}
}
