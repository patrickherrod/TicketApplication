package tickets;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class StudentTest {
	
	@Test public void constructorTest() {
		Student s = new Student("Ikaika");
		
		assertTrue(s != null);
	}
	
	@Test public void getUserNameTest() {
		Student s = new Student("Ikaika");
		
		assertEquals("Ikaika",s.getUserName());
	}
	
	@Test public void associateTicketTest() {
		Student s = new Student("Ikaika");
		TicketCategory tc = new TicketCategory("quiz"); 
		Ticket t = new Ticket(tc,4.0,299);
		s.associateTicket(t);

		assertTrue(s.contains(t));
		//This tests the associateTicket() method as well as the contains() method.
	}

	@Test public void getTicketsTest() {
		Student s = new Student("Ikaika");
		TicketCategory tc = new TicketCategory("quiz"); 
		
		Ticket t = new Ticket(tc,3.0,234);
		Ticket z = new Ticket(tc,3.0,987);
		Ticket i = new Ticket(tc,3.0,134);
		s.associateTicket(t);
		s.associateTicket(z);
		s.associateTicket(i);
		
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		tickets.add(t);
		tickets.add(z);
		tickets.add(i);
		
		
		assertEquals(tickets,s.getTickets());
	}
	
	@Test public void getTicketsTest2() {
		Student s = new Student("Ikaika");
		TicketCategory tc = new TicketCategory("quiz");
		TicketCategory tkc = new TicketCategory("classwork");
		
		Ticket t = new Ticket(tc,3.0,234);
		Ticket z = new Ticket(tc,3.0,987);
		Ticket i = new Ticket(tc,3.0,134);
		Ticket y = new Ticket(tkc,4.0,888);
		s.associateTicket(t);
		s.associateTicket(z);
		s.associateTicket(i);
		s.associateTicket(y);
		
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		tickets.add(t);
		tickets.add(z);
		tickets.add(i);
		assertEquals(tickets,s.getTickets(tc));
	}
	
	@Test public void getNumTicks() {
		Student s = new Student("Ikaika");
		TicketCategory tc = new TicketCategory("quiz"); 
		
		Ticket t = new Ticket(tc,3.0,234);
		Ticket z = new Ticket(tc,3.0,987);
		Ticket i = new Ticket(tc,3.0,134);
		s.associateTicket(t);
		s.associateTicket(z);
		s.associateTicket(i);
		
		assertEquals(3,s.getNumTicks());
	}
	
}
