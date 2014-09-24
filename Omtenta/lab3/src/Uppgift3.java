import se.lth.cs.ptdc.square.Square;
import se.lth.cs.ptdc.window.SimpleWindow;

public class Uppgift3 {

	public static void main(String[] args) {
		Square sq = new Square(50, 50, 50);
		SimpleWindow w = new SimpleWindow(300, 300, "MoveSquare");
		sq.draw(w);
		while (true) {
			w.waitForMouseClick();
			int newX = w.getMouseX() - sq.getX();
			int newY = w.getMouseY() - sq.getY();
			for (int i = 0; i < 10; i++) {
				int partX = newX / 10;
				int partY = newY / 10;
				sq.move(partX, partY);
				sq.draw(w);
			}

		}
	}

}
