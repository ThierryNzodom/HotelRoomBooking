package manage.JavaClass;

public class Person {
	
	String titel;
	String vorname;
	String nachname;
	String strasse;
	String plz;
	String ort;
	
	public Person() {
		super();
	}
	
	public Person(String titel, String vorname, String nachname,
			String strasse, String plz, String ort) {
		super();
		this.titel = titel;
		this.vorname = vorname;
		this.nachname = nachname;
		this.strasse = strasse;
		this.plz = plz;
		this.ort = ort;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	@Override
	public String toString() {
		return "Person [titel=" + titel + ", vorname=" + vorname
				+ ", nachname=" + nachname + ", strasse=" + strasse + ", plz="
				+ plz + ", ort=" + ort + "]";
	}
	
}
