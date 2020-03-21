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
		DecimalFormat df = new DecimalFormat("0.00");
		
		/*Find another way to store information*/
		Bullet a9 = new Bullet(115, 1050, "Sig Elite Ball, 365 FMJ");
		Bullet a308 = new Bullet(147, 2750, "Magtech M80 Ball");
		Kit kit1 = new Kit(2.1, 100, "Ruger American Predator");
		Kit kit2 = new Kit(0, 50, "M&P Shield 2");
		
		/*Give information about the gun and cartridge to ensure information is selected correctly*/
		System.out.println("The Muzzle Energy of " + a9.getName() + " is " + df.format(a9.muzzleEnergy()) + " ft-lbs");
		System.out.println("You are shooting out of a " + kit2.getName());
		System.out.println("Zeroed at " + kit2.getZero() + " Yards");
		System.out.println("With a Scope Offset of " + kit2.getOffset() + " Inches");
		
		int t = 1; //Counting 1 second at a time, for testing
  		double d = 0; //Distance traveled
 		s = (m*t) + (g/2)*(t*t);
		d = a9.getVelocity() * t;
		System.out.println("Bullet Drop after " + t + " Second: " + df.format(s) + " Inches");
		System.out.println("Bullet Distance after " + t + " Second: " + df.format(d/3) + " Yards");
		
		int i = 0;
		while (i < 5) {
			t++;
			s = (m*t) + (g/2)*(t*t);
			d = a9.getVelocity() * t;
			System.out.println("Bullet Drop after " + t + " Seconds: " + df.format(s) + " Inches");
			System.out.println("Bullet Distance after " + t + " Seconds: " + df.format(d/3) + " Yards");
			i++;
		}
		
		/* (Distance/(Velocity/3)) = Time to reach distance */
		for (int j=0; j<Distances.length; j++) {
			td = (Distances[j]/(a9.getVelocity()/3));
			s = (m*td) + (g/2)*(td*td);
			System.out.println("Bullet Drop at " + Distances[j] + " Yards: " + df.format(s) + " Inches");
		}
		
	}
}