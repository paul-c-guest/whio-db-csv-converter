package com.pcg.whiodbcsv.map;

public enum MaintenanceStatus {

	FUNCTIONING {
		@Override
		public String descriptiveString() {
			return "Still functioning";
		}
	},

	NOT_FUNCTIONING {
		@Override
		public String descriptiveString() {
			return "Not functioning";
		}
	},

	MISSING {
		@Override
		public String descriptiveString() {
			return "Missing";
		}
	};

	abstract String descriptiveString();

}
