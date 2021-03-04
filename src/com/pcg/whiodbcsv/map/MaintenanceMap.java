package com.pcg.whiodbcsv.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaintenanceMap {

	Map<String, Area> areas;

	public MaintenanceMap() {
		areas = new HashMap<String, Area>();
	}

	public boolean hasArea(String areaName) {
		return areas.containsKey(areaName);
	}

	public Area getArea(String areaName) {
		return (areas.containsKey(areaName)) ? areas.get(areaName) : null;
	}

	public Area addArea(Area area) {
		if (!hasArea(area.areaName))
			areas.put(area.areaName, area);
		return getArea(area.areaName);
	}

	public List<Area> getAreas() {
		List<Area> list = new ArrayList<Area>(areas.values());
		return list;
	}
	
	public String getPrimaryAreaName() {
		int highest = 0;
		String valley = null;
		for (Area a : areas.values()) {
			if (a.getLines().size() > highest) {
				highest = a.getLines().size();
				valley = a.areaName;
			}
		}
		return valley;
	}

}
