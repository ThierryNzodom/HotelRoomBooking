package manage.JavaClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import xdataBaseConnection.IOManager;

public class Buchung {
	String buchungsnummer;
	String buchungdatum;
	String produkte;
	String nummerChar = "BCH00";
	int nummerZahl = 0;
	Connection dbConn;
//	Warenkorb wk;
//	Vector warenkorbList = wk.getZimmerVector();
	
	public Buchung() throws ClassNotFoundException, SQLException {		
		super();
		dbConn = new IOManager().getConnection();
		nummerZahl++;
	}
	public Buchung(String buchungsnummer, String buchungdatum, String produkte) throws ClassNotFoundException, SQLException {
		super();
		dbConn = new IOManager().getConnection();
		nummerZahl++;
		this.buchungsnummer = buchungsnummer;
		this.buchungdatum = buchungdatum;
		this.produkte = produkte;
	}
	// Neue Buchung einfügen
	public void insertBuchung() throws SQLException, ClassNotFoundException{
		String sql = "INSERT INTO buchung" +
					"(BUCHUNGSNUMMER, BUCHUNGSDATUM, PRODUKTE)" +
					"VALUES" +
					"(?,?,?)";
		System.out.println(sql);
//		Connection dbConn = new IOManager().getConnection();
		PreparedStatement prep = dbConn.prepareStatement(sql);
		prep.setString(1, this.getBuchungsnummer());
		prep.setString(2, this.getBuchungdatum());
		prep.setString(3, this.getProdukte());
		prep.executeUpdate();
		System.out.println("Buchung erfolgreich eingefügt");
	}
	// Update Methode für die Umbuchung
	public void umbuchen() throws SQLException, ClassNotFoundException{
		String sql = "UPDATE buchung " 		+
					"SET BUCHUNGSDATUM = ?, " 	+
					"PRODUKTE = ? " 		+
					"WHERE BUCHUNGSNUMMER = ?;";
		System.out.println(sql);
//		Connection dbConn = new IOManager().getConnection();
		PreparedStatement prep = dbConn.prepareStatement(sql);
		prep.setString(1, this.buchungdatum);
		prep.setString(2, this.produkte);
		prep.setString(3, this.buchungsnummer);		
		prep.executeUpdate();
		System.out.println("Gebuchte Räume werden erfolgreich geändert.");
	}
	// delete Methode für die Stornierung
	public void stornieren() throws SQLException, ClassNotFoundException{
		String sql = "delete from buchung WHERE BUCHUNGSNUMMER = ?;";
		System.out.println(sql);
//		Connection dbConn = new IOManager().getConnection();
		PreparedStatement prep = dbConn.prepareStatement(sql);		
		prep.setString(1, this.buchungsnummer);		
		prep.executeUpdate();
		System.out.println("Gebuchte Räume werden erfolgreich Storniert.");
	}
	public String getProdukte() {
		return produkte;
	}
	public void setProdukte(String produkte) {
		this.produkte = produkte;
	}
	public String getProdukteFromLIst(ArrayList<Zimmer> bzimmer) {
		String products = "";
		Iterator<Zimmer> iter = bzimmer.iterator();
		while (iter.hasNext()) {
			Zimmer zimmer = (Zimmer) iter.next();
			String zid = zimmer.getzID();
			products += zid + ";";
		}
		produkte = products;
		return products;
	}
	public String getBuchungsnummer() {
		buchungsnummer = nummerChar + nummerZahl;
		return buchungsnummer;
	}
	public void setBuchungsnummer(String buchungsnummer) {
		this.buchungsnummer = buchungsnummer;
	}
	public String getBuchungdatum() {
		return buchungdatum;
	}
	public void setBuchungdatum(String buchungdatum) {
		this.buchungdatum = buchungdatum;
	}
}
