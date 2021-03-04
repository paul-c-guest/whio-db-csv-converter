package com.pcg.whiodbcsv.map;

public enum Status {

	FUNCTIONING {
		@Override
		String fullString() {
			return "Still functional";
		}
	},

	NOT_FUNCTIONING {
		@Override
		String fullString() {
			return "Not functional";
		}
	},

	MISSING {
		@Override
		String fullString() {
			return "Missing";
		}
	};

	abstract String fullString();

}
