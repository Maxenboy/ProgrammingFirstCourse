import java.awt.Color;
import java.util.Random;

import se.lth.cs.ptdc.window.SimpleWindow;

public class Randomstroll {
	public static void main(String[] args) {
		Random rand = new Random();
		SimpleWindow w = new SimpleWindow(1000, 1000, "Random");
		ColorTurtle t1 = new ColorTurtle(w, 350, 350, Color.black);
		ColorTurtle t2 = new ColorTurtle(w, 250, 250, Color.red);
		t1.penDown();
		t2.penDown();
		double distance = 100;
		while (distance >= 50) {
			t1.forward(1 + rand.nextInt(10));
			t1.left(-180 + rand.nextInt(360));
			t2.forward(1 + rand.nextInt(10));
			t2.left(-180 + rand.nextInt(360));
			distance = Math.sqrt(Math.pow((t2.getX() - t1.getX()), 2)
					+ Math.pow((t2.getY() - t1.getY()), 2));
		}
		System.out.println(distance);
	}
}
