import java.util.Random;

import se.lth.cs.ptdc.window.SimpleWindow;

public class SlumpTurtles {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(600, 600, "");
		Turtle t1 = new Turtle(w, 350, 350);
		Turtle t2 = new Turtle(w, 250, 250);
		Random rand1 = new Random();
		Random rand2 = new Random();
		while (Math.hypot(t1.getX() - t2.getX(), t1.getY() + t2.getY()) >= 50) {
			t1.forward(1 + rand1.nextInt(10));
			t1.left(-180 + 360 * rand1.nextDouble());
			t2.forward(1 + rand2.nextInt(10));
			t2.left(-180 + 360 * rand2.nextDouble());

		}
		System.out.println(Math.hypot(t1.getX() - t2.getX(),
				t1.getY() + t2.getY()));
	}
}
