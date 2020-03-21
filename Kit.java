public class Kit {
	private double scopeOffset;
	private int zeroDistance;
	private String name;
	
	public Kit() {
		scopeOffset = 0;
		zeroDistance = 0;
	}
	
	public Kit(double s, int z, String n) {
		setSpecs(s, z, n);
	}
	
	public void setSpecs(double s, int z, String n) {
		scopeOffset = s;
		zeroDistance = z;
		name = n;
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
}
