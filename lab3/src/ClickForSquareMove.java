import se.lth.cs.ptdc.square.Square;
import se.lth.cs.ptdc.window.SimpleWindow;

public class ClickForSquareMove {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(500, 500, "BAJS");
		Square sq = new Square(300, 300, 100);
		sq.draw(w);
		while (true) {
			int oldX = sq.getX();
			int oldY = sq.getY();
			w.waitForEvent();
			if (w.getEventType() == SimpleWindow.MOUSE_EVENT) {
				int newX = w.getMouseX();
				int newY = w.getMouseY();
				int distX = (newX - oldX) / 10;
				int distY = (newY - oldY) / 10;
				for (int i = 0; i < 10; i++) {
					sq.move(distX, distY);
					sq.draw(w);
				}

			} else {
				char ch = w.getKey();
				if (ch == 'r') {
					w.setLineColor(java.awt.Color.RED);
					int newX = w.getMouseX();
					int newY = w.getMouseY();
					int distX = (newX - oldX) / 10;
					int distY = (newY - oldY) / 10;
					for (int i = 0; i < 10; i++) {
						sq.move(distX, distY);
						sq.draw(w);
					}
				} else {
					w.setLineColor(java.awt.Color.BLACK);
					int newX = w.getMouseX();
					int newY = w.getMouseY();
					int distX = (newX - oldX) / 10;
					int distY = (newY - oldY) / 10;
					for (int i = 0; i < 10; i++) {
						sq.move(distX, distY);
						sq.draw(w);

					}
				}
			}
		}
	}
}
