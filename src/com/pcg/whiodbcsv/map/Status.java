package com.pcg.whiodbcsv.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Status {

	MaintenanceStatus status;
	Map<String, Trap> traps;

	public Status(MaintenanceStatus status) {
		this.status = status;
		traps = new HashMap<>();
	}

	public String getEnumName() {
		return status.toString();
	}

	public String getDescriptiveName() {
		return status.fullString();
	}

	public Trap addTrap(Trap trap) {
		if (!traps.containsKey(trap.trapName))
			traps.put(trap.trapName, trap);
		return traps.get(trap.trapName);
	}

	public Trap getTrap(String trapName) {
		return traps.get(trapName);
	}

	public List<Trap> getTraps() {
		List<Trap> list = new ArrayList<>(traps.values());
		list.sort(null); // uses Trap.compareTo()
		return list;
	}

}
