import se.lth.cs.ptdc.square.Square;
import se.lth.cs.ptdc.window.SimpleWindow;

public class ManySquares {
	public static void main(String[] args) {
		Square sq = new Square(100, 100, 100);
		SimpleWindow w = new SimpleWindow(1100, 1100, "ManySquares");
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 10; j++) {
				sq.draw(w);
				sq.move(100, 0);
			}
			sq.move(-1000, 100);
		}
	}

}
