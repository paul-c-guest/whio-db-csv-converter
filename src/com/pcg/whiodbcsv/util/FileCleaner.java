package com.pcg.whiodbcsv.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileCleaner {

	public static File tidyUp(File input) {

		String line = null;
		File cleaned = null;
		Scanner reader = null;
		PrintWriter writer = null;

		try {
			reader = new Scanner(input);
			cleaned = new File(".temp");
			writer = new PrintWriter(cleaned);
			
			while (reader.hasNextLine()) {
				line = reader.nextLine();
				
				if (line.charAt(line.length() - 1) == '"') 
					line += reader.nextLine();
				
				writer.println(line);
			}

		} catch (FileNotFoundException e) {

		} finally {
			writer.close();
		}

		return cleaned;
	}

}
