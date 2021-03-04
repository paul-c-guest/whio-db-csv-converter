package com.pcg.whiodbcsv.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Line implements Comparable<Line> {

	String lineName;
	Map<String, Trap> traps;

	private Line() {
	}

	public Line(String line) {
		lineName = line;
		traps = new TreeMap<>();
	}

	public void addTrap(Trap trap) {
		if (!traps.containsKey(trap.trapName))
			traps.put(trap.trapName, trap);
	}

	public List<Trap> getTraps() {
		List<Trap> list = new ArrayList<Trap>(traps.values());
		return list;
	}
	
	public String getLineName() {
		return lineName;
	}

	@Override
	public int compareTo(Line other) {
		return this.lineName.compareToIgnoreCase(other.lineName);
	}
}
