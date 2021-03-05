package com.pcg.whiodbcsv.map;

public class Trap implements Comparable<Trap> {

	String trapName;
	String comment;
	String trapType;

	private Trap() {
	}

	public Trap(String trapID, String comment, String trapType) {
		this.trapName = trapID;
		this.trapType = trapType;
		
		if (comment.equals("N/A"))
			this.comment = "";
		else
			this.comment = comment;
	}

	@Override
	public int compareTo(Trap other) {
		return this.getInt() - other.getInt();
	}

	private int getInt() {
		StringBuilder number = new StringBuilder();
		for (char ch : trapName.toCharArray())
			if (Character.isDigit(ch))
				number.append(ch);
		return Integer.parseInt(number.toString());
	}

	@Override
	public String toString() {
		return (trapName + ", " + comment);
	}

	public String toHTMLString() {
		return (trapName + " [" + trapType + "] " + comment);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Trap && this.getClass().cast(obj).trapName.equals(this.trapName);
	}

	@Override
	public int hashCode() {
		return 47 * trapName.hashCode() + 47 * trapType.hashCode();
	}

}
