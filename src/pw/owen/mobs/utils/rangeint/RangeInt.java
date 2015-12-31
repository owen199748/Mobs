package pw.owen.mobs.utils.rangeint;

public class RangeInt {
	private int max = 0;
	private int min = 0;

	public RangeInt(int max, int min) {

		this.max = max;
		this.min = min;

	}

	public RangeInt(int auto) {
		this.max = auto;
		this.min = auto;
	}

	public int getMax() {
		return max;
	}

	public int getMin() {
		return min;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getInt() {
		int auto = 0;
		int x = 0;
		if (min > max) {
			auto = max;
			x = min - max;
		} else {
			auto = min;
			x = max - min;
		}

		return auto + ((int) (Math.random() * (x + 1)));
	}

	@Override
	public String toString() {
		return getMin() + "-" + getMax();
	}

}
