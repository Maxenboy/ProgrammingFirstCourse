import se.lth.cs.ptdc.maze.Maze;
import se.lth.cs.ptdc.window.SimpleWindow;

public class TestMazeTurtle {
	public static void main(String[] args) {
		Maze maze = new Maze(3);
		SimpleWindow w = new SimpleWindow(600, 600, "Maze");
		MazeTurtle t = new MazeTurtle(w, maze.getXEntry(), maze.getYEntry());
		t.penDown();
		maze.draw(w);
		t.walk(maze);

	}
}
