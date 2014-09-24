import java.util.Scanner;
import se.lth.cs.ptdc.square.Square;
import se.lth.cs.ptdc.window.SimpleWindow;

public class DrawManySquares {
	public static void main(String[] args) {
		System.out.println("Skriv förminskning");
		System.out.println("Skriv rotering");
		Scanner scan = new Scanner(System.in);
		int dim = scan.nextInt();
		int grad = scan.nextInt();
		SimpleWindow w = new SimpleWindow(600, 600, "DrawManySquares");
		Square sq = new Square(300, 300, 200);
		while (sq.getSide() > 0) {
			sq.draw(w);
			sq.rotate(grad);
			sq.setSide(sq.getSide() - dim);
		}
	}
}
