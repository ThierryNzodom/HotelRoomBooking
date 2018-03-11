package manage.JavaClass;

public class Kunde {

	int knr;
	String anrede;
	String vorname;
	String nachname;
	String gdatum;
	String telnummer;
	String email;
	String adresse;
	
	
	public Kunde() {
		super();
	}
	public Kunde(int knr, String anrede, String vorname, String nachname, String gdatum, String telnummer, String adresse) {
		super();
		this.knr = knr;
		this.anrede = anrede;
		this.vorname = vorname;
		this.nachname = nachname;
		this.gdatum = gdatum;
		this.telnummer = telnummer;
		this.adresse = adresse;
	}
	public int getKnr() {
		return knr;
	}
	public void setKnr(int knr) {
		this.knr = knr;
	}
	public String getAnrede() {
		return anrede;
	}
	public void setAnrede(String anrede) {
		this.anrede = anrede;
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
	public String getGdatum() {
		return gdatum;
	}
	public void setGdatum(String gdatum) {
		this.gdatum = gdatum;
	}
	public String getTelnummer() {
		return telnummer;
	}
	public void setTelnummer(String telnummer) {
		this.telnummer = telnummer;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	@Override
	public String toString() {
		return "Kunde [knr=" + knr + ", anrede=" + anrede + ", vorname=" + vorname + ", nachname=" + nachname
				+ ", gdatum=" + gdatum + ", telnummer=" + telnummer + ", adresse=" + adresse + "]";
	}
	

}
