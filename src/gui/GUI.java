package gui;
import hw5.CourseManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import tickets.Course;
import tickets.Student;
import tickets.Ticket;

/*
 * 	This class runs the GUI for the program.
 */

public class GUI {
	
	//Public items that constantly need to be accessed by other classes
	public static JComboBox courseSelection;
	public static JList studentList;
	public static DefaultListModel names;
	public static JList ticketList;
	public static DefaultListModel tickets;
	public static JFrame window = new JFrame();
	
	/*
	 * Main method to run GUI
	 */
	public static void main(String[] args) {
		ArrayList<String> courseNames = getLocalCourseNames();
		final CourseManager cm = new CourseManager();
		for(String name : courseNames) {							//Populates the course manager with names of courses
			cm.addCourse(name);										//from the loaded files
		}
		JPanel content = new JPanel();
		
		MainWindowButtons buttonListener = new MainWindowButtons(cm);
		
		//MenuBar
		JMenuBar mb = new JMenuBar();
		JMenu mnuFile = new JMenu("File");
		JMenuItem mnuItemQuit = new JMenuItem("Quit");
		JMenuItem mnuItemSave = new JMenuItem("Save");
		mnuItemQuit.addActionListener(buttonListener);
		mnuItemSave.addActionListener(buttonListener);
		window.setJMenuBar(mb);
		mb.add(mnuFile);
		mnuFile.add(mnuItemSave);
		mnuFile.add(mnuItemQuit);
		
		//Panels
		JPanel pnlSelectedCourse = new JPanel();
		JPanel pnlComboBox = new JPanel();
		JPanel pnlAddButtons = new JPanel();
		JPanel pnlRemoveButtons = new JPanel();
		JPanel pnlGrades = new JPanel();
		JPanel pnlListButtons = new JPanel();
		JPanel pnlJList = new JPanel();
		JPanel pnlJList2 = new JPanel();
		JPanel pnlBothLists = new JPanel();
		
		//Buttons
		JButton btnAddCourse = new JButton("Add Course");
		JButton btnAddStudent = new JButton("Add Student");
		JButton btnCreateTix = new JButton("Create Tickets");
		JButton btnRemoveCourse = new JButton("Remove Course");		
		JButton btnRemoveStudent = new JButton("Remove Student");
		JButton btnRmoveTicket = new JButton("Remove Ticket");	
		JButton btnStudentGrade = new JButton("Student Grade");
		JButton btnCourseGrades = new JButton("Course Grades");
		JButton btnDisplayCourseTickets = new JButton("Display Course Tickets");
		JButton btnDisplayAllTickets = new JButton("Display All Tickets");
		JButton btnScanTickets = new JButton("Scan Tickets");	

		//Label
		JLabel selectedCourseLabel = new JLabel("Selected Course: ");
		
		//Addition of the Action Listeners
		btnAddCourse.addActionListener(buttonListener);
		btnAddStudent.addActionListener(buttonListener);
		btnCreateTix.addActionListener(buttonListener);
		btnRemoveCourse.addActionListener(buttonListener);
		btnRemoveStudent.addActionListener(buttonListener);
		btnRmoveTicket.addActionListener(buttonListener);
		btnStudentGrade.addActionListener(buttonListener);
		btnCourseGrades.addActionListener(buttonListener);
		btnDisplayCourseTickets.addActionListener(buttonListener);
		btnDisplayAllTickets.addActionListener(buttonListener);
		btnScanTickets.addActionListener(buttonListener);
		
		//Course selector JComboBox
		courseSelection = new JComboBox();
		JComboBoxListener courseListener = new JComboBoxListener(cm);
		courseSelection.addItemListener(courseListener);
		for(Course c : cm.courses)
			courseSelection.addItem((String)c.getCourseName());
		cm.selectedCourse = cm.getCourse((String)courseSelection.getSelectedItem());
		
	    //Student JList
		names = new DefaultListModel();
		JListListener listListener = new JListListener(cm);
		studentList = new JList(names);
		studentList.addListSelectionListener(listListener);
	    studentList.setSelectedIndex(0);
	    studentList.setVisibleRowCount(names.size());
		JScrollPane studentListPane = new JScrollPane(studentList);
		studentListPane.setPreferredSize(new Dimension(250,200));
		Border listPanelBorder = BorderFactory.createTitledBorder("Students in the selected course");
		pnlJList.setBorder(listPanelBorder);
		pnlJList.add(studentListPane);
		pnlJList.setVisible(true);

		//Ticket List JList2
		tickets = new DefaultListModel();
		ticketList = new JList(tickets);
	    ticketList.setSelectedIndex(0);
	    ticketList.setVisibleRowCount(tickets.getSize());
		JScrollPane ticketListPane = new JScrollPane(ticketList);
		ticketListPane.setPreferredSize(new Dimension(420,200));
		Border listPanelBorder2 = BorderFactory.createTitledBorder("Tickets that the student has acquired");
		pnlJList2.setBorder(listPanelBorder2);
		pnlJList2.add(ticketListPane);
		pnlJList2.setVisible(true);
		

		//Adding items to their panels
		pnlSelectedCourse.add(selectedCourseLabel);
		pnlSelectedCourse.add(pnlComboBox.add(courseSelection));
		pnlAddButtons.add(btnAddCourse);
		pnlAddButtons.add(btnAddStudent);
		pnlAddButtons.add(btnCreateTix);
		pnlRemoveButtons.add(btnRemoveCourse);
		pnlRemoveButtons.add(btnRemoveStudent);
		pnlRemoveButtons.add(btnRmoveTicket);
		pnlGrades.add(btnStudentGrade);
		pnlGrades.add(btnCourseGrades);
		pnlBothLists.add(pnlJList);
		pnlBothLists.add(pnlJList2);
		pnlListButtons.add(btnDisplayCourseTickets);
		pnlListButtons.add(btnDisplayAllTickets);
		pnlListButtons.add(btnScanTickets);
		
		//Adjusting the Color of the Panels
		pnlSelectedCourse.setBackground(Color.DARK_GRAY);
		pnlAddButtons.setBackground(Color.DARK_GRAY);
		pnlRemoveButtons.setBackground(Color.DARK_GRAY);
		pnlGrades.setBackground(Color.DARK_GRAY);
		pnlBothLists.setBackground(Color.DARK_GRAY);
		pnlListButtons.setBackground(Color.DARK_GRAY);
		
		//Changing JLabel font color
		selectedCourseLabel.setForeground(Color.WHITE);
				
		//Adding the Panels to the Content pane
		content.add(pnlSelectedCourse);
		content.add(pnlAddButtons);
		content.add(pnlRemoveButtons);
		content.add(pnlGrades);
		content.add(pnlBothLists);
		content.add(pnlListButtons);
		
		//Box Layout for content
		content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
		
		//Selected Course
		selectedCourseLabel.setFont(new Font("Sans-Serif", Font.ITALIC, 26));
		
		//Finalizing the window
		window.setResizable(false);
		window.setContentPane(content);
		window.setSize(800,550);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Center the centor on the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = window.getSize().width;
		int h = window.getSize().height;
		int x = (dim.width-w)/2;
		int y = (dim.height-h)/2;
		window.setLocation(x, y);
		updateStudentList(cm);

	}
	
	/*
	 * 	Returns the course names from the files loaded into the system
	 * 	@return courseNames - the course name/s to be returned
	 */
	
	public static ArrayList<String> getLocalCourseNames() { 
		ArrayList<String> courseNames = new ArrayList<String>();
		File localDirectory = new File(".");
		for(File f : localDirectory.listFiles()) {
			if(f.getName().endsWith("-config.csv")) {
				courseNames.add(
			       f.getName().substring(0,
			    		                 f.getName().indexOf("-config.csv")));
				
			}
		}
		return courseNames;
	}
	
	/*
	 * 	Displays the courses in the ComboBox in the GUI
	 * 	@return courseSelection - the name of the course/s in the drop down ComboBox
	 */
	
	public static JComboBox getCSComboBox() {
		return courseSelection;
	}
	
	/*
	 * 	Displays the Students in the JList
	 * 	@return studentList - the name of the student to be displayed in the JList
	 */
	
	public static JList getStudentListJList() {
		return studentList;
	}
	
	/*
	 * 	Updates the JList to show the students in whatever course is selected in the ComboBox
	 * 	@param cm - the course manager handling the courses
	 */
	
	public static void updateStudentList(CourseManager cm) {

		Course c = cm.getCourse((String)gui.GUI.courseSelection.getSelectedItem());
		gui.GUI.names = new DefaultListModel();
		for(Student s : c.getStudents()) {
			gui.GUI.names.addElement(s);
		}
		if(gui.GUI.studentList != null) {
			gui.GUI.studentList.setModel(gui.GUI.names);	//sets the JList to the "names" DefaultListModel of students in the selected list
		}
		refreshTicketList();
	}
	
	/*
	 * 	Updates the JList to show whatever tickets are associated with the selected student
	 * 	@param cm - the course manager handling the courses
	 */
	
	public static void updateTicketList(CourseManager cm) {
		Student s = (Student)gui.GUI.studentList.getSelectedValue();
		gui.GUI.tickets = new DefaultListModel();
		for(Ticket t : s.getTickets()) {
			gui.GUI.tickets.addElement(t);
		}
		if(gui.GUI.ticketList != null) {
			gui.GUI.ticketList.setModel(gui.GUI.tickets);	//sets the JList "tickets" DefualtListModel of the selected student.
		}
	}
	
	/*
	 * 	Whenever their is a manipulation of a the ticket list the JList containing the "tickets" DefaultListModel
	 * 	is updated.
	 */
	
	public static void refreshTicketList() {
		gui.GUI.tickets = new DefaultListModel();
		if(gui.GUI.ticketList != null) {
			gui.GUI.ticketList.setModel(gui.GUI.tickets);
		}
	}
}
