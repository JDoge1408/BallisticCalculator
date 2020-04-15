public class Kit {
	private double scopeOffset;
	private int zeroDistance;
	private String name;
	private String caliber;

	public Kit() {
		scopeOffset = 0;
		zeroDistance = 0;
	}

	public Kit(double s, int z, String n, String c) {
		setSpecs(s, z, n, c);
	}

	public void setSpecs(double s, int z, String n, String c) {
		scopeOffset = s;
		zeroDistance = z;
		name = n;
		caliber = c;
	}

	public double getOffset() {
		return scopeOffset;
	}

	public int getZero() {
		return zeroDistance;
	}

	public String getName() {
		return name;
	}

	public String getCaliber() {
		return caliber;
	}
}
