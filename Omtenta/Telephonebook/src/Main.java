import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Contactlist c = new Contactlist();
		Scanner scan = new Scanner(System.in);
		do {
			System.out.println("Skriv in namn");
			String name = scan.next();
			System.out.println("Skriv in personnummer");
			String pnbr = scan.next();
			System.out.println("Skriv in telefonnummer");
			String tel = scan.next();
			System.out.println("Skriv in mail");
			String mail = scan.next();
			c.addPerson(name, pnbr, tel, mail);
		} while (!scan.next().equals("e"));
		c.print();
		c.removePerson("plummen");
		c.print();
		c.changeName("max", "maxaberg");
		c.print();
		c.addTelNbr("0705616788", "max");
		c.print();
		c.removeTelNbr("0705616788", "maxaberg");
		c.print();
		c.changeMail("max_aaberg@hotmail.com","max");
		c.print();
		c.changeMail("buren@gmail","buren");
		c.print();
	}
}
