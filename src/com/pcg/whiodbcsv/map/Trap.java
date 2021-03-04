package com.pcg.whiodbcsv.map;

public class Trap implements Comparable<Trap> {

	String trapName;
	Status status;
	String comment;
	String trapType;

	private Trap() {
	}

	public Trap(String trapID, Status status, String comment, String trapType) {
		this.trapName = trapID;
		this.status = status;
		this.comment = comment;
		this.trapType = trapType;
	}

	@Override
	public int compareTo(Trap other) {
		if (this.status == other.status)
			return this.trapName.compareToIgnoreCase(other.trapName);

		else
			return this.status.ordinal() - other.status.ordinal();
	}

	@Override
	public String toString() {
		return (trapName + ", " + status.toString().toLowerCase() + "\n" + comment);
	}
	
	public String toHTMLString() {
		return ("<b>" + trapName + "</b>" + " [" +  trapType + "]: " + comment);
	}

}
