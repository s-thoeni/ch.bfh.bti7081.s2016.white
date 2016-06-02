package ch.bfh.bti7081.s2016.white.sne.data.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Operator {
	GREATER(">") {
		@Override
		public boolean compareInt(int x1, int x2) {
			return x1 > x2;
		}
	},
	LESSER("<") {
		@Override
		public boolean compareInt(int x1, int x2) {
			return x1 < x2;
		}
	},
	GREATER_EQUAL(">=") {
		@Override
		public boolean compareInt(int x1, int x2) {
			return x1 >= x2;
		}
	},
	LESSER_EQUAL("<=") {
		@Override
		public boolean compareInt(int x1, int x2) {
			return x1 <= x2;
		}
	};

	private final String text;

	private Operator(String text) {
		this.text = text;
	}

	public abstract boolean compareInt(int x1, int x2);

	private static final Map<String, Operator> nameToValueMap;

	static {
		// Really I'd use an immutable map from Guava...
		nameToValueMap = new HashMap<String, Operator>();
		for (Operator op : EnumSet.allOf(Operator.class)) {
			nameToValueMap.put(op.text, op);
		}
	}

	public static Operator fromFriendlyName(String friendlyName) {
		return nameToValueMap.get(friendlyName);
	}

	@Override
	public String toString() {
		return text;
	}
}
