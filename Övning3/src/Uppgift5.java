import java.util.Scanner;

public class Uppgift5 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int betyg = scan.nextInt();
		int g = 0;
		int u = 0;
		int sum = 0;
		while (scan.hasNextInt()) {
			if (betyg == 0) {
				u++;
			} else {
				g++;
				sum += betyg;
			}
		}
		if (sum == 0) {
			System.out.println("Du är ej godkänd i något");
		} else {
			int tot = g + u;
			System.out.println("Antal betyg:" + tot);
			System.out.println("Antal underkända betyg:" + u);
			System.out.println("Medelbetyg:" + g / tot);
		}
	}
}
