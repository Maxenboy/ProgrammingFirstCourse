import se.lth.cs.ptdc.window.SimpleWindow;

public class Uppgift4 {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(500, 500, "Draw");
		while (true) {
			w.waitForMouseClick();
			int newX = w.getMouseX();
			int newY = w.getMouseY();
			w.lineTo(newX, newY);
		}
	}

}
