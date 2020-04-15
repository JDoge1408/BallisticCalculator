import java.awt.*;
import java.math.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;

//S = VoT + 1/2 A ( T x T )
public class test extends JFrame {

	// variables for constructing main GUI
	private JLabel lblKit;
	private JLabel lblBullet;
	private JButton btnNewKit;
	private JButton btnNewBullet;
	private JButton btnCalculate;
	private JComboBox cboKit;
	private JComboBox cboBullet;
	
	double td = 0; // Time to reach a designated distance
	double s = 0; // Bullet Drop
	double m = 0; // Angle of barrel mom/moa/mil
	double g = 9.81; // Gravity coefficient
	int[] Distances = { 25, 50, 75, 100, 150, 200, 300, 400, 500, 1000 };
	String[] Calibers = { "7.62x39", "7.62 NATO", "22lr", "17hmr" }; // List of possible calibers
	DecimalFormat df = new DecimalFormat("0.00");

	// String arrays for information from the dialog boxes "New Kit" and "New
	// Bullet"
	// Set the strings currently as option1", "option2", "option3. You may delete
	// these
	// strings and import the information needed.
	String[] kit_dialog = { "option1", "option2", "option3" };
	String[] bullet_dialog = { "option1", "option2", "option3" };

	// Main GUI Constructor
	public test() {
		setTitle("Ballistic Calculator");
		setSize(640, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Panel that is empty to add content
		JPanel panel = new JPanel();
		panel.setLayout(null);
		add(panel);

		// Labels for "Kits" and "Bullets"
		JLabel lblKit = new JLabel("Kits");
		lblKit.setBounds(0, 0, 50, 50);
		panel.add(lblKit);
		JLabel lblBullet = new JLabel("Bullets");
		lblBullet.setBounds(0, 50, 50, 50);
		panel.add(lblBullet);

		// Buttons for "New Kit", "New Bullet", and "Calculate"
		JButton btnNewKit = new JButton("New Kit");
		btnNewKit.setBounds(0, 100, 100, 100);
		panel.add(btnNewKit);
		JButton newBullet = new JButton("New Bullet");
		newBullet.setBounds(540, 100, 100, 100);
		panel.add(newBullet);
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.setBounds(220, 100, 200, 100);
		panel.add(btnCalculate);

		// Combo Boxes for Kit and Bullet
		// Grabs information from the string array
		JComboBox cboKit = new JComboBox(kit_dialog);
		cboKit.setSelectedIndex(0);
		cboKit.setBounds(100, 0, 540, 50);
		panel.add(cboKit);
		JComboBox cboBullet = new JComboBox(bullet_dialog);
		cboBullet.setSelectedIndex(0);
		cboBullet.setBounds(100, 50, 540, 50);
		panel.add(cboBullet);

		setVisible(true);
	}

	public static void main(String[] args) {

		// new test() calls to the main GUI constructor created
		new test();

		/* Find another way to store this information */
		Bullet a308 = new Bullet(147, 2750, "Magtech M80 Ball", "7.62 NATO");
		Kit kit1 = new Kit(2.1, 100, "Ruger American Predator", "7.62 NATO");

		/*
		 * Give information about the gun and cartridge to ensure information is
		 * selected correctly
		 */
		System.out.println(
				"The Muzzle Energy of " + a308.getName() + " is " + df.format(a308.muzzleEnergy()) + " ft-lbs");
		System.out.println("You are shooting out of a " + kit1.getName());
		System.out.println("Zeroed at " + kit1.getZero() + " Yards");
		System.out.println("With a Scope Offset of " + kit1.getOffset() + " Inches");

		int t = 1; // Counting 1 second at a time, for testing
		double d = 0; // Distance traveled
		s = (m * t) + (g / 2) * (t * t);
		// originally: d = a9.getVelocity() * t;
		d = a308.getVelocity() * t;
		System.out.println("Bullet Drop after " + t + " Second: " + df.format(s) + " Inches");
		System.out.println("Bullet Distance after " + t + " Second: " + df.format(d / 3) + " Yards");

		int i = 0;
		while (i < 5) {
			t++;
			s = (m * t) + (g / 2) * (t * t);
			d = a308.getVelocity() * t;
			System.out.println("Bullet Drop after " + t + " Seconds: " + df.format(s) + " Inches");
			System.out.println("Bullet Distance after " + t + " Seconds: " + df.format(d / 3) + " Yards");
			i++;
		}

		/* (Distance/(Velocity/3)) = Time to reach distance */
		for (int j = 0; j < Distances.length; j++) {
			td = (Distances[j] / (a308.getVelocity() / 3));
			s = (m * td) + (g / 2) * (td * td);
			System.out.println("Bullet Drop at " + Distances[j] + " Yards: " + df.format(s) + " Inches");
		}
	}
}
