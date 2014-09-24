import java.util.Scanner;

public class Uppgift1 {
	public static void main(String[] args) {
		int antal = 1;
		Scanner scan = new Scanner(System.in);
		int scan1 = scan.nextInt();
		while (scan1 > 1) {
			if (scan1 % 2 == 0) {
				scan1 = scan1 / 2;
				antal++;
				System.out.print(scan1 + ", ");
			} else {
				scan1 = scan1 * 3 + 1;
				antal++;
				System.out.print(scan1 + ", ");
			}
			
		}
		System.out.print("(" + antal + " " + "tal" + ")");
	}
}
