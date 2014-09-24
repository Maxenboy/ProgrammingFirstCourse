import java.util.Scanner;

import se.lth.cs.ptdc.maze.Maze;
import se.lth.cs.ptdc.window.SimpleWindow;

public class MainMazeTurtle {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		SimpleWindow w = new SimpleWindow(600, 600, "");
		System.out.println("skriva labyrintnummer");
		w.waitForEvent();
		Maze m1 = new Maze(scan.nextInt());
		MazeTurtle t = new MazeTurtle(w, m1.getXEntry(), m1.getYEntry());
		while (m1.atExit(t.getX(), t.getY())) {
			t.walk(m1);
		}
	}
}
