import java.util.ArrayList;

public class Person {
	private String name;
	private String personNbr;
	private ArrayList<TelephoneNumber> tNbr;
	private String mail;
	private int id;

	public Person(int id, String name, String personNbr, String tel, String mail) {
		this.name = name;
		this.personNbr = personNbr;
		this.tNbr = new ArrayList<TelephoneNumber>();
		this.tNbr.add(new TelephoneNumber("", tel));
		this.mail = mail;
		this.id = id;
	}

	public Person(int id, String name, String personNbr) {
		this.id = id;
		this.name = name;
		this.personNbr = personNbr;
		this.tNbr = new ArrayList<TelephoneNumber>();
	}

	public String getName() {
		return name;
	}

	public String getPersonNbr() {
		return personNbr;
	}

	public String getMail() {
		if (mail != null) {
			return mail;
		} else {
			return "Denna person har ingen mail";
		}
	}

	public void setName(String s) {
		name = s;
	}

	public void addTelNbr(String s) {
		tNbr.add(new TelephoneNumber("", s));
	}

	public void removeTelNbr(String s) {
		if (!tNbr.isEmpty()) {
			for (int i = 0; i < tNbr.size(); i++) {
				if (s.equals(tNbr.get(i))) {
					tNbr.remove(i);
				}
			}
		}
	}

	public void changeMail(String m) {
		mail = m;
	}

	public String getNumber() {
		String s = "";
		for (int i = 0; i < tNbr.size(); i++) {
			s += tNbr.get(i).toString() + "\t";
		}
		return s;
	}
	
	public boolean equals(Person p){
		if (p.name.toLowerCase() == this.name.toLowerCase()){
			return true;
		}
		return false;
	}

	public int getID() {
		return id;
	}
	
	@Override
	public String toString(){
		String telStr = "";
		for (TelephoneNumber t : tNbr)
			telStr += t.toString() + "\t";
		return name + " " + personNbr+ " "  + mail+ " "  + telStr;
	}
	
	
}
