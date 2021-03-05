package com.pcg.whiodbcsv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.pcg.whiodbcsv.html.HTML;
import com.pcg.whiodbcsv.map.Area;
import com.pcg.whiodbcsv.map.Line;
import com.pcg.whiodbcsv.map.MaintenanceMap;
import com.pcg.whiodbcsv.map.MaintenanceStatus;
import com.pcg.whiodbcsv.map.Status;
import com.pcg.whiodbcsv.map.Trap;

/**
 * static collection of methods for purpose of converting provided CSV files to
 * a more readable format
 * 
 * @author paul guest
 *
 */
public class Converter {

	// block public access to no-arg constructor
	private Converter() {
	}

	/**
	 * For conversion of a whio database csv file to a readable collection of
	 * maintenance requirements for the relevant trap locations. <br>
	 * Places the new converted file at the same directory that the target file is
	 * in.
	 * 
	 * @param input a CSV file exported from the Whio trap database
	 * @return a boolean reporting the success of the conversion
	 */
	public static boolean convertFile(File input) {
		if (!input.isFile())
			return false;

		try (Scanner reader = new Scanner(input);) {

			MaintenanceMap mmap = new MaintenanceMap();

			while (reader.hasNextLine()) {

				String line = reader.nextLine();

				// solve problem caused by empty quoted comment being split across two lines
				if (line.charAt(line.length() - 1) == '"')
					line += reader.nextLine();

				String[] values = parseLine(line);
				String status = values[8].toLowerCase();

				if (status.contains("not"))
					put(mmap, values, MaintenanceStatus.NOT_FUNCTIONING);
				else if (status.contains("maintenance"))
					put(mmap, values, MaintenanceStatus.FUNCTIONING);
				else if (status.contains("missing"))
					put(mmap, values, MaintenanceStatus.MISSING);
			}

			File target = new File(getPathForConversion(input));

			if (target.exists()) {
				target.delete();
			}

			target.createNewFile();

			writeFile(target, mmap);

			return true;

		} catch (IOException io) {
		}

		return false;

	}

	/**
	 * create the maintenance sheet file from the supplied MaintenanceMap
	 * 
	 * @param file
	 */
	private static void writeFile(File file, MaintenanceMap mmap) {

		try (PrintWriter writer = new PrintWriter(file);)

		{
			writer.println(HTML.fileHeader(mmap.getPrimaryAreaName()));

			for (Area area : mmap.getAreas()) {
				writer.println(HTML.heading2(area.getAreaName()));

				for (Line line : area.getLines()) {
					writer.println(HTML.bold(line.getLineName().toUpperCase()) + "<br>");

					for (Status status : line.getStatuses()) {
						writer.println(HTML.bold(status.getDescriptiveName() + "<br>"));

						for (Trap trap : status.getTraps()) {
							writer.println(trap.toHTMLString() + "<br>");

						}

					}

					// space after each line grouping
					writer.println("<br>");
				}
			}

			writer.println(HTML.fileCloser());

		} catch (FileNotFoundException e) {

		}
	}

	private static void put(MaintenanceMap mmap, String[] record, MaintenanceStatus maintenanceStatus) {

		Area area = new Area(record[2]);
		Line line = new Line(record[3]);
		Status status = new Status(maintenanceStatus);
		Trap trap = new Trap(record[4], record[9], record[11]);

		mmap.addArea(area).addLine(line).addStatus(status).addTrap(trap);
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
	private static String getPathForConversion(File input) throws IOException {
		String inPath = input.getCanonicalPath();

		// trim file type extension
		for (int i = inPath.length() - 1; i > 0; i--) {
			if (inPath.charAt(i) == '.') {
				inPath = inPath.substring(0, i);
				break;
			}
		}

//		System.out.println(inPath);

		return inPath + ".html";
	}

}
