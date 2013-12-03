package hw5;

import static org.junit.Assert.*;

import org.junit.Test;


public class CourseManagerTest {
	
	@Test public void constructorTest() {
		CourseManager cm = new CourseManager();
		
		assertTrue(cm != null);
	}
	
	@Test public void addCourseTest() {
		CourseManager cm = new CourseManager();
		cm.addCourse("Chem");
		cm.addCourse("Bio");
		cm.addCourse("Physics");
		
		assertTrue(cm.getCourse("Chem") != null);
		assertTrue(cm.getCourse("Bio") != null);
		assertTrue(cm.getCourse("Physics") != null);
		//Also a getCourse() test.
	}
	
	@Test public void changeCourseNameTest() {
		CourseManager cm = new CourseManager();
		cm.addCourse("Chem");
		cm.addCourse("Bio");
		cm.addCourse("Physics");
		
		cm.changeCourseName("Chem", "Chemistry");
		cm.changeCourseName("Bio","Biology");
		
		assertTrue(cm.getCourse("Chemistry") != null);
		assertTrue(cm.getCourse("Biology") != null);
	}
	
	@Test public void removeCourseTest() {
		CourseManager cm = new CourseManager();
		cm.addCourse("Chem");
		cm.addCourse("Bio");
		cm.addCourse("Physics");
		
		cm.removeCourse("Bio");
		
		assertTrue(cm.getCourse("Bio") == null);
		
		cm.removeCourse("Physics");
		assertTrue(cm.getCourse("Physics") == null);
		
		cm.removeCourse("Chem");
		assertEquals(0, cm.courses.size());
	}

}
