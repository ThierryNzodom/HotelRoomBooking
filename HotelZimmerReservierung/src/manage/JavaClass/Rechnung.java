package manage.JavaClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import xdataBaseConnection.IOManager;


public class Rechnung {
	
	String rechnungsnummer;
	Buchung buchung;
	Kunde kunde;
	double gesamtpreis;
	
	public Rechnung() {
		super();
	}
	
	//Gibt die ZAhl notwendig für die Nächste Buchungsnummer
		public int getNummerZahlRechnung() throws ClassNotFoundException, SQLException{
			Connection dbConn = new IOManager().getConnection();
			int zahl = 0;
			if (dbConn != null){
				String sql = "SELECT RID FROM RECHNUNG ";			
				System.out.println(sql);
				Statement stmt = dbConn.createStatement();
				ResultSet dbRes = stmt.executeQuery(sql);
				System.out.println("SQL-Befehl erfolgreich ausgeführt");
				System.out.println();
			while(dbRes.next()){							
			String rechnungsnummer = dbRes.getString("RID");
			char c = rechnungsnummer.charAt(0);
			String ersteChar = String.valueOf(c);
			zahl = Integer.valueOf(ersteChar);
			}
		}					
			System.out.println("Die Nummerzahl: " + zahl + " wurde zurückgegeben.");
			return zahl;
		}
		public String toStringTeilRechungsnummer(String vorname, String nachname) {
			String teilRechnungsnummer = "";
			String ersteBuchstabeVorname = vorname.valueOf(vorname.charAt(0)); 
			String ersteBuchstabeNachname = nachname.valueOf(nachname.charAt(0));
			String combinedLetters = ersteBuchstabeVorname + ersteBuchstabeNachname;
			teilRechnungsnummer = "RCH" + combinedLetters;
			return teilRechnungsnummer;
		}
	// Neue Buchung einfügen
	public void insertRechnung() throws SQLException, ClassNotFoundException{
		Connection dbConn = new IOManager().getConnection();
		String sql = "INSERT INTO rechnung" +
					"(RID, BNR, KNR, GESAMTPREIS)" +
					"VALUES" +
					"(?,?,?,?)";
		System.out.println(sql);
		PreparedStatement prep = dbConn.prepareStatement(sql);
		prep.setString(1, this.getRechnungsnummer());
		prep.setString(2, buchung.getBuchungsnummer());
		prep.setInt(3, kunde.getKnr());
		prep.setDouble(4, this.gesamtpreis);
		prep.executeUpdate();
		System.out.println("Rechnung erfolgreich eingefügt");
	}
	public Buchung getBuchung() {
		return buchung;
	}
	public void setBuchung(Buchung buchung) {
		this.buchung = buchung;
	}
	public Kunde getKunde() {
		return kunde;
	}
	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}
	public String getRechnungsnummer() {		
		return rechnungsnummer;
	}
	public void setRechnungsnummer(String rechnungsnummer) {
		this.rechnungsnummer = rechnungsnummer;
	}
	public double getGesamtpreis() {
		return gesamtpreis;
	}
	public void setGesamtpreis(double gesamtpreis) {
		this.gesamtpreis = gesamtpreis;
	}	
	
}
