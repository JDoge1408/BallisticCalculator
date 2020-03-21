public class Kit {
	private double scopeOffset;
	private int zeroDistance;
	private String name;
	private String caliber; //This will compare to the caliber of the selected bullet to ensure they match, also switch this to refer to an array
	
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
