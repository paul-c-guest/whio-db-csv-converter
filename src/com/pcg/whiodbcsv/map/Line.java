package com.pcg.whiodbcsv.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Line implements Comparable<Line> {

	String lineName;
	Map<Integer, Status> statuses;

	private Line() {
	}

	public Line(String line) {
		lineName = line;
		statuses = new TreeMap<>();
	}

	public Status addStatus(Status status) {
		if (!statuses.containsKey(status.getEnumOrdinal()))
			statuses.put(status.getEnumOrdinal(), status);
		return statuses.get(status.getEnumOrdinal());
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
