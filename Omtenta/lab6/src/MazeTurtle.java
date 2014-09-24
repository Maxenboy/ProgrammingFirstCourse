import se.lth.cs.ptdc.maze.Maze;
import se.lth.cs.ptdc.window.SimpleWindow;

public class MazeTurtle extends Turtle {

	public MazeTurtle(SimpleWindow w, int x, int y) {
		super(w, x, y);

	}

	public void walk(Maze maze) {
		x = maze.getXEntry();
		y = maze.getYEntry();
		while (!maze.atExit(x, y)) {
			if (maze.wallAtLeft(super.getDirection(), x, y)
					&& maze.wallInFront(super.getDirection(), x, y)) {
				direction -= 90;
				super.forward(1);
				SimpleWindow.delay(5);
			} else if (maze.wallAtLeft(super.getDirection(), x, y)) {
				super.forward(1);
				SimpleWindow.delay(5);
			} else {
				direction += 90;
				super.forward(1);
				SimpleWindow.delay(5);
			}
		}
	}
}
