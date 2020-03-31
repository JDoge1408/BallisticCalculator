import java.math.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

//S = VoT + 1/2 A ( T x T )

public class test {
	public static void main(String[] args) {
		double td = 0; //Time to reach a designated distance
		double s = 0; //Bullet Drop
		double m = 0; //Angle of barrel mom/moa/mil
		double g = 9.81; //Gravity coefficient
		int[] Distances = {25, 50, 75, 100, 150, 200, 300, 400, 500, 1000};
		String[] Calibers = {"7.62x39", "7.62x51/.308", "22lr", "17hmr"}; //List of possible calibers
		DecimalFormat df = new DecimalFormat("0.00");
		
		/* Find another way to store this information */
		Bullet a308 = new Bullet(147, 2750, "Magtech M80 Ball");
		Kit kit1 = new Kit(2.1, 100, "Ruger American Predator");
		
		/*Give information about the gun and cartridge to ensure information is selected correctly*/
		System.out.println("The Muzzle Energy of " + a308.getName() + " is " + df.format(a308.muzzleEnergy()) + " ft-lbs");
		System.out.println("You are shooting out of a " + kit1.getName());
		System.out.println("Zeroed at " + kit1.getZero() + " Yards");
		System.out.println("With a Scope Offset of " + kit1.getOffset() + " Inches");
		
		int t = 1; //Counting 1 second at a time, for testing
  		double d = 0; //Distance traveled
 		s = (m*t) + (g/2)*(t*t);
		d = a308.getVelocity() * t;
		System.out.println("Bullet Drop after " + t + " Second: " + df.format(s) + " Inches");
		System.out.println("Bullet Distance after " + t + " Second: " + df.format(d/3) + " Yards");
		
		int i = 0;
		while (i < 5) {
			t++;
			s = (m*t) + (g/2)*(t*t);
			d = a308.getVelocity() * t;
			System.out.println("Bullet Drop after " + t + " Seconds: " + df.format(s) + " Inches");
			System.out.println("Bullet Distance after " + t + " Seconds: " + df.format(d/3) + " Yards");
			i++;
		}
		
		/* (Distance/(Velocity/3)) = Time to reach distance */
		for (int j=0; j<Distances.length; j++) {
			td = (Distances[j]/(a308.getVelocity()/3));
			s = (m*td) + (g/2)*(td*td);
			System.out.println("Bullet Drop at " + Distances[j] + " Yards: " + df.format(s) + " Inches");
		}
		
	}
}
