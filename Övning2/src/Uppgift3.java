import se.lth.cs.ptdc.square.Square;
import se.lth.cs.ptdc.window.SimpleWindow;

public class Uppgift3 {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(5000, 5000, "ManySquares");
		Square sq = new Square(100, 100, 100);
		int i = 0;
		for (i = 0; i < 5; i++) {
			for (int j = 0; j < 10; j++) {
				sq.draw(w);
				sq.move(100, 0);
			}
			sq.move(-1000, 100);
		}
	}

}
