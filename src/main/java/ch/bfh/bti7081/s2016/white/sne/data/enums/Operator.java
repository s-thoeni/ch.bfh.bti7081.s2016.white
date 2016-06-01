package ch.bfh.bti7081.s2016.white.sne.data.enums;

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

	@Override
	public String toString() {
		return text;
	}
}
