import java.util.Scanner;

public class Calculator {
	public static void main(String[] args) {
		System.out.println("Skriv två tal");
		Scanner scan = new Scanner(System.in);
		double nbr1 = scan.nextDouble();
		double nbr2 = scan.nextDouble();
		double sum = nbr1 + nbr2;
		double diff = Math.abs(nbr1 - nbr2);
		double prod = nbr1 * nbr2;
		double kvot = nbr1 / nbr2;
		System.out.println("Summan av talen är " + sum + "\n"
				+ "Skillnaden mellan talen är:" + diff + "\n"
				+ "Produkten mellan talen är:" + prod + "\n"
				+ "Kvoten mellan talen är:" + kvot);
	}
}
