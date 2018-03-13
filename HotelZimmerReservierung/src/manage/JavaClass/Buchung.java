package manage.JavaClass;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Buchung {
	
	String buchungsnummer;
	int position;
	String bdatum;
	String status;
	String zeit_von;
	String zeit_bis;
	String ziD;
	
	int zahlE,zahlD,zahlS;
	
	Zimmer zimmer;
	ArrayList<Zimmer> zListFromBuchung = new ArrayList<Zimmer>();
	
	int nummerZahl = 0;
//	Warenkorb wk;
//	Vector warenkorbList = wk.getZimmerVector();
	
	public Buchung() {		
		super();
		this.buchungsnummer = "";
		this.position = 0;
		this.status = "";
		this.bdatum = "";
		this.ziD = "";
		this.zeit_von = "";
		this.zeit_bis = "";
	}
	public Buchung(String buchungsnummer, int position, String status, String bdatum, String ziD) {
		super();
		this.buchungsnummer = buchungsnummer;
		this.position = position;
		this.status = status;
		this.bdatum = bdatum;
		this.ziD = ziD;
	}
	public String toStringTeilBuchungsnummer(String vorname, String nachname) {
		String teilBuchungsnummer = "";
		String ersteBuchstabeVorname = vorname.valueOf(vorname.charAt(0)); 
		String ersteBuchstabeNachname = nachname.valueOf(nachname.charAt(0));
		String combinedLetters = ersteBuchstabeVorname + ersteBuchstabeNachname;
		teilBuchungsnummer = "BCH" + combinedLetters;
		return teilBuchungsnummer;
	}
	
	public ArrayList<Zimmer> getZimmerLIstFromBuchung(ArrayList<Zimmer> bzimmer) {
		Iterator<Zimmer> iter = bzimmer.iterator();
		while (iter.hasNext()) {
			Zimmer zimmer = (Zimmer) iter.next();
			zListFromBuchung.add(zimmer);
		}
		return zListFromBuchung;
	}
	// Methode, die die Anzahl Übernachtungen ermittelt
	public int anzahlUebernachtung(String von, String bis) throws ParseException{			
		int diffDays = 0;
		try {
			DateFormat dfvon = new SimpleDateFormat("dd.mm.yyyy");
			DateFormat dfbis = new SimpleDateFormat("dd.mm.yyyy");
			Date dvon = dfvon.parse(von);
			Date dbis = dfbis.parse(bis);
			long diffMillis = dbis.getTime() - dvon.getTime();
			diffDays = (int) Math.round(diffMillis / (1000. * 60. * 60. * 24.));
				System.out.println(diffDays);
			} catch (ParseException ex) {
				ex.printStackTrace();
		}
		return diffDays;
	}
	public int getPosition() {
		return position;
	}
	public String getZeit_von() {
		return zeit_von;
	}
	public void setZeit_von(String zeit_von) {
		this.zeit_von = zeit_von;
	}
	public String getZeit_bis() {
		return zeit_bis;
	}
	public void setZeit_bis(String zeit_bis) {
		this.zeit_bis = zeit_bis;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Zimmer getZimmer() {
		return zimmer;
	}
	public void setZimmer(Zimmer zimmer) {
		this.zimmer = zimmer;
	}
	public String getZiD() {
		return ziD;
	}
	public void setZiD(String ziD) {
		this.ziD = ziD;
	}
	public String getBuchungsnummer() {
		return buchungsnummer;
	}
	public void setBuchungsnummer(String buchungsnummer) {
		this.buchungsnummer = buchungsnummer;
	}
	public String getBdatum() {
		return bdatum;
	}
	public void setBdatum(String bdatum) {
		this.bdatum = bdatum;
	}
	public int getZahlE() {
		return zahlE;
	}
	public void setZahlE(int zahlE) {
		this.zahlE = zahlE;
	}
	public int getZahlD() {
		return zahlD;
	}
	public void setZahlD(int zahlD) {
		this.zahlD = zahlD;
	}
	public int getZahlS() {
		return zahlS;
	}
	public void setZahlS(int zahlS) {
		this.zahlS = zahlS;
	}
	public ArrayList<Zimmer> getzListFromBuchung() {
		return zListFromBuchung;
	}
	public void setzListFromBuchung(ArrayList<Zimmer> zListFromBuchung) {
		this.zListFromBuchung = zListFromBuchung;
	}
	
}
