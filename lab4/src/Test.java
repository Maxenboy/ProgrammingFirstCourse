import se.lth.cs.ptdc.window.SimpleWindow;

public class Test {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(600, 600, "TurtleDrawSquare");
		Turtle t = new Turtle(w, 300, 300);
		t.penDown();
		for (int i = 0; i < 2; i++) {
			t.left(45);
			t.forward(100);
			System.out.println(t.getX() + " " + t.getY());
			System.out.println(t.getDirection());
		}
	}
}
