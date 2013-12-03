package hw5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import tickets.Course;

public class CourseManager {
	public ArrayList<Course> courses = new ArrayList<Course>();
	public File file;
	public Course selectedCourse;

	/*
	 * 	Adds a course to the course manager.
	 * 	@param name - the name to give to the new course.
	 */
	public void addCourse(String name) {
		file = new File(name+"-config.csv");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				//this occurs if you're in a directory you don't have access to
			}
		}
		FileManager fm = new FileManager(file);
		Course c = new Course(name);
		
		try {										
			fm.loadFile(c);							
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		courses.add(c);
	}
	
	/*
	 * 	Method that returns a course from courseManagers list.
	 * 	@param name - the name of the desired course.
	 */
	public Course getCourse(String name) {
		for (Course s: courses) {
			if (s.getCourseName().equals(name))
				return s;
		}
		return null;
	}
	
	/*
	 * 	Removes a course from the course manager.
	 * 	@param name - the name of the course to remove
	 */
	public void removeCourse(String name) {
		for (int i = 0; i < courses.size(); i++) {				//cannot use a foreach loop because you cannot remove and item when it is locked into a variable
			if (courses.get(i).getCourseName().equals(name)) {
				File fileToDelete = new File(name+"-config.csv");
				if(fileToDelete.exists())
					fileToDelete.delete();
				courses.remove(i);
			}
		}
		FileManager fm = new FileManager(file);
		for(Course s : courses)
			fm.saveFile(s);
	}
	
	/*
	 * 	Changes the course of name to have the course name of toName.
	 * 	@param name - the name of the Course wanted to change.
	 * 	@param toName - name that the Course name wil be changed to
	 */
	public void changeCourseName(String name, String toName) {
		for (Course s: courses) {
			if (s.getCourseName().equals(name))
				s.courseName = toName;
		}
	}
	
	/*
	 * 	Returns the names of all of the courses in the system
	 * 	@return names -  the names of the courses in the system
	 */
	
	public ArrayList<String> getCourseNames() { 
		ArrayList<String> names = new ArrayList<String>();
		for(Course c : courses) {
			names.add(c.getCourseName());
		}
		return names;
	}
}
