package com.pcg.whiodbcsv.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Area {

	String areaName;
	Map<String, Line> lines;

	private Area() {
	}

	public Area(String areaName) {
		this.areaName = areaName;
		lines = new TreeMap<>();
	}

	public Line addLine(Line line) {
		if (!lines.containsKey(line.lineName))
			lines.put(line.lineName, line);
		return getLine(line.lineName);
	}

	public Line getLine(String lineName) {
		return lines.containsKey(lineName) ? lines.get(lineName) : null;
	}

	public List<Line> getLines() {
		return new ArrayList<>(lines.values());
	}
	public String getAreaName() {
		return areaName;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Area) {
			Area other = (Area) obj;
			return this.areaName.equals(other.areaName);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 17 * areaName.hashCode();
	}

}
