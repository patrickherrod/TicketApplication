package hw5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import tickets.Course;
import tickets.Student;
import tickets.Ticket;
import tickets.TicketCategory;
import csv.CSVParser;
import csv.CSVWriter;

/*
 * 	This class the loading and saving of files.  It saves each course in its
 * 	own seperate file.
 */
public class FileManager {
	
	File csvFile;
	
	/*
	 * 	Basic constructor that sets the csvFile field to the file that is
	 * 	passed to it in a parameter.
	 * 	@param csvFile - the file to save and load a course to and from
	 */
	public FileManager(File csvFile) {
		this.csvFile = csvFile;
	}
	
	/*
	 * 	Returns the csvFile that is being saved and loaded from
	 * 	@return csvFile - the csvFile
	 */
	public File getConfigFile() { return csvFile; }
	
	/*
	 * 	Loads a specific course from the file held in the csvFile field.
	 * 	@param c - the specified course to load
	 */
	public void loadFile(Course c) throws FileNotFoundException{
		int id = 0;
		double pointValue = 0.0;
		CSVParser csvp = new CSVParser(csvFile);
		while (csvp.hasNextLine()) {	//loop that recreates the tickets from cvsFile
			ArrayList<String> line = csvp.getNextLine();
			id = Integer.parseInt(line.get(0));
			pointValue = Double.parseDouble(line.get(1));
			if (c.getCategory(line.get(2)) == null)
				c.addCategory(line.get(2));
			Ticket t = new Ticket(c.getCategory(line.get(2)), pointValue, id);
			c.getCategory(line.get(2)).add(t);
			if (line.size() == 4 ) {
				Student student = new Student(line.get(3));
				if (c.getStudent(line.get(3)) == null)
					c.addStudent(student);
				c.getStudent(line.get(3)).associateTicket(t);
			}
		}

	}
	
	/*
	 * 	Saves a specific course to the file held in the csvFile field
	 * 	@param c - the specific course to save
	 */
	public void saveFile(Course c){
		try {
			CSVWriter cWR = new CSVWriter(csvFile);
			ArrayList<String> ticketData = new ArrayList<String>();			//arraylist for the lines of the ticket
			ArrayList<Ticket> ticksWritten = new ArrayList<Ticket>();		//arraylist keeping track of all the written tickets
			for (Student s : c.studList) {
				for (Ticket t : s.getTickets()) {		//write the tickets of each student
					ticksWritten.add(t);
					ticketData.add(t.getID() + "");
					ticketData.add(t.getValue() + "");
					ticketData.add(t.getCategory().getName());
					ticketData.add(s.getUserName());
					cWR.writeLine(ticketData);			//write ticket data
					ticketData.clear();					//clear arraylist of ticket data
				}
			}
			for (TicketCategory tc : c.tickCats)
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
	
	

}
