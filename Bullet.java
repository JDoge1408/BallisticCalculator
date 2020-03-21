public class Bullet {
	private double mass;
	private double velocity;
	private String name;
	
	public Bullet() {
		mass = 0;
		velocity = 0;
	}
	
	public Bullet(double m, double v, String n) {
		setSpecs(m, v, n);
	}
	
	public void setSpecs(double m, double v, String n) {
		mass = m;
		velocity = v;
		name = n;
	}
	
	public double getMass() {
		return mass;
	}
	
	public double getVelocity() {
		return velocity;
	}
	
	public String getName() {
		return name;
	}
	
	public double muzzleEnergy() {
		return (mass * velocity * velocity)/(64.32 * 7000);
	}
}
