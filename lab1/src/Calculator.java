import java.util.Scanner;

public class Calculator {
	public static void main(String[] args) {
		System.out.println("Skriv två tal");
		Scanner scan = new Scanner(System.in);
		double nbr1 = scan.nextDouble();
		double nbr2 = scan.nextDouble();
		double sum = 0;
		double kvot = 0;
		double prod = 0;
		double skill = 0;
		if (nbr1 > nbr2) {
			sum = nbr1 + nbr2;
			skill = nbr1 - nbr2;
			prod = nbr1 * nbr2;
			kvot = nbr1 / nbr2;
		} else {
			sum = nbr2 + nbr1;
			skill = nbr2 - nbr1;
			prod = nbr2 * nbr1;
			kvot = nbr2 / nbr1;
		}
		System.out.println("Summan av talen är " + sum);
		System.out.println("Skillnaden mellan talen är " + skill);
		System.out.println("Produkten av talen är " + prod);
		System.out.println("Kvoten av talen är " + kvot);
	}
}
