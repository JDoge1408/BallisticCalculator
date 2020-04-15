public class Bullet {
	private double mass;
	private double velocity;
	private String name;
	private String caliber;

	public Bullet() {
		mass = 0;
		velocity = 0;
	}

	public Bullet(double m, double v, String n, String c) {
		setSpecs(m, v, n, c);
	}

	public void setSpecs(double m, double v, String n, String c) {
		mass = m;
		velocity = v;
		name = n;
		caliber = c;
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

	public String getCaliber() {
		return caliber;
	}

	/* This calculates muzzle energy using mass and velocity from the cartridge */
	public double muzzleEnergy() {
		return (mass * velocity * velocity) / (64.32 * 7000);
	}
}
