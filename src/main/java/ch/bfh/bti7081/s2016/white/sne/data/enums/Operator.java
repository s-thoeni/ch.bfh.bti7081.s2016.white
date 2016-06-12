package ch.bfh.bti7081.s2016.white.sne.data.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * ENUM type for compare operator
 * @author team white
 *
 */
public enum Operator {
	/**
	 * greater than operator
	 */
	GREATER(">") {
		@Override
		public boolean compareInt(int x1, int x2) {
			return x1 > x2;
		}
	},
	/**
	 * lesser than operator
	 */
	LESSER("<") {
		@Override
		public boolean compareInt(int x1, int x2) {
			return x1 < x2;
		}
	},
	/**
	 * greater than or equal operator
	 */
	GREATER_EQUAL(">=") {
		@Override
		public boolean compareInt(int x1, int x2) {
			return x1 >= x2;
		}
	},
	/**
	 * lesser than or equal operator
	 */
	LESSER_EQUAL("<=") {
		@Override
		public boolean compareInt(int x1, int x2) {
			return x1 <= x2;
		}
	};

	/**
	 * Text for friendly name
	 */
	private final String text;

	/**
	 * private constructor for ENUM
	 * @param text
	 */
	private Operator(String text) {
		this.text = text;
	}

	/**
	 * abstract method for comparing two integers
	 * @param x1 - first int value for comparison
	 * @param x2 - second int value for comparison
	 * @return
	 */
	public abstract boolean compareInt(int x1, int x2);

	/**
	 * Name to value map for friendly name
	 */
	private static final Map<String, Operator> nameToValueMap;

	static {
		// Really I'd use an immutable map from Guava...
		nameToValueMap = new HashMap<String, Operator>();
		for (Operator op : EnumSet.allOf(Operator.class)) {
			nameToValueMap.put(op.text, op);
		}
	}

	/**
	 * retuns Operator for given friendly name
	 * @param friendlyName as String
	 * @return Operator
	 */
	public static Operator fromFriendlyName(String friendlyName) {
		return nameToValueMap.get(friendlyName);
	}

	@Override
	public String toString() {
		return text;
	}
}
