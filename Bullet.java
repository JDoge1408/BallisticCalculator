import java.io.*;
import java.util.*;

public class Bullet {
	private double mass;
	private double velocity;
	private String name;
	private int bulletChoice;
	ArrayList<String> names = new ArrayList<String>();
	ArrayList<Double> velocities = new ArrayList<Double>();
	ArrayList<Double> masses = new ArrayList<Double>();

	public Bullet() {
		mass = 0;
		velocity = 0;
	}

	public Bullet(double m, double v, String n) throws IOException {
		newBullet(m, v, n);
	}

	public void setSpecs(int c) {

		// sets variables using ArrayLists and the bullet the user chose
		this.mass = masses.get(c);
		this.velocity = velocities.get(c);
		this.name = names.get(c);
	}

	public void newBullet(double m, double v, String n) throws IOException {
		// writes inputed information to a data file to be stored
		File outFile = new File("bulletdata.txt");
		FileWriter fWriter = new FileWriter(outFile, true);
		PrintWriter bulletPrinter = new PrintWriter(fWriter);
		bulletPrinter.print(n + "," + v + "," + m + ",");
		bulletPrinter.close();

		updateLists();
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

	// returns arrayList of bullet names
	public ArrayList<String> getNames() {
		return names;
	}

	public ArrayList<Double> getMasses() {
		return masses;
	}

	public ArrayList<Double> getVelocities() {
		return velocities;
	}

	// gets information from data file for each bullet
	public void updateLists() throws FileNotFoundException {
		Scanner bulletScanner = new Scanner(new FileReader("bulletdata.txt"));
		bulletScanner.useDelimiter(",");

		// loops through scanner and adds the information to their respective ArrayLists
		while (bulletScanner.hasNext()) {
			names.add(bulletScanner.next());
			velocities.add(bulletScanner.nextDouble());
			masses.add(bulletScanner.nextDouble());
		}
		bulletScanner.close();
	}

	/* This calculates muzzle energy using mass and velocity from the cartridge */
	public double muzzleEnergy() {
		return (mass * velocity * velocity) / (64.32 * 7000);
	}
}
