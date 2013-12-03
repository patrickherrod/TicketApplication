package gui;

import hw5.CourseManager;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/*
 * 	This class runs the Listener for the JComboBox
 */

public class JComboBoxListener implements ItemListener{

	private CourseManager cm;

	/*
	 * 	Sets the Listener to run for the CourseMananger desired
	 * 	@param cm - The course manager handling the courses
	 */
	
	public JComboBoxListener(CourseManager cm) {
		this.cm = cm;
	}

	/*
	 * Listener method that updates the student list whenever the selected course is changed.
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED) {
			GUI.updateStudentList(cm);
		}
	}
	
	
}

