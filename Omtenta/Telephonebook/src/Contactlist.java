import java.util.ArrayList;

public class Contactlist {
	private ArrayList<Person> persons;
	private int id;

	public Contactlist() {
		this.persons = new ArrayList<Person>();
		id = 0;
	}

	public void addPerson(String name, String personNbr, String tel, String mail) {
		persons.add(new Person(id++, name, personNbr, tel, mail));
	}

	public void removePerson(String s) {
		for (int i = 0; i < persons.size(); i++) {
			if (s.equals(persons.get(i).getName())) {
				persons.remove(i);
			}
		}
	}

	public void changeName(String name, String newName) {
		for (int i = 0; i < persons.size(); i++) {
			if (name.equals(persons.get(i).getName())) {
				persons.get(i).setName(newName);
			}
		}
	}

	public void addTelNbr(String tel, String name) {
		for (int i = 0; i < persons.size(); i++) {
			if (name.equals(persons.get(i).getName())) {
				persons.get(i).addTelNbr(tel);
			}
		}
	}

	public void removeTelNbr(String tel, String name) {
		for (int i = 0; i < persons.size(); i++) {
			if (name.equals(persons.get(i).getName())) {
				persons.get(i).removeTelNbr(tel);
			}
		}
	}

	public ArrayList<String> searchName(String name) {
		ArrayList<String> names = new ArrayList<String>();
		for (int i = 0; i < persons.size(); i++) {
			if (name.equals(persons.get(i).getName())) {
				names.add(persons.get(i).getName());
			}
		}
		return names;
	}

	public void changeMail(String mail, String name) {
		for (int i = 0; i < persons.size(); i++) {
			if (name.equals(persons.get(i).getName())) {
				persons.get(i).changeMail(mail);
			}
		}
	}

	public void print() {
		for (int i = 0; i < persons.size(); i++)
			System.out.println(persons.get(i).toString());
		System.out.println();
	}

}
