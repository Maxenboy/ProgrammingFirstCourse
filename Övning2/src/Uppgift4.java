import se.lth.cs.ptdc.window.SimpleWindow;

public class Uppgift4 {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(500, 500, "Click");
		while (true) {
			w.waitForMouseClick();
			w.lineTo(w.getMouseX(), w.getMouseY());
		}
	}
}
