package manage.JavaBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import manage.JavaClass.Kunde;
import xdataBaseConnection.IOManager;
import xdataBaseConnection.NoConnectionException;

public class KundenBean {

	Kunde kunde;
	double auftragsumme;
	boolean loggedIn;
	Connection dbConn;
	
	//Insert Kunde in der DB
	public KundenBean() throws ClassNotFoundException, SQLException, NoConnectionException{
		dbConn = new IOManager().getConnection();
		
		auftragsumme = 0;
		this.loggedIn = false;		
	}
	
	public int insertKundeNoCheck() throws SQLException, NoConnectionException, ClassNotFoundException {
		// Feldlängen testen
		dbConn = new IOManager().getConnection();
		
		if(dbConn != null){
		String sql = "INSERT INTO KUNDE " +
				"(ANREDE,VORNAME,NACHNAME,GDATUM,TELNUMMER,ADRESSE,AUFTRAGSUMME) " +
				"VALUES " +
				"(?,?,?,?,?,?,?)";
		System.out.println(sql);
		
		try {	
		PreparedStatement prepStat = dbConn.prepareStatement(sql);
		prepStat.setString(1, kunde.getAnrede());
		prepStat.setString(2, kunde.getVorname());
		prepStat.setString(3, kunde.getNachname());
		prepStat.setString(4, kunde.getGdatum());
		prepStat.setString(5, kunde.getTelnummer());
		prepStat.setString(6, kunde.getAdresse());
		prepStat.setDouble(7, this.auftragsumme);
		prepStat.executeUpdate();
		System.out.println("Kunde Email: " + kunde.getEmail() + " erfolgreich eingefügt!");
		return 0; //Alles gut gelaufen!
		
		} catch (SQLException e) {
			e.printStackTrace();
			int rc = e.getErrorCode();
			if(rc <0){
				return rc;
			}else{
				return 99;
			}
		}
	}else{
		return 98; //DB Fehler!
	}		
	}
	// Update Kunde in der DB
	public void updateKunde() throws SQLException, ClassNotFoundException{
		Connection dbConn = new IOManager().getConnection();
		String sql = "UPDATE KUNDE "	+
					"SET TITEL = ?, " 	+
					"SET VORNAME = ? " 		+
					"SET NACHNAME = ? " 	+
					"SET GDATUM = ?, " 	+
					"SET TELNUMMER= ? " 		+
					"SET EMAIL = ? " 	+
					"SET ADRESSE = ? " 	+
					"SET AUFTRAGSUMME = ? " 	+
					"WHERE EMAIL = ? ";
		System.out.println(sql);
//		Connection dbConn = new IOManager().getConnection();
		PreparedStatement prepStat = dbConn.prepareStatement(sql);
		prepStat.setString(1, kunde.getAnrede());
		prepStat.setString(2, kunde.getVorname());
		prepStat.setString(3, kunde.getNachname());
		prepStat.setString(4, kunde.getGdatum());
		prepStat.setString(5, kunde.getTelnummer());
		prepStat.setString(6, kunde.getEmail());
		prepStat.setString(7, kunde.getAdresse());
		prepStat.setDouble(8, this.auftragsumme);		
		prepStat.executeUpdate();
		System.out.println("Kunde wird erfolgreich geändert.");
	}
	// Delete Kunde in der DB
	public void buchungStornieren() throws SQLException, ClassNotFoundException{
		Connection dbConn = new IOManager().getConnection();
		String sql = "delete from KUNDE WHERE EMAIL = ? ;";
		System.out.println(sql);
//		Connection dbConn = new IOManager().getConnection();
		PreparedStatement prep = dbConn.prepareStatement(sql);		
		prep.setString(1, kunde.getEmail());
		prep.executeUpdate();
		System.out.println("Kunde wird erfolgreich gelöscht.");
	}
	//Gibt die ZAhl notwendig für die Nächste KundenNummer
		public int getNummerZahl() throws ClassNotFoundException, SQLException{
			Connection dbConn = new IOManager().getConnection();
			int zahl = 0;
			if (dbConn != null){
				String sql = "SELECT KNR FROM KUNDE";			
				System.out.println(sql);
				Statement stmt = dbConn.createStatement();
				ResultSet dbRes = stmt.executeQuery(sql);
				System.out.println("SQL-Befehl erfolgreich ausgeführt");
				System.out.println();
			while(dbRes.next()){							
			String kundennummer = dbRes.getString("KNR");
			char c = kundennummer.charAt(0);
			String ersteChar = String.valueOf(c);
			zahl = Integer.valueOf(ersteChar);			
			}
		}
			System.out.println("Die Nummerzahl: " + zahl + " vom letzten Kunde wurde zurückgegeben.");
			return zahl;
	}
	public String toString(){
		String s = "Kunde mit ";
				s+= "Anrede: " + kunde.getAnrede();	
				s+= "Vorname: " + kunde.getVorname() + ", ";
				s+= "Nachname: " + kunde.getNachname() + ", ";
				s+= "Gdatum: " + kunde.getGdatum() + ", ";
				s+= "Telnummer: " + kunde.getTelnummer() + ", ";
				s+= "Email: " + kunde.getEmail() + ", ";
				s+= "Adresse: " + kunde.getAdresse() + ". ";
							
		return s;
	}
	
//	public String getActive() {
//		return active;
//	}
//	public void setActive(String active) {
//		this.active = active;
//	}
//	public String getAdmin() {
//		return admin;
//	}
//	public void setAdmin(String admin) {
//		this.admin = admin;
	
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public Kunde getKunde() {
		return kunde;
	}
	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}
	public double getAuftragsumme() {
		return auftragsumme;
	}
	public void setAuftragsumme(double auftragsumme) {
		this.auftragsumme = auftragsumme;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

}
