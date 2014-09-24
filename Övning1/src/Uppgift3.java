import java.util.Scanner;

public class Uppgift3 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Skriv en starttid och därefter en sluttid");
		int t1 = scan.nextInt();
		int m1 = scan.nextInt();
		int t2 = scan.nextInt();
		int m2 = scan.nextInt();
		int tot = (t2 - t1) * 60 + (m2 - m1);
		if (tot >= 60) {
			System.out.println("Tidsskillnad:" + " " + tot / 60 + "h" + " "
					+ tot % 60 + " " + "min");
		} else {
			System.out.println("Tidsskillnad:" + " " + tot % 60 + " " + "min");
		}
		if ((t1 < 0 || t1 > 24) || (t2 < 0 || t2 > 24) || (m1 > 0 || m1 > 60)
				|| (m2 < 0 || m2 > 60)) {
			System.out.println("Tiden är ologiskt inskriven");
		}
	}
}
