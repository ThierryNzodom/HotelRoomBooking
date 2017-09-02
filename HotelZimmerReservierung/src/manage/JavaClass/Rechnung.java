package manage.JavaClass;

import java.util.ArrayList;

public class Rechnung {
	
	String rechnungsnummer;
	ArrayList<Buchung> rbnummer;
	double gesamtrechnung;
	String rechnungChar = "RCH00";
	int rechnungZahl = 0;
	
	public Rechnung(String rechnungsnummer, double gesamtrechnung) {
		super();
		this.rechnungsnummer = rechnungsnummer;
		this.gesamtrechnung = gesamtrechnung;
	}
	public Rechnung() {
		super();
		rechnungZahl++;
	}
	public String getRechnungsnummer() {		
		rechnungsnummer = rechnungChar + rechnungZahl;
		return rechnungsnummer;
	}
	public void setRechnungsnummer(String rechnungsnummer) {
		this.rechnungsnummer = rechnungsnummer;
	}
	public double getGesamtrechnung() {
		return gesamtrechnung;
	}
	public void setGesamtrechnung(double gesamtrechnung) {
		this.gesamtrechnung = gesamtrechnung;
	}
	
	
}
