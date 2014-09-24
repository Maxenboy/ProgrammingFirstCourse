import se.lth.cs.ptdc.window.SimpleWindow;

public class TurtleDrawSquare {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(600, 600, "TurtleDrawSquare");
		Turtle t = new Turtle(w, 300, 300);
		t.penDown();
		for (int i = 0; i < 4; i++) {
			System.out.println("x=" + t.getX() + "y=" + t.getY());
			System.out.println("grader=" + t.getDirection());
			t.forward(100);
			SimpleWindow.delay(1000);
			t.left(90);
		}
	}
}
