import java.util.Scanner;

public class Uppgift3a {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int starth = scan.nextInt() * 60;
		int startm = scan.nextInt();
		int sluth = scan.nextInt() * 60;
		int slutm = scan.nextInt();
		System.out.println((sluth - starth) + (slutm - startm));
	}
}
