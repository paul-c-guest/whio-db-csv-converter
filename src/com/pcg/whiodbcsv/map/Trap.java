package com.pcg.whiodbcsv.map;

public class Trap implements Comparable<Trap> {

	String trapName;
	String comment;
	String trapType;

	private Trap() {
	}

	public Trap(String trapID, String comment, String trapType) {
		this.trapName = trapID;
		this.comment = comment;
		this.trapType = trapType;
	}

	@Override
	public int compareTo(Trap other) {
			return this.trapName.compareToIgnoreCase(other.trapName);
	}

	@Override
	public String toString() {
		return (trapName + ", " + comment);
	}
	
	public String toHTMLString() {
		return ("<b>" + trapName + "</b>" + " [" +  trapType + "]: " + comment);
	}

}
