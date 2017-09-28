package manage.JavaClass;

import java.util.Date;

public class Kunde extends User {

	String titel;
	String vorname;
	String nachname;
	int telnummer;
	String strasse;
	String plz;
	String ort;
	Date gdatum;
	
	public boolean istEingeloggt() {
		return false;
	}
}
