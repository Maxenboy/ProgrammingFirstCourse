import java.util.Scanner;

public class Uppgift312 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("hej");
		int nbr1 = scan.nextInt();
		int smaller = 0;
		int bigger = 0;
		for (int i = 0; i < 10; i++) {
			int nbr2 = scan.nextInt();
			if (nbr1 > nbr2) {
				smaller++;
			} else if (nbr1 < nbr2) {
				bigger++;
			}
		}
		System.out.println(bigger + " av talen var större än första talet"
				+ "\n" + smaller + " av talen var mindre än första talet.");
	}
}
