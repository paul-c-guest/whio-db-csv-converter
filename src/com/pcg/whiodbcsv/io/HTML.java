package com.pcg.whiodbcsv.io;

public class HTML {

	/**
	 * wrap a string of body text with the html tag H1 (Heading 1)
	 * @param text string to wrap
	 * @return the wrapped string for use in html body text
	 */
	public static String heading1(String text) {
		return "<h1>" + text + "</h1>";
	}
	
	/**
	 * wrap a string of body text with the html tag H2 (Heading 2)
	 * @param text string to wrap
	 * @return the wrapped string for use in html body text
	 */
	public static String heading2(String text) {
		return "<h2>" + text + "</h2>";
	}
	
	public static String bold(String text) {
		return "<b>" + text + "</b>";
	}
	
	public static String fileHeader(String title) {
		return "<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" + "<meta charset=\"UTF-8\">\n" + "<title>" + title
				+ "</title>\n" + "</head>\n" + "<body>";
	}

	public static String fileHeader() {
		return fileHeader("Maintenance Sheet");
	}
	
	public static String fileCloser() {
		return "</body>\n" + "</html>";
	}

}