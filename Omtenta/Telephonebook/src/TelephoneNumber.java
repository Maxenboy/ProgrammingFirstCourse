
public class TelephoneNumber {
	private String countrycode;
	private String nbr;

	public TelephoneNumber(String countrycode, String nbr) {
		if (countrycode.length() == 0){
			this.countrycode = "0046";
		}else{
			this.countrycode = countrycode;
		}
		this.nbr = nbr;
	}

	public String getCode() {
		return countrycode;
	}

	public String getNbr() {
		return nbr;
	}
	
	@Override 
	public String toString(){
		return countrycode + nbr;
	}
}
