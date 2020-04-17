import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

//S = VoT + 1/2 A ( T x T )
public class test extends JFrame {

	// arraylists to pass arraylists from bullet and kit classes to
	static ArrayList<String> bulletNames = new ArrayList<String>();

	static ArrayList<String> kitNames = new ArrayList<String>();

	// arrays for storing information to give to comboboxes
	String[] bulletArray = bulletNames.toArray(new String[bulletNames.size()]);
	String[] kitArray = kitNames.toArray(new String[kitNames.size()]);

	public int kitChoice;
	public int bulletChoice;

	// variables for constructing main GUI
	private JLabel lblKit;
	private JLabel lblBullet;
	private JButton btnNewKit;
	private JButton btnNewBullet;
	private JButton btnCalculate;
	private JComboBox cboKit;
	private JComboBox cboBullet;

	// Main GUI Constructor
	public test() {
		setTitle("Ballistic Calculator");
		setSize(496, 239);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Panel that is empty to add content
		JPanel panel = new JPanel();
		panel.setLayout(null);
		add(panel);

		// Labels for "Kits" and "Bullets"
		JLabel lblKit = new JLabel("Kits");
		lblKit.setBounds(10, 0, 50, 50);
		panel.add(lblKit);
		JLabel lblBullet = new JLabel("Bullets");
		lblBullet.setBounds(10, 50, 50, 50);
		panel.add(lblBullet);

		// Buttons for "New Kit", "New Bullet", and "Calculate"
		JButton btnNewKit = new JButton("New Kit");
		btnNewKit.setBounds(0, 100, 120, 100);
		btnNewKit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					btnNewKitActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			private void btnNewKitActionPerformed(ActionEvent evt) throws IOException {
				newKit();
				dispose();
			}
		});
		panel.add(btnNewKit);

		JButton btnNewBullet = new JButton("New Bullet");
		btnNewBullet.setBounds(360, 100, 120, 100);
		btnNewBullet.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					btnNewBulletActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			private void btnNewBulletActionPerformed(ActionEvent evt) throws IOException {
				newBullet();
				dispose();
			}
		});
		panel.add(btnNewBullet);
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.setBounds(120, 100, 240, 100);
		btnCalculate.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					btnCalculateActionPerformed(evt);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			private void btnCalculateActionPerformed(ActionEvent evt) throws FileNotFoundException {
                // TODO Auto-generated method stub
                Kit k = new Kit();
                k.updateLists();
                k.setSpecs(kitChoice);
                Bullet b = new Bullet();
                b.updateLists();
                b.setSpecs(bulletChoice);
                calculate(k,b);
            }
		});
		panel.add(btnCalculate);

		// Combo Boxes for Kit and Bullet
		// Grabs information from the string array
		JComboBox cboKit = new JComboBox(kitArray);
		cboKit.setSelectedIndex(0);
		cboKit.setBounds(80, 0, 380, 50);

		cboKit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// cboKitActionPerformed(evt);
				// creates a new kit so methods can be used
				Kit kitHolder = new Kit();
				try {
					// updates the variables that will fill the
					kitHolder.updateLists();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// stores the choice of kit to use with ArrayLists
				kitChoice = cboKit.getSelectedIndex();
			}
		});
		panel.add(cboKit);

		JComboBox cboBullet = new JComboBox(bulletArray);
		cboBullet.setSelectedIndex(0);
		cboBullet.setBounds(80, 50, 380, 50);
		cboBullet.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// cboBulletActionPerformed(evt);

				// creates new bullet to use methods
				Bullet bulletHolder = new Bullet();
				try {
					// updates the lists so the information is accurate
					bulletHolder.updateLists();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// stores the choice of bullet to use with ArrayLists
				bulletChoice = cboBullet.getSelectedIndex();
			}
		});
		panel.add(cboBullet);

		setVisible(true);
	}

	// moved stuff from main to a new method that can be used when the calculate button is pressed.
	public void calculate(Kit kit, Bullet bullet) {
		double td = 0; // Time to reach a designated distance
		double s = 0; // Bullet Drop
		double m = 0; // Angle of barrel mom/moa/mil UNUSED
		double g = 9.81; // Gravity coefficient
		int[] Distances = { 25, 50, 75, 100, 150, 200, 300, 400, 500, 1000 };
		DecimalFormat df = new DecimalFormat("0.00");
		
		String[] calcinfo = new String[4];
		calcinfo[0] = "The Muzzle Energy of " + bullet.getName() + " is " + df.format(bullet.muzzleEnergy()) + " ft-lbs";
		calcinfo[1] = "\nYou are shooting out of a " + kit.getName() + "";
		calcinfo[2] = "\nZeroed at " + kit.getZero() + " Yards";
		calcinfo[3] = "\nWith a Scope Offset of " + kit.getOffset() + " Inches";

		/* (Distance/(Velocity/3)) = Time to reach distance */
		String printlns = "";
		for (int j = 0; j < Distances.length; j++) {
			td = (Distances[j] / (bullet.getVelocity() / 3));
			s = (m * td) + (g / 2) * (td * td);
			printlns = printlns + "\nBullet Drop at " + Distances[j] + " Yards: " + df.format(s) + " Inches";
		}
		
		JOptionPane.showMessageDialog(null, calcinfo[0] + calcinfo[1] + calcinfo[2] + calcinfo[3] + printlns, "Calculations", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void newKit() throws IOException {
        String kn; //kit name
        String kz; //kit zero distance
        String ks; //kit scope offset

       kn = JOptionPane.showInputDialog("What is the name of the kit?");

       kz = JOptionPane.showInputDialog("What is the zero distance?");
       int kz2 = Integer.parseInt(kz);

       ks = JOptionPane.showInputDialog("What is the scope offset?");
       Double ks2 = Double.parseDouble(ks);

       Kit k = new Kit(ks2, kz2, kn);
       k.updateLists();
       main(null);
	}

	public void newBullet() throws IOException {
        String bn; //bullet name
        String bv; //bullet velocity
        String bm; //bullet mass

       bn = JOptionPane.showInputDialog("What is the name of the bullet?");

       bv = JOptionPane.showInputDialog("What is the velocity of the bullet?");
       Double bv2 = Double.parseDouble(bv);

       bm = JOptionPane.showInputDialog("What is the mass of the bullet?");
       Double bm2 = Double.parseDouble(bm);

       Bullet b = new Bullet(bm2, bv2, bn);
       b.updateLists();
       main(null);
	}
	
	public static void main(String[] args) throws IOException {

		/* Find another way to store this information */
		Bullet b = new Bullet();
		Kit k = new Kit();
		b.updateLists();
		k.updateLists();
		bulletNames = b.getNames();
		kitNames = k.getNames();
		new test();
	}
}
