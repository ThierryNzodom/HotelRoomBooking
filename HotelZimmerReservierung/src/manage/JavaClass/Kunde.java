package manage.JavaClass;

public class Kunde extends User {

	int rechnungsnummer;
	int buchungsnummer;
	int kundenID;
	
	public boolean istEingeloggt() {
		return false;
	}
}
