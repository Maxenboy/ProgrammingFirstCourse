import java.util.Scanner;

public class Uppgift3b {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int starth = scan.nextInt() * 60;
		int startm = scan.nextInt();
		int sluth = scan.nextInt() * 60;
		int slutm = scan.nextInt();
		int tot = sluth - starth + (slutm - startm);
		int tim = tot / 60;
		int min = tot % 60;
		System.out.println(tim + ":" + min);
	}
}
