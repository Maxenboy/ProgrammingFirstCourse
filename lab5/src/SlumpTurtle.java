import java.util.Random;

import se.lth.cs.ptdc.window.SimpleWindow;

public class SlumpTurtle {
	public static void main(String[] args) {
		SimpleWindow w =new SimpleWindow(600,600,"");
		Turtle t = new Turtle(w,300,300);
		Random rand = new Random();
		for(int i =0;i<1000;i++){
			t.forward(1+rand.nextInt(10));
			SimpleWindow.delay(100);
			t.left(-180+360*rand.nextDouble());
		}
	}
}
