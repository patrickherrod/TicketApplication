package csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
 * 	This class is used to write out CSV files. 
 */
public class CSVWriter {
	private PrintWriter pwr;

	/*	
	 * 	Creates a csv writer which writes to the provided filename.
	 * 	@param f - the file to be written to
	 */
	public CSVWriter (File f)
		throws FileNotFoundException {
		pwr = new PrintWriter(					//assigns pwr to a new printwriter
				new FileOutputStream(f));		//that is a fileoutstream
		
	}
	
	/*
	 * 	Writes out each value in the arraylist on a single line 
	 * 	in the csv file, with each value seperated by a comma.
	 * 	@param ls - the list of items to be written to the file
	 */
	public void writeLine(ArrayList<String> ls) 
		 {
		for (int i = 0; i < ls.size()-1; i++) {
			pwr.print(ls.get(i) + ", ");		//writes every element in the list followed by a comma
		}										//except the last element
		pwr.println(ls.get(ls.size()-1));		//this prints the last element without a following comma
	}

	/*
	 * 	Closes the internal PrintWriter.
	 */
	public void close() {
		pwr.close();
		
	}
}
