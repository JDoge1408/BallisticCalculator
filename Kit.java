import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Kit {
	private double scopeOffset;
	private int zeroDistance;
	private String name;
	private int kitChoice;
	ArrayList<String> names = new ArrayList<String>();
	ArrayList<Integer> zeroDistances = new ArrayList<Integer>();
	ArrayList<Double> scopeOffsets = new ArrayList<Double>();

	public Kit() {
		scopeOffset = 0;
		zeroDistance = 0;
	}

	public Kit(double s, int z, String n) throws IOException {
		newKit(s, z, n);
	}

	public void setSpecs(int c) {
		// sets specs using data from ArrayLists depending on the users choice
		this.scopeOffset = scopeOffsets.get(c);
		this.zeroDistance = zeroDistances.get(c);
		this.name = names.get(c);
	}

	public void newKit(double s, int z, String n) throws IOException {

		// writes info about new kit to data file
		File outFile = new File("kitdata.txt");
		FileWriter fWriter = new FileWriter(outFile, true);
		PrintWriter kitPrinter = new PrintWriter(fWriter);
		kitPrinter.print(n + "," + z + "," + s + ",");
		kitPrinter.close();

		updateLists();
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

	// returns arraylist of kit names
	public ArrayList<String> getNames() {
		return names;
	}

	public ArrayList<Integer> getZeros() {
		return zeroDistances;
	}

	public ArrayList<Double> getOffsets() {
		return scopeOffsets;
	}

	// gets information from data file for each bullet
	public void updateLists() throws FileNotFoundException {
		Scanner kitScanner = new Scanner(new FileReader("kitdata.txt"));
		kitScanner.useDelimiter(",");

		// loops through scanner and adds the information to their respective ArrayLists
		while (kitScanner.hasNext()) {
			names.add(kitScanner.next());
			zeroDistances.add(kitScanner.nextInt());
			scopeOffsets.add(kitScanner.nextDouble());
		}
		kitScanner.close();
	}
}
