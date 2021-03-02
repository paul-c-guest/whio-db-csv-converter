package com.pcg.whiodbcsv;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * static collection of methods for purpose of converting provided CSV files to
 * a more readable format
 * 
 * @author paul guest
 *
 */
public class Converter {

	// disallow creation of Converter as an object
	private Converter() {
	}

	/**
	 * For conversion of a whio database csv file to a readable collection of
	 * maintenance requirements for the relevant trap locations.
	 * 
	 * @param input a CSV file exported from the Whio trap database
	 * @return a boolean reporting the success of the conversion
	 */
	public static boolean convertFile(File input) {
		if (!input.isFile())
			return false;

		try (Scanner reader = new Scanner(input);

		) {

			File output = new File(getNewPath(input));
			
			// delete file if already present
			if (output.exists()) {
				output.delete();
			}
			output.createNewFile(); 

			while (reader.hasNextLine()) {
				String[] line = parseLine(reader.nextLine());
			}

		} catch (IOException io) {
			return false;
		}

		return false;

	}

	/**
	 * if line conforms to expected comma separated format and demarcations,
	 * produces an array of each element.
	 * <p>
	 * format for array indices is: <br>
	 * 0 geometry, 1 GCX_OID, 2 Area, 3 Line, 4 Tag No, 5 Date Checked, 6 Baited
	 * With, 7 Baited On, 8 Status, 9 Comment, 10 User ID, 11 Trap Type, 12 Trap No,
	 * 13 Easting, 14 Northing
	 * <p>
	 * GCX OID is always three values surrounded in quotes, themselves comma
	 * separated internally.<br>
	 * Where the comment string itself holds a comma, then the comment is surrounded
	 * in quotes.
	 * 
	 * @param line a line from the csv file
	 * @return the string split to an array
	 */
	private static String[] parseLine(String line) {
		List<String> list = new ArrayList<>();
		StringBuilder element = new StringBuilder();
		char[] chars = line.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			
			switch (chars[i]) {

			case '"':
				while (chars[++i] != '"') {
					element.append(chars[i]);
				}

//				System.out.println(element.toString());
				list.add(element.toString());
				element = new StringBuilder();
				i++;
				break;

			case ',':
//				System.out.println(element.toString());
				list.add(element.toString());
				element = new StringBuilder();
				break;

			default:
				element.append(chars[i]);
				break;
			}
		}
		
//		System.out.println(element.toString());
		list.add(element.toString()); // add last element after routine breaks out

		return list.toArray(new String[0]);
	}

	/**
	 * get a path for the converted file to create (or overwrite). this will make
	 * the new file in the directory that the csv file resides in.
	 */
	private static String getNewPath(File input) throws IOException {
		String inPath = input.getCanonicalPath();

		// trim file type extension
		for (int i = inPath.length() - 1; i > 0; i--) {
			if (inPath.charAt(i) == '.') {
				inPath = inPath.substring(0, i);
				break;
			}
		}

//		System.out.println(inPath);

		return inPath + ".txt";
	}

}
