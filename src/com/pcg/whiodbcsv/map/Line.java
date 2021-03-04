package com.pcg.whiodbcsv.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Line implements Comparable<Line> {

	String lineName;
	Map<String, Status> statuses;

	private Line() {
	}

	public Line(String line) {
		lineName = line;
		statuses = new TreeMap<>();
	}

	public Status addStatus(Status status) {
		if (!statuses.containsKey(status.getEnumName()))
			statuses.put(status.getEnumName(), status);
		return statuses.get(status.getEnumName());
	}

	public List<Status> getStatuses() {
		return new ArrayList<>(statuses.values());
	}
	
	public String getLineName() {
		return lineName;
	}

	@Override
	public int compareTo(Line other) {
		return this.lineName.compareToIgnoreCase(other.lineName);
	}
}
