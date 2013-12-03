package hw5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import tickets.Course;
import tickets.Student;


public class FileManagerTest {

	File tmpTicketFile;
	FileManager fm;
	
	@Before public void setUp(){
		Random r = new Random();
		tmpTicketFile = new File("/tmp/ticket-"+
				r.nextInt(10000) + ".csv");
		PrintWriter ticketFileOut;
		try {
			ticketFileOut = new PrintWriter(tmpTicketFile);
		    ticketFileOut.println("814,2.0,somecategory,pherrod13");
		    ticketFileOut.println("214,5.0,quiz,pherrod13");
		    ticketFileOut.flush();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fm = new FileManager(tmpTicketFile);
	}
	
	@Test public void constructorTest() {

	}
	
	@Test public void loadFileTest() {
		try {
			Course c = new Course("Math");
			Student s = new Student("Ikaika");
			c.addStudent(s);
			fm.loadFile(c);
			assertTrue(fm != null);
			assertEquals("quiz", c.getCategory("quiz").getName());
			assertEquals(2, c.getCategories().size());
			

			Random r = new Random();
			File tmpTicketFile2 = new File("/tmp/ticket2-"+
					r.nextInt(10000) + ".csv");
			FileManager fm2 = new FileManager(tmpTicketFile2);
			fm2.saveFile(c);
			
			c = new Course("Math");
			fm2.loadFile(c);
			
			///copy in previous tests
			assertTrue(fm2 != null);
			assertEquals(2, c.getCategories().size());
			assertEquals("quiz", c.getCategory("quiz").getName());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
