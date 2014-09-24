import java.awt.Color;

import se.lth.cs.ptdc.square.Square;
import se.lth.cs.ptdc.window.SimpleWindow;

public class Uppgift4 {
	public static void main(String[] args) {
		Square sq = new Square(50, 50, 50);
		SimpleWindow w = new SimpleWindow(300, 300, "MoveSquare");
		sq.draw(w);
		while (true) {
			w.waitForEvent();
			int newX = w.getMouseX() - sq.getX();
			int newY = w.getMouseY() - sq.getY();
			if (w.getEventType() != SimpleWindow.MOUSE_EVENT) {
				if (w.getKey() == 'r') {
					w.setLineColor(Color.red);
				} else {
					w.setLineColor(Color.black);
				}
			}
			sq.erase(w);
			sq.move(newX, newY);
			sq.draw(w);

		}
	}
}
