package com.pcg.whiodbcsv.map;

public enum MaintenanceStatus {

	FUNCTIONING {
		@Override
		public String fullString() {
			return "Still functional";
		}
	},

	NOT_FUNCTIONING {
		@Override
		public String fullString() {
			return "Not functional";
		}
	},

	MISSING {
		@Override
		public String fullString() {
			return "Missing";
		}
	};

	abstract String fullString();

}
