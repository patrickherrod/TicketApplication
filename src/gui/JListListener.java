package gui;

import hw5.CourseManager;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/*
 * 	This class runs the Listener for the JList
 */

public class JListListener implements ListSelectionListener {

	private CourseManager cm;

	/*
	 * 	Sets the listener to run for the desired Jlist
	 * 	@param cm - The course manager that handles the courses
	 */
	
	public JListListener(CourseManager cm) {
		this.cm = cm;
	}

	/*
	 * Listener method that updates the student ticket list.
	 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == true)
			GUI.updateTicketList(cm);

	}

}
