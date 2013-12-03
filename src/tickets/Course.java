package tickets;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import csv.CSVWriter;

/*
 * 	This class handles ticket organization and associations between tickets
 *  and students for a particular course. It also handles loading and saving 
 *  of ticket data. Ticket data saved in a csv file stores a complete 
 *  ticket record in each row. Each row contains the following fields:

    The ticket id
    The ticket value
    The ticket category name
    The username of the associated student (or 'null' if there isn't an 
    associated student)

 */
public class Course {
	public ArrayList<Student> studList = new ArrayList<Student>();
	public ArrayList<TicketCategory> tickCats = new ArrayList<TicketCategory>();
	private File f;
	public String courseName;
	
	/*
	 *	This constructor creates a Course object and loads all of the ticket data stored in 
	 *	the specified csv file.
	 *	@param csvFile - the file to load and write data from
	 */
	public Course(String name) {
		courseName = name;
	}
	
	/*
	 * 	Returns the course name of the course it is called on.
	 * 	@returns courseName - the variable Sting name for the name of the course.
	 */
	
	public String getCourseName() {
		return courseName;
	}

	/*
	 * 	Returns the student with the specified username, or null 
	 * 	if the student doesn't exist.
	 * 	@param username - name of the desired student
	 * 	@return s - desired student
	 * 	@return null - student does not exist
	 */
	public Student getStudent(String username) {
		for (Student s : studList)
			if (username.equals(s.getUserName()))	//if username of ticket matches that of any student
				return s;
		return null;
	}
	
	/*
	 * 	Returns a list of all the students in this course.
	 * 	@return studList - the arraylist of all of the students enrolled
	 * 						in the course
	 */
	public ArrayList<Student> getStudents() {
		return studList;
	}
	
	/*
	 * 	 Creates a new category with the specified name, if it does not
	 * 	 already exist.
	 *	 @param categoryName - name of the new category
	 */
	public void addCategory(String categoryName) {
		TicketCategory new1 = new TicketCategory(categoryName);
		tickCats.add(new1);
	}
	
	/*
	 * 	Returns the category with the specified name, or null if it does
	 *  not exist.
	 *  @param categoryName - name of the desired category
	 *  @return tc - the found category
	 *  @return null - category doesn't exist
	 */
	public TicketCategory getCategory(String categoryName) {
		for (TicketCategory tc : tickCats)
			if (categoryName.equals(tc.getName()))
				return tc;
		return null;
	}
	
	/*
	 * 	Creates a new Ticket with an id number that is not already used.
	 * 	@param category - category for the new ticket
	 * 	@param value - point value for the new ticket
	 * 	@return new1 - the newly created ticket
	 */
	public Ticket createNewTicket(TicketCategory category, double pointValue) {
		Random r = new Random();
		int x = 0;
		boolean flag = true;		//flag to signal when the random # is a unique #
		do {
			x = r.nextInt(10000);;
			for (Ticket t : category.getTickets())	//to get each ticket in category
				if (x == t.getID())
					flag = false;
		}
		while(!flag);
		Ticket new1 = new Ticket(category,pointValue,x);
		category.add(new1);			//adds new ticket to specified category arraylist
		return new1;
	}
	
	/*
	 * Saves all of the ticket data to the csv file.
	 */
	public void writeToFile() {
		try {
			CSVWriter cWR = new CSVWriter(f);
			ArrayList<String> ticketData = new ArrayList<String>();			//arraylist for the lines of the ticket
			ArrayList<Ticket> ticksWritten = new ArrayList<Ticket>();		//arraylist keeping track of all the written tickets
			for (Student s : studList)
				for (Ticket t : s.getTickets()) {		//write the tickets of each student
					ticksWritten.add(t);
					ticketData.add(t.getID() + "");
					ticketData.add(t.getValue() + "");
					ticketData.add(t.getCategory().getName());
					ticketData.add(s.getUserName());
					cWR.writeLine(ticketData);			//write ticket data
					ticketData.clear();					//clear arraylist of ticket data
				}
			for (TicketCategory tc : tickCats)
				for (Ticket t : tc.getTickets())
					if(!ticksWritten.contains(t)) {		//write the non-associated tickets that have not already been written
						ticketData.add(t.getID() + "");
						ticketData.add(t.getValue() + "");
						ticketData.add(t.getCategory().getName());
						cWR.writeLine(ticketData);		//write ticket data
						ticketData.clear();				//clear arraylist of ticket data
				}
		cWR.close();
		}
		
		catch (FileNotFoundException e) {
			System.out.println("Error: The file was not found.");
		}
		
	}
	
	/*
	 * 	Returns a list of all the ticket categories for this class.
	 * 	@return tickCats - arraylist containing all the ticket categories
	 */
	public ArrayList<TicketCategory> getCategories() {
		return tickCats;
	}
	
	/*
	 * 	Adds a student to this course.
	 * 	@param student - the student to be added
	 */
	public void addStudent(Student student) {
		studList.add(student);
	}
	
	/*
	 * 	Removes a student from this course
	 * 	@param username - the name of the student to be removed
	 */
	
	public void removeStudent(String username) {
		int indexToBeDeleted = -1;
		for(int i = 0; i < studList.size(); i++) {
			if((studList.get(i)).getUserName() == username)
				indexToBeDeleted = i;
		}
		studList.remove(indexToBeDeleted);
	}
}
