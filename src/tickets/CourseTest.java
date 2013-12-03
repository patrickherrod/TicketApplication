package tickets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;


public class CourseTest {
	/*File tmpTicketFile1 = new File("/tmp/ticket-0.csv");
	PrintWriter ticketFileOut = new PrintWriter(tmpTicketFile1);
    ticketFileOut.println("814,2.0,somecategory,pherrod13");
    ticketFileOut.flush();
	*/
	
	
	@Test public void testConstructor() {
		Course c = new Course("Math");
		
		assertTrue(c != null);
	}
	
	@Test public void getCourseName() {
		Course c = new Course("Math");
		
		assertEquals("Math",c.getCourseName());
	}
	
	@Test public void addStudentTest() {
		Course c = new Course("Math");
		Student s = new Student("Ikaika");
		c.addStudent(s);
		
		assertEquals(s,c.getStudent("Ikaika"));
		//This tests the addStudent() method as well as the getStudent() method.
	}
	
	@Test public void createNewTicketTest() {
		Course c = new Course("Math");
		c.addCategory("classwork");
		TicketCategory tc = new TicketCategory("quiz");
		Ticket t = c.createNewTicket(tc,4.5);
		
		assertTrue(t != null);
		assertTrue(c.getCategory("classwork") != null);
		assertTrue("Check to see if the ID generated is within the legal range",
				t.getID() <= 10000);
		assertTrue(t.getID() > 0);
		assertEquals(4.5,t.getValue(),0);
	}

	@Test public void addCategoryTest() {
		Course c = new Course("Math");
		c.addCategory((new TicketCategory("classwork")).getName());
		
		assertTrue(c.getCategory("classwork") != null);
		//Tests the getCategory() method as well as the addCategory() method.
	}
	
	@Test public void getStudentsTest() {
		Course c = new Course("Math");
		Student s = new Student("Ikaika");
		Student p = new Student("Pat");
		Student t = new Student("Steven");
		Student g = new Student("Graham");
		
		c.addStudent(s);
		c.addStudent(p);
		c.addStudent(t);
		c.addStudent(g);
		
		ArrayList<Student> students = new ArrayList<Student>();
		students.add(s);
		students.add(p);
		students.add(t);
		students.add(g);
		
		assertEquals(students,c.getStudents());
	}
	
	@Test public void getCategoriesTest() {
		Course c = new Course("Math");
		/*TicketCategory tc = new TicketCategory("classwork");
		TicketCategory tkc = new TicketCategory("quiz");
		TicketCategory t = new TicketCategory("homework");
		TicketCategory k = new TicketCategory("participation");
		TicketCategory kc = new TicketCategory("test");*/
		c.addCategory("classwork");
		c.addCategory("quiz");
		c.addCategory("homework");
		c.addCategory("participation");
		c.addCategory("test");
		
		assertTrue(c.getCategories() instanceof ArrayList<?> && (c.getCategories()).size() == 5);
	}
	
	@Test public void removestudentTest() {
		Course c = new Course("Math");
		Student s = new Student("Ikaika");
		Student p = new Student("Pat");
		Student t = new Student("Steven");
		Student g = new Student("Graham");
		c.addStudent(s);
		c.addStudent(p);
		c.addStudent(t);
		c.addStudent(g);
		
		assertEquals(4,c.studList.size());
		
		c.removeStudent("Pat");
		
		assertEquals(3,c.studList.size());
		
		c.removeStudent("Ikaika");
		
		assertTrue(!c.studList.contains(s));
		
	}
}
