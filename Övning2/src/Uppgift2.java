import java.util.Scanner;

public class Uppgift2 {
	public static void main(String[] args) {
		System.out.println("Skriv Kön (man=1, kvinna= vilken siffra som)");
		System.out.println("Skriv vikt");
		System.out.println("Skriv längd");
		Scanner scan = new Scanner(System.in);
		int sex = scan.nextInt();
		int weight = scan.nextInt();
		int height = scan.nextInt();
		int normWeight = 0;
		int status = 0;
		if (sex == 1) {
			normWeight = height - 110;
			if (weight < 0.9 * normWeight) {
				status = 1;
			} else if (weight > 1.05 * normWeight) {
				status = 3;
			} else {
				status = 2;
			}
		} else {
			if (weight < 0.95 * normWeight) {
				status = 1;
			} else if (weight > 1.05 * normWeight) {
				status = 3;
			} else {
				status = 2;
			}
		}
		switch (status) {
		case 1:
			System.out.println("Du är undernärd, ät en hamburgare");
			break;
		case 2:
			System.out.println("Du är helt normal, keep up the goodwork");
			break;
		case 3:
			System.out.println("Du är överviktig, bums till gymmet");
			break;
		}

	}
}