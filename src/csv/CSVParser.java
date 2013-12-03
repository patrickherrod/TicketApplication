package csv;

import java.util.ArrayList;
import java.lang.String;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;

/**
        This class is used to parse values out of a comma-seperated file.
*/
public class CSVParser
extends java.lang.Object {

/**
        opens the indicated file for parsing
        @param csvFile - the file to be parsed
*/
   public CSVParser(File csvFile)
      throws FileNotFoundException {
      in = new Scanner(new FileReader(csvFile));
   }

/**
        opens the indicated file for parsing and requires
        each line to have a specified number of values
        @param csvFile - file to be parsed
        @param numeValues - the number of values each line of the
   		csv file must contain
   */
   public CSVParser(File csvFile, int numValues)
      throws FileNotFoundException, IllegalArgumentException {
      values = numValues;
      in = new Scanner(new FileReader(csvFile));
   }

   /**
      Parses and returns the content from the next available
      line of data in the csv file. For more specific details,
      see the class description. If a specific number of values
      per line was specified and the parsed line contains a
      different number of values, an IllegalArgumentException
      will be thrown with the following message: "Expected
      <expected-number-of-values> but found <actual-number-of
      -values>".
      @return line - the content of the current line in the csv file
*/
   public ArrayList<String> getNextLine()
      throws RuntimeException {
      Scanner special = new Scanner(in.nextLine());             //new scanner that reads and deals with strings
      String newWord = null;                                    //temporary place holder for the string
      ArrayList<String> line = new ArrayList<String>();         //arraylist for the organized line of words
      int valueCounter = 0;                                     //to count the words in each line
      special.useDelimiter(",");                                //allows the ability to seperate and grab each word
      while (special.hasNext()) {
         newWord = special.next();
         newWord = newWord.trim();
         line.add(newWord);                                     //appending the trimmed seperated word to the arraylist
         valueCounter++;
      }
      if (values !=0 && valueCounter != values) {               //a check on the number of arguments
         throw new IllegalArgumentException("Error: Expected " + values + 
         " arguments but found " + valueCounter);
      }
      return line;
   }

/**
        determines whether or not there is an available
        line to be read in the csv file
        @return - the boolean value based on whether there
        is a next line
*/
   public boolean hasNextLine() {
      return (in.hasNextLine());
   }

   private Scanner in;                                          //fields for the class
   private int values;
}
