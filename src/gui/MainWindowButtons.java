package gui;

import gui.print.TicketSheet;
import hw5.CourseManager;
import hw5.FileManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import tickets.Course;
import tickets.Student;
import tickets.Ticket;
import tickets.TicketCategory;

/*
 * 	This class handles all of the click-able buttons in the GUI and their actions.
 */
public class MainWindowButtons implements ActionListener {

	private CourseManager cm;

	/*
	 * 	The basic constructor that sets the course manager field as
	 * 	the course manager that is passed in through the parameter.
	 * 	@param cm - the course manager handling the courses
	 */
	public MainWindowButtons(CourseManager cm) {
		this.cm = cm;
	}
	
	/*
	 * Interface listener that responds to any button that has a listner being clicked.
	 * @param ActionEvent - any clicking of any button that is picked by an actionlistener
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		/*
		 * Situation where the "Add Course" is pressed and a course is created
		 * and added to the course manager.
		 */
		if(e.getActionCommand().equals("Add Course")) {
			String courseName = null;
			while(courseName == null) {
				courseName = JOptionPane.showInputDialog("Enter a name for the course");
				if(courseName == null) {  //user canceled 
					return;
				}
				if(cm.getCourse(courseName) != null) { //user entered an existing course name
					JOptionPane.showMessageDialog(null, "Error: The course '"+courseName + 
							"' already exists.");
					return;
				}
			}
			cm.addCourse(courseName);
			gui.GUI.getCSComboBox().addItem(courseName); //adds course to our courseSelection combo box
		}
		
		/*
		 * Situation where the "Remove Course" button is pressed and the selected course
		 * in the combo box is deleted and the course is removed from the course manager.
		 */
		else if(e.getActionCommand().equals("Remove Course")) {
			int x = JOptionPane.showConfirmDialog(null, "Warning!  Deleting this course will delete all of its" +
														" tickets.\nContinue?");
			if (x == 1 || x == 2) {			//User hit "no" or "cancel"
				return;
			}
			Course c = cm.getCourse((String)gui.GUI.courseSelection.getSelectedItem());
			c.tickCats.clear();
			cm.removeCourse(c.getCourseName());
			gui.GUI.getCSComboBox().removeItem(c.getCourseName());  //removes course from our courseSelection combo box
		}
		
		/*
		 * Situation where the user presses the "Add Student" button.  A student is created
		 * with the name being the input from the user.  The list of students is updated.
		 */
		else if(e.getActionCommand().equals("Add Student")) {
			String studentName = null;
			cm.selectedCourse = cm.getCourse((String)gui.GUI.getCSComboBox().getSelectedItem());  //updates selected course
			while(studentName == null) {
				studentName = JOptionPane.showInputDialog("Enter a name for the Student");
				if(studentName == null)			//user hit cancel
					return;
				if(cm.selectedCourse.getStudent(studentName) != null) {			//user has not selected a student
					JOptionPane.showMessageDialog(null, "Error: the student '"
													+ studentName + "' already exists.");
					return;
				}
			}
			if(studentName.length() == 0) {  //user hit enter of "ok" without typing anything
				JOptionPane.showMessageDialog(null, "You must enter a name to add a student.");
				return;
			}
			Student newStudent = new Student(studentName);
			cm.selectedCourse.addStudent(newStudent);
			GUI.updateStudentList(cm);
		}
		
		/*
		 * Situation where a user clicks the "Remove Student" button.  A student must be
		 * selected from the student list.  The user is warned before deleting the student.
		 * The students tickets are matched with the copy of the tickets that is being held
		 * in the ticket categories' list of the tickets in the course.  The matches are
		 * deleted from the ticket category and then deleted from the the student's list
		 * of tickets.  The student is then removed from the class, and the student list
		 * is update.
		 */
		else if(e.getActionCommand().equals("Remove Student")) {
			cm.selectedCourse = cm.getCourse((String)gui.GUI.getCSComboBox().getSelectedItem());	//updates selected course
			Student s = (Student)gui.GUI.studentList.getSelectedValue();		//gets the selected student
			if (s == null) {		//user hit did not select a student
				JOptionPane.showMessageDialog(null, "Error: You select a student " +
													"before hitting this button");
				return;
			}
			int x = JOptionPane.showConfirmDialog(null, "Warning!  Deleting this student will also delete\nall of the" +
													" tickets created for this course. Continue?");
			if (x == 1 || x == 2) {		//user hit "no" or "cancel"
				return;
			}
			ArrayList<Ticket> tixToDelete = new ArrayList<Ticket>();		//list to hold the tickets to delete from student
			tixToDelete = s.studTicks;
			ArrayList<String> indexToDelete = new ArrayList<String>();
			for(TicketCategory tc : cm.selectedCourse.getCategories()) {
				for (Ticket tToDelete : tixToDelete) {
					for (int i = 0; i < tc.ticks.size(); i++) {
						if( tc.ticks.get(i)== tToDelete) {				//if ticket in that ticket category matches that
							indexToDelete.add(Integer.toString(i));		//one of the tickets to delete then add it to list of tickets to delete
						}
					}
				}
				for(int z = 0; z < indexToDelete.size(); z++) {
					tc.ticks.remove(Integer.parseInt(indexToDelete.get(z)));	//remove tickets from ticket category at found index
				}
				indexToDelete.clear();
			}
			s.studTicks.clear();
			cm.selectedCourse.removeStudent(s.getUserName());
			GUI.updateStudentList(cm);
		}
		
		/*
		 * The user selects the "Create Tickets" button.  The user is prompted to
		 * provide three types of information about the ticket before their creation:
		 * what ticketcategory, what point value and how many tickets to create.  Once
		 * created a ticket sheet is made and shown to give the user a visual of the
		 * tickets just created.
		 */
		else if(e.getActionCommand().equals("Create Tickets")) {
			String ticCat;
			boolean ticCatFlag = false;		//flags and ints to keep do while loop going
			int letterFlag = 0;
			double pointValue = 0;
			boolean pointValFlag = false;
			int counter = 0;
			boolean counterFlag = false;
			if((cm.getCourse((String)gui.GUI.courseSelection.getSelectedItem())).getStudents().size() == 0) {  //if there are no students in the selected course
				JOptionPane.showMessageDialog(null, "There must be a student in the class to create tickets.");
				return;
			}
			do {
				ticCat = JOptionPane.showInputDialog("Enter a Ticket Category");
				letterFlag = 0;
				if (ticCat == null) {
					return;
				}
				else if (ticCat.length() == 1) {   //if the ticketcategory is only 1 character 
					if(!(Character.isLetter(ticCat.charAt(0)))) {   //if that one character is not a letter
						JOptionPane.showMessageDialog(null, "The first character of the " +
															"TicketCategory must be a letter");
						letterFlag++;
					}
					ticCatFlag = true;
				}
				else if (ticCat.length() >= 2) {  //if the ticketcategory name is greater than two characters
					for(int i=0; i < 2; i++) {
						if(!(Character.isLetter(ticCat.charAt(i)))) {    //if the first two characters are not letters 
							letterFlag++;
						}
					}
					if(letterFlag > 0) {
						JOptionPane.showMessageDialog(null, "The first two characters of the " +
															"TicketCategory must be letters");
					}
					ticCatFlag = true;
				}
				else if (ticCat.length() == 0) //if nothing was entered
					JOptionPane.showMessageDialog(null, "You must input a ticketCategory."); 
			}
			while(!ticCatFlag || letterFlag > 0);
			
			do {
				String result = JOptionPane.showInputDialog("Enter Point Value");
				if(result != null) {  //user canceled
					try { 
						pointValue = Double.parseDouble(result);
					} catch(NumberFormatException nfe) {
						JOptionPane.showMessageDialog(null, "ERROR: Enter a valid floating point number for the ticket value");
					}
				}
				if (pointValue > 0) 
					pointValFlag = true;
				else if (pointValue < 0) 
					JOptionPane.showMessageDialog(null, "Error: Cannot enter a negative Point Value Entered");
				if(result == null)
					return;
			}
			while (!pointValFlag);
			
			do {
				int errorCounter = 0;
				String result = JOptionPane.showInputDialog("How Many Tickets to Create?");
				if (result != null) {  //user canceled
					try { 
						counter = Integer.parseInt(result);
					} catch(NumberFormatException nfe) {  //if number is not an integer
						JOptionPane.showMessageDialog(null, "ERROR: Enter a valid integer for the number of tickets" +
															" to create.");
						errorCounter++;
					}
					if (counter > 0 && errorCounter == 0)
						counterFlag = true;
					else if (counter < 1 & errorCounter == 0)
						JOptionPane.showMessageDialog(null, "Must Create Atleast One Ticket");
				}
				else
					return;
			}
			while (!counterFlag);
			
			TicketCategory tc = cm.getCourse((String)gui.GUI.courseSelection.getSelectedItem()).getCategory(ticCat);  //get category from the selected course
			if(tc == null) {		//if the ticket category doesn't exist then create one
				cm.getCourse((String)gui.GUI.courseSelection.getSelectedItem()).addCategory(ticCat);
				tc = cm.getCourse((String)gui.GUI.courseSelection.getSelectedItem()).getCategory(ticCat);
			}
			ArrayList <Ticket> ticketsss = new ArrayList <Ticket> ();
			for(int i = 0; i<counter; i++) {  //create new tickets
				ticketsss.add((cm.getCourse((String)gui.GUI.courseSelection.getSelectedItem())).createNewTicket(tc, pointValue));
			}
			TicketSheet ts = new TicketSheet(ticketsss);
			ts.setVisible(true);
		}

		/*
		 * The user selects the "Scan Ticket" button after selecting a student from the
		 * student list.  The user is then prompted to input a ticket idea, can be done
		 * manually of with a barcode scanner.  The ticket id is matched to a ticket in
		 * the system and that ticket is associated to the selected student.
		 */
		else if(e.getActionCommand().equals("Scan Tickets")) {
			int tixID = -1;
			Student s = (Student)gui.GUI.studentList.getSelectedValue();
			if (s == null) {  //if user didn't select a student
				JOptionPane.showMessageDialog(null, "Error: You must select a student " +
													"before hitting this button");
				return;
			}
			while (tixID == -1) {
				String tid = JOptionPane.showInputDialog(null, "Enter the ID number for the ticket.");
				if(tid == null) return;  //user hit cancel
				try {
					tixID = Integer.parseInt(tid);
				} catch(NumberFormatException nfe) {  //if user entered something other than a number
					JOptionPane.showMessageDialog(null, "Ticket ID's must be formatted as an integer");
					return;
				}
				if(tixID == -1)
					return;
			}
			boolean flag = false;
			for (TicketCategory tc : cm.getCourse((String)gui.GUI.courseSelection.getSelectedItem()).tickCats) {
				for (Ticket t : tc.getTickets()) {
					if (t.getID() == tixID) { //search for the ticket in the course
						flag = true;
						for (Student stud : cm.getCourse((String)gui.GUI.courseSelection.getSelectedItem()).getStudents()) {
							if (stud.contains(t)){  //if a student already has that ticket
								JOptionPane.showMessageDialog(null, "This ticket is already associated" +  //checks if the ticket is already associated
																	"with a student.");
								return;
							}
						}
					}
				}
			}
			if(!flag) {
				JOptionPane.showMessageDialog(null, "Error: This ticket does not exist in this class.");
				return;
			}
			
			for (TicketCategory tc : cm.getCourse((String)gui.GUI.courseSelection.getSelectedItem()).tickCats) {
				for (Ticket t : tc.getTickets()) {
					if (t.getID() == tixID) {
						s.associateTicket(t);		//associate ticket
						JOptionPane.showMessageDialog(null, "Ticket " + tixID + " was associated with " + 
								s.getUserName());
					}
				}
			}
		GUI.updateTicketList(cm);
		}
		
		/*
		 * The user selects the "Display All Tickets" button.  No matter what course
		 * is selected or what what student every tickets currently in the system
		 * is displayed in a ticket sheet in a separate window.
		 */
		else if(e.getActionCommand().equals("Display All Tickets")) {
			ArrayList<Ticket> tickets = new ArrayList<Ticket>();
			for(Course c : cm.courses) {
				for(TicketCategory tc : c.getCategories()) {
					for(Ticket t : tc.getTickets()) {
						tickets.add(t);			//get all tickets from all of the course
					}
				}
			}
			if(tickets.size() == 0) {
				JOptionPane.showMessageDialog(null, "There were no tickets created.");
				return;
			}
			TicketSheet ts = new TicketSheet(tickets);
			ts.setVisible(true);
		}
		
		/*
		 * The user clicks the "Display Course Tickets" button.  All of the tickets
		 * from the course selected in the JCombo Box are displayed in a ticket sheet
		 * in a separate window.
		 */
		else if(e.getActionCommand().equals("Display Course Tickets")) {
			Course c = cm.getCourse((String)gui.GUI.courseSelection.getSelectedItem());
			ArrayList<Ticket> tickets = new ArrayList<Ticket>();
			for(TicketCategory tc : c.getCategories()) {
				for(Ticket t : tc.getTickets()) {		//get all of the tickets from the selected course
					tickets.add(t);
				}
			}
			if(tickets.size() == 0) {
				JOptionPane.showMessageDialog(null, "There were no tickets created in this course.");
				return;
			}
			TicketSheet ts = new TicketSheet(tickets);
			ts.setVisible(true);
		}
		
		/*
		 * The user selects the JMenu item "Quit".  The window closes and the 
		 * program is terminated.
		 */
		else if(e.getActionCommand().equals("Quit")) {
			gui.GUI.window.dispose();
		}
		
		/*
		 * The user selects the JMenu item "Save".  The program saves each course,
		 * to a separate file.  In each course file all the students that have
		 * at least one ticket and all of their tickets are saved.
		 */
		else if(e.getActionCommand().equals("Save")) {
			FileManager fm = new FileManager(cm.file);
			for(Course c : cm.courses) {
				for (Student s : c.getStudents()) {
					if(s.getNumTicks() == 0) {		//if student doesn't have any tickets
						JOptionPane.showMessageDialog(null, "Student " + s + " has not acquired any tickets and " +
															"therefore will not be saved.");
						//the student won't be saved, because our load and save functionality
						//does not include loading and saving students who have no ticket to the file
					}
				}
				fm.saveFile(c);
			}
		}
		
		/*
		 * The user selects the "Delete Selected Ticket" button.  The user must have
		 * already selected a ticket from the ticket list.  The ticket is delete from
		 * the student's arraylist of tickets and then the ticket is deleted from the
		 * arraylist of tickets in that ticket's ticket category.  The user receives
		 * a confirmation message and then the ticket list window is refreshed.
		 */
		else if(e.getActionCommand().equals("Remove Ticket")) {
			Ticket t = (Ticket)GUI.ticketList.getSelectedValue();		//get selected ticket
			Student s = (Student)GUI.studentList.getSelectedValue();	//get selected student
			int indexToDelete = -1;
			if (t == null) {
				JOptionPane.showMessageDialog(null, "Error: You must select a ticket " +
													"before hitting this button");
				return;
			}
			int x = JOptionPane.showConfirmDialog(null, "Warning!  You are about to delete\n" + t + "\n from " +
														s.getUserName() + ". Continue?");
			if (x == 1 || x == 2) {		//user hit no or cancel
				return;
			}
			for(Ticket td : s.getTickets()) {	//each one of the students tickets
				if(td.getID() == t.getID())
					indexToDelete = s.getTickets().indexOf(t);		//get index of ticket to delete from student
			}
			if (indexToDelete > -1)
				s.studTicks.remove(indexToDelete);		//if ticket was found delete it
			else {
				JOptionPane.showMessageDialog(null, "Please select a ticket.");
				return;
			}
			int indexToDelete2 = -1;	//reset indeToDelete
			for (TicketCategory tc : cm.selectedCourse.getCategories()) {
				for(Ticket td : tc.getTickets()) {
					if(td.getID() == t.getID())
						indexToDelete = s.getTickets().indexOf(t);  //get the index of the ticket in the ticket category
				}
				if (indexToDelete2 > -1)
					tc.ticks.remove(indexToDelete2);
			}
			JOptionPane.showMessageDialog(null, "Student: " + s.getUserName() + " " + 
													t + "   was successfully deleted.");
			GUI.updateTicketList(cm);
		}
		
		/*
		 * When the user selects the "Student Grade" button.  The user must have already selected a student.
		 * The user is prompted with a message about how they would like to calculate the score for the
		 * selected student.  They have the option to see the student's overall total points earned or
		 * the student's points earned in one ticket category.
		 */
		else if(e.getActionCommand().equals("Student Grade")) {
			cm.selectedCourse = cm.getCourse((String)gui.GUI.getCSComboBox().getSelectedItem());
			Student s = (Student)gui.GUI.studentList.getSelectedValue();
			if (s == null) {
				JOptionPane.showMessageDialog(null, "Error: You select a student " +
													"before hitting this button");
				return;
			}
			ArrayList<TicketCategory> tcs = new ArrayList<TicketCategory>();
			for (Ticket t : s.getTickets()) {
				if (!(tcs.contains(t.getCategory())))
					tcs.add(t.getCategory());		//get and store all categories of which the student has acquired tickets
			}
			String[] choices = new String[tcs.size()+1];
			for (int i = 0; i < tcs.size(); i++)
				choices[i] = tcs.get(i).getName();		//array to hold the choices of categories for user selection
			choices[tcs.size()] = "Overall Grade";
		    String input = (String) JOptionPane.showInputDialog(null, "Calculate grade by ticket category\nor calculate overall grade?",
		    							"Grade Calculator", JOptionPane.QUESTION_MESSAGE, null,
		    							choices, choices[0]);   //input message asking user how to calculate the grade: by category or overall grade
		    if(input == null)	//user canceled
		    	return;
		    if (!(input == choices[tcs.size()])) {  //if user selected anything but the last choice
		    	double points = 0;
		    	for(Ticket t : (s.getTickets(cm.selectedCourse.getCategory(input))))
		    		points += t.getValue();		//total points of all the students tickets in the choosen category
		    	JOptionPane.showMessageDialog(null, s.getUserName() + " acquired " + (s.getTickets
		    										(cm.selectedCourse.getCategory(input)).size() + " tickets" +
		    										" in " + input + ".\nTotal Points Earned: " + points));
		    }
		    else {		//the user selected "Overall Grade"
		    	double points = 0;
		    	for(Ticket t : s.getTickets())
		    		points += t.getValue();		//total the points of all tickets earned by the student
		    	JOptionPane.showMessageDialog(null, s.getUserName() + " acquired " + s.getNumTicks() + " tickets." +
		    										" \nTotal Points Earned: " + points);
		    }
		}
		
		/*
		 * The user selects the "Course Grades" button.  There must be students in the
		 * selected course for the user to continue.  The user is then prompted with a
		 * window that gives the user the option to calculate all students' points
		 * in just one ticket category or to calculate all students' overall points
		 * for the course.
		 */
		else if(e.getActionCommand().equals("Course Grades")) {
			if((cm.getCourse((String)GUI.courseSelection.getSelectedItem()).studList.size() == 0)) {	//if no students are in the course
				JOptionPane.showMessageDialog(null, "There are no students in this course");
				return;
			}
			
			ArrayList<TicketCategory> tcs = new ArrayList<TicketCategory>();
			for (TicketCategory tc : cm.getCourse((String)GUI.courseSelection.getSelectedItem()).getCategories()) {
				tcs.add(tc);		//get all categories and store them
			}
			String[] choices = new String[tcs.size()+1];
			for (int i = 0; i < tcs.size(); i++)
				choices[i] = tcs.get(i).getName();		//add categories to selection list
			choices[tcs.size()] = "All Student Grades";	//add option of all student grades to the end of the list of choices 
			String input = (String) JOptionPane.showInputDialog(null, "All student grades by cateogry, or\n all student grades?",
					"Grade Calculator", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
			
			if(input == null)	//user canceled
				return;
			
			JFrame frame = new JFrame();
			String[] classList = new String[100];
			double points = 0;
			double[] pointHolder = new double[1000];
			if (input == choices[tcs.size()]) {		//if user selected "All Student Grades"
				for(int i = 0; i < cm.getCourse((String)GUI.courseSelection.getSelectedItem()).getStudents().size(); i++) {
					for(Ticket t : cm.getCourse((String)GUI.courseSelection.getSelectedItem()).getStudents().get(i).getTickets()) {
						points += t.getValue();
					}
					pointHolder[i] = points;	//total the score and save them
					points = 0;
				}
				for (int i = 0; i < cm.getCourse((String)GUI.courseSelection.getSelectedItem()).getStudents().size(); i++) {
					double z = pointHolder[i];
					classList[i] = cm.getCourse((String)GUI.courseSelection.getSelectedItem()).getStudents().get(i).getUserName() + "  " +
								Double.toString(z);  //assign each student their total score
				}
			}
			else {
				for (int i = 0; i < cm.getCourse((String)GUI.courseSelection.getSelectedItem()).getStudents().size(); i++){
					for (Ticket t : cm.getCourse((String)GUI.courseSelection.getSelectedItem()).getStudents().get(i).getTickets(cm.getCourse((String)GUI.courseSelection.getSelectedItem()).getCategory(input))) {
						points += t.getValue();
					}
					pointHolder[i] = points;	//total the score for the selected category and save them
					points = 0;
				}
				for (int i = 0; i < cm.getCourse((String)GUI.courseSelection.getSelectedItem()).getStudents().size(); i++){
					if (cm.getCourse((String)GUI.courseSelection.getSelectedItem()).getStudents().get(i).getTickets(cm.getCourse((String)GUI.courseSelection.getSelectedItem()).getCategory(input)).size() 
							> 0 ){
						double z = pointHolder[i];
						classList[i] = cm.getCourse((String)GUI.courseSelection.getSelectedItem()).getStudents().get(i).getUserName() + "  " +
						Double.toString(z);		//assign each student their selected category total score
					}
				}
			}
		    
			JOptionPane.showInputDialog(frame, "Points", "Ticket Category: " + input, JOptionPane.QUESTION_MESSAGE,
				        					null, classList, "Titan");
		}
	
	}

}
