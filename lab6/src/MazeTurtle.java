import se.lth.cs.ptdc.maze.Maze;
import se.lth.cs.ptdc.window.SimpleWindow;

public class MazeTurtle extends Turtle {

	public MazeTurtle(SimpleWindow w, int x, int y) {
		super(w, x, y);
		// TODO Auto-generated constructor stub
	}

	public void walk(Maze maze) {
		x = maze.getXEntry();
		y = maze.getYEntry();
		if (maze.wallAtLeft(getDirection(), (int) x, (int) y) == false
				&& maze.wallInFront(getDirection(), (int) x, (int) y) == false) {
			grad += 90;
		} else if (maze.wallAtLeft(getDirection(), (int) x, (int) y) == true
				&& maze.wallInFront(getDirection(), (int) x, (int) y) == true) {
			grad -= 90;
		}
	}

}
