package com.pcg.whiodbcsv;

import java.io.File;

public class WhioMaintenanceSheetConverter {

	private static final String HELP_TEXT = "provide a valid .csv file to convert, exported from the whio database.\ne.g. \"WhioMaintenanceSheetConverter output.csv\"\n";

	public static void main(String[] args) {
		if (args.length == 1) {
			try {

				File input = new File(args[0]);

				Converter.convertFile(input);

				System.out.println("conversion successful.\n");

			} catch (Exception any) {
				System.out.println("something was wrong with the given input.\n");
				System.out.println(any.getMessage());
			}

		} else {
			System.out.println(HELP_TEXT);
		}
	}

}
