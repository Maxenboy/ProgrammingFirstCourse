import java.util.Scanner;

public class Uppgift1 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int count = 1;
		int a0 = scan.nextInt();
		while (a0 > 1) {
			if (a0 % 2 == 0) {
				a0 = a0 / 2;
				System.out.print(" " + a0 + ",");
			} else {
				a0 = 3 * a0 + 1;
				System.out.print(" " + a0 + ",");
			}
			count++;
		}
		System.out.print("(" + count + " tal i talföljden)");
	}

}
