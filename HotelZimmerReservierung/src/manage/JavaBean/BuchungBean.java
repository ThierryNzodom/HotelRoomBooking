package manage.JavaBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import xdataBaseConnection.IOManager;
import manage.JavaClass.Buchung;
import manage.JavaClass.Zimmer;

public class BuchungBean {

	Buchung buchung;
	// Variabeln für Datumsangaben	
		Date dt = new Date();
		SimpleDateFormat df = new SimpleDateFormat( "dd.MM.yyyy" );
	//Liste der Belegung/zimmertyp
	ArrayList<Buchung> ebelegungList = new ArrayList<>();
	ArrayList<Buchung> dbelegungList = new ArrayList<>();
	ArrayList<Buchung> sbelegungList = new ArrayList<>();
	//Liste der pro zimmertyp
	ArrayList<Zimmer> einzelnZimmerList = new ArrayList<Zimmer>();
	ArrayList<Zimmer> doppelZimmerList = new ArrayList<Zimmer>();
	ArrayList<Zimmer> suiteList = new ArrayList<Zimmer>();
	//Liste der freien Zimmer
	ArrayList<Zimmer> listZimmerFrei = new ArrayList<Zimmer>();
	ArrayList<Zimmer> listDZimmerFrei = new ArrayList<Zimmer>();
	ArrayList<Zimmer> listSZimmerFrei = new ArrayList<Zimmer>();
	
	public ArrayList<Zimmer> getEZimmerFromDb() throws ClassNotFoundException, SQLException{
		Connection dbConn = new IOManager().getConnection();
		if (dbConn != null){
			String sql = "SELECT * FROM ZIMMER WHERE ZIMMERID LIKE \'CL%\'";			
			System.out.println(sql);
			Statement stmt = dbConn.createStatement();
			ResultSet dbRes = stmt.executeQuery(sql);
			System.out.println("SQL-Befehl erfolgreich ausgeführt");
			System.out.println();
		while(dbRes.next()){			
			Zimmer zimmer = new Zimmer();
			String zimmerid = dbRes.getString("ZIMMERID");
			String zimmertyp = dbRes.getString("ZIMMERTYP");
			String groesse = dbRes.getString("GROESSE");
			double preis = dbRes.getDouble("PREIS");
			
			//Zimmer Values füllen
			zimmer.setzID(zimmerid);
			zimmer.setZtyp(zimmertyp);
			zimmer.setGroesse(groesse);
			zimmer.setPreis(preis);
		
		einzelnZimmerList.add(zimmer);
		
		}
	}					
		System.out.println("Select from einzelnZimmerList erfolgreicht.");
		return einzelnZimmerList;
	}
	public ArrayList<Zimmer> getDZimmerFromDb() throws ClassNotFoundException, SQLException{
		Connection dbConn = new IOManager().getConnection();
		if (dbConn != null){
			String sql = "SELECT * FROM ZIMMER WHERE ZIMMERID LIKE \'SD%\'";			
			System.out.println(sql);
			Statement stmt = dbConn.createStatement();
			ResultSet dbRes = stmt.executeQuery(sql);
			System.out.println("SQL-Befehl erfolgreich ausgeführt");
			System.out.println();
		while(dbRes.next()){			
			Zimmer zimmer = new Zimmer();
			String zimmerid = dbRes.getString("ZIMMERID");
			String zimmertyp = dbRes.getString("ZIMMERTYP");
			String groesse = dbRes.getString("GROESSE");
			double preis = dbRes.getDouble("PREIS");
			
			//Zimmer Values füllen
			zimmer.setzID(zimmerid);
			zimmer.setZtyp(zimmertyp);
			zimmer.setGroesse(groesse);
			zimmer.setPreis(preis);
		
		doppelZimmerList.add(zimmer);
		
		}
	}					
		System.out.println("Select from doppelZimmerList erfolgreicht.");
		return doppelZimmerList;
	}
	public ArrayList<Zimmer> getSZimmerFromDb() throws ClassNotFoundException, SQLException{
		Connection dbConn = new IOManager().getConnection();
		if (dbConn != null){
			String sql = "SELECT * FROM ZIMMER WHERE ZIMMERID LIKE \'CD%\'";			
			System.out.println(sql);
			Statement stmt = dbConn.createStatement();
			ResultSet dbRes = stmt.executeQuery(sql);
			System.out.println("SQL-Befehl erfolgreich ausgeführt");
			System.out.println();
		while(dbRes.next()){			
			Zimmer zimmer = new Zimmer();
			String zimmerid = dbRes.getString("ZIMMERID");
			String zimmertyp = dbRes.getString("ZIMMERTYP");
			String groesse = dbRes.getString("GROESSE");
			double preis = dbRes.getDouble("PREIS");
			
			//Zimmer Values füllen
			zimmer.setzID(zimmerid);
			zimmer.setZtyp(zimmertyp);
			zimmer.setGroesse(groesse);
			zimmer.setPreis(preis);
		
		suiteList.add(zimmer);
		
		}
	}					
		System.out.println("Select from suiteList erfolgreicht.");
		return suiteList;
	}
	public ArrayList<Buchung> getEBelegungFromDb() throws ClassNotFoundException, SQLException{
		Connection dbConn = new IOManager().getConnection();
		if (dbConn != null){
			String sql = "SELECT BUCHUNGSNUMMER, ZIMMER_ID, ZEIT_VON, ZEIT_BIS FROM BUCHUNG "
					+ "WHERE ZIMMER_ID LIKE \'CL%\'";			
			System.out.println(sql);
			Statement stmt = dbConn.createStatement();
			ResultSet dbRes = stmt.executeQuery(sql);
			System.out.println("SQL-Befehl erfolgreich ausgeführt");
			System.out.println();
		while(dbRes.next()){							
		Buchung buchung = new Buchung();
		String buchungsnummer = dbRes.getString("BUCHUNGSNUMMER");
		String zimmerid = dbRes.getString("ZIMMER_ID");
		String zeit_von = dbRes.getString("ZEIT_VON");
		String zeit_bis = dbRes.getString("ZEIT_BIS");
		
		//Belegung befüllen
		buchung.setBuchungsnummer(buchungsnummer);
		buchung.setZiD(zimmerid);
		buchung.setZeit_von(zeit_von);
		buchung.setZeit_bis(zeit_bis);
		
		ebelegungList.add(buchung);
		
		}
	}					
		System.out.println("Select from Buchung für Einzelnzimmer erfolgreicht.");
		return ebelegungList;
	}
	public ArrayList<Buchung> getDBelegungFromDb() throws ClassNotFoundException, SQLException{
		Connection dbConn = new IOManager().getConnection();
		if (dbConn != null){
			String sql = "SELECT BUCHUNGSNUMMER, ZIMMER_ID, ZEIT_VON, ZEIT_BIS FROM BUCHUNG "
					+ "WHERE ZIMMER_ID LIKE \'SD%\'";			
			System.out.println(sql);
			Statement stmt = dbConn.createStatement();
			ResultSet dbRes = stmt.executeQuery(sql);
			System.out.println("SQL-Befehl erfolgreich ausgeführt");
			System.out.println();
		while(dbRes.next()){							
		Buchung buchung = new Buchung();
		String buchungsnummer = dbRes.getString("BUCHUNGSNUMMER");
		String zimmerid = dbRes.getString("ZIMMER_ID");
		String zeit_von = dbRes.getString("ZEIT_VON");
		String zeit_bis = dbRes.getString("ZEIT_BIS");
		
		//Belegung befüllen
		buchung.setBuchungsnummer(buchungsnummer);
		buchung.setZiD(zimmerid);
		buchung.setZeit_von(zeit_von);
		buchung.setZeit_bis(zeit_bis);
		
		dbelegungList.add(buchung);
		
		}
	}					
		System.out.println("Select from Zimmerbelegung für doppelzimmer erfolgreicht.");
		return dbelegungList;
	}
	public ArrayList<Buchung> getSBelegungFromDb() throws ClassNotFoundException, SQLException{
		Connection dbConn = new IOManager().getConnection();
		if (dbConn != null){
			String sql = "SELECT BUCHUNGSNUMMER, ZIMMER_ID, ZEIT_VON, ZEIT_BIS FROM BUCHUNG "
					+ "WHERE ZIMMER_ID LIKE \'CD%\'";			
			System.out.println(sql);
			Statement stmt = dbConn.createStatement();
			ResultSet dbRes = stmt.executeQuery(sql);
			System.out.println("SQL-Befehl erfolgreich ausgeführt");
			System.out.println();
		while(dbRes.next()){							
		Buchung buchung = new Buchung();
		String buchungsnummer = dbRes.getString("BUCHUNGSNUMMER");
		String zimmerid = dbRes.getString("ZIMMER_ID");
		String zeit_von = dbRes.getString("ZEIT_VON");
		String zeit_bis = dbRes.getString("ZEIT_BIS");
		
		//Belegung befüllen
		buchung.setBuchungsnummer(buchungsnummer);
		buchung.setZiD(zimmerid);
		buchung.setZeit_von(zeit_von);
		buchung.setZeit_bis(zeit_bis);
		
		sbelegungList.add(buchung);
		
		}
	}					
		System.out.println("Select from Buchung für suite erfolgreicht.");
		return sbelegungList;
	}
	
	public ArrayList<Zimmer> zimmerfrei (ArrayList<Zimmer> einzelnZimmerList, ArrayList<Buchung> ebelegungList)throws ParseException, java.text.ParseException{
		ArrayList<Zimmer> toRemoveZimmer = new ArrayList<Zimmer>();
		String userdate_von = buchung.getZeit_von();
		String userdate_bis = buchung.getZeit_bis();
		String dbdate_von;
		String dbdate_bis;
		Date udtv = new Date();
		Date udtb = new Date();
		Date dtv = new Date();
		Date dtb = new Date();
		SimpleDateFormat dfuv = new SimpleDateFormat("dd.MM.yyyy");
		SimpleDateFormat dfub = new SimpleDateFormat("dd.MM.yyyy");
		SimpleDateFormat dfv = new SimpleDateFormat("dd.MM.yyyy");
		SimpleDateFormat dfb = new SimpleDateFormat("dd.MM.yyyy");
		listZimmerFrei = einzelnZimmerList;
		
		for (Zimmer zimmer : einzelnZimmerList) {
			for (Buchung dbbelegung : ebelegungList) {
				if(zimmer.getzID().equals(dbbelegung.getZiD())){
					
					dbdate_von = dbbelegung.getZeit_von();
					dbdate_bis = dbbelegung.getZeit_bis();
					
					udtv = dfuv.parse(userdate_von);
					udtb = dfub.parse(userdate_bis);
					dtv = dfv.parse(dbdate_von);
					dtb = dfb.parse(dbdate_bis);
					
					if(
						(udtv.before(dtv)&& (udtb.before(dtv)||udtv.equals(dtv)))
														||
						((udtv.after(dtb)||udtv.equals(dtb)) && udtb.after(dtb))
					  ) {
						System.out.println("Zimmer mit ID " + zimmer.getzID() + " ist im Moment gebucht aber frei für anderes datum.");						
					} else {
						Iterator<Zimmer> myIterator = einzelnZimmerList.iterator();
						while (myIterator.hasNext()) {
							if(myIterator.next().getzID().equals(dbbelegung.getZiD())){
								toRemoveZimmer.add(zimmer);								
							}
						}
						System.out.println("Zimmer mit ID " + zimmer.getzID() + " ist im Moment gebucht CHANGE YOUR BOOKING DAY/PERIOD.");
					}
				}else{
					System.out.println("Das Zimmer mit ID -- " + zimmer.getzID() + " vs " +  dbbelegung.getZiD() + " -- ist nicht in DB Belegung.");
				}
			}
		}
		listZimmerFrei.removeAll(toRemoveZimmer);
		return listZimmerFrei;
	}
	public ArrayList<Zimmer> zimmerDfrei (ArrayList<Zimmer> doppelZimmerList, ArrayList<Buchung> dbelegungList)throws ParseException, java.text.ParseException{
		ArrayList<Zimmer> toRemoveZimmer = new ArrayList<Zimmer>();
		String userdate_von = buchung.getZeit_von();
		String userdate_bis = buchung.getZeit_bis();
		String dbdate_von;
		String dbdate_bis;
		Date udtv = new Date();
		Date udtb = new Date();
		Date dtv = new Date();
		Date dtb = new Date();
		SimpleDateFormat dfuv = new SimpleDateFormat("dd.MM.yyyy");
		SimpleDateFormat dfub = new SimpleDateFormat("dd.MM.yyyy");
		SimpleDateFormat dfv = new SimpleDateFormat("dd.MM.yyyy");
		SimpleDateFormat dfb = new SimpleDateFormat("dd.MM.yyyy");
		listDZimmerFrei = doppelZimmerList;
		
		for (Zimmer zimmer : doppelZimmerList) {
			for (Buchung dbbelegung : dbelegungList) {
				if(zimmer.getzID().equals(dbbelegung.getZiD())){
					
					dbdate_von = dbbelegung.getZeit_von();
					dbdate_bis = dbbelegung.getZeit_bis();
					
					udtv = dfuv.parse(userdate_von);
					udtb = dfub.parse(userdate_bis);
					dtv = dfv.parse(dbdate_von);
					dtb = dfb.parse(dbdate_bis);
					
					if(
						(udtv.before(dtv)&& (udtb.before(dtv)||udtv.equals(dtv)))
														||
						((udtv.after(dtb)||udtv.equals(dtb)) && udtb.after(dtb))
					  ) {
						System.out.println("Zimmer mit ID " + zimmer.getzID() + " ist im Moment gebucht aber frei für anderes datum.");
					} else {
						Iterator<Zimmer> myIterator = doppelZimmerList.iterator();
						while (myIterator.hasNext()) {
							if(myIterator.next().getzID().equals(dbbelegung.getZiD())){
								toRemoveZimmer.add(zimmer);								
							}
						}
						System.out.println("Zimmer mit ID " + zimmer.getzID() + " ist im Moment gebucht CHANGE YOUR BOOKING DAY/PERIOD.");
					}
				}else{
					System.out.println("Das Zimmer mit ID -- " + zimmer.getzID() + " vs " +  dbbelegung.getZiD() + " -- ist nicht in DB Belegung.");
				}
			}
		}
		listDZimmerFrei.removeAll(toRemoveZimmer);
		return listDZimmerFrei;
	}
	public ArrayList<Zimmer> zimmerSfrei (ArrayList<Zimmer> suiteList, ArrayList<Buchung> sbelegungList)throws ParseException, java.text.ParseException{
		ArrayList<Zimmer> toRemoveZimmer = new ArrayList<Zimmer>();
		String userdate_von = buchung.getZeit_von();
		String userdate_bis = buchung.getZeit_bis();
		String dbdate_von;
		String dbdate_bis;
		Date udtv = new Date();
		Date udtb = new Date();
		Date dtv = new Date();
		Date dtb = new Date();
		SimpleDateFormat dfuv = new SimpleDateFormat("dd.MM.yyyy");
		SimpleDateFormat dfub = new SimpleDateFormat("dd.MM.yyyy");
		SimpleDateFormat dfv = new SimpleDateFormat("dd.MM.yyyy");
		SimpleDateFormat dfb = new SimpleDateFormat("dd.MM.yyyy");
		listSZimmerFrei = suiteList;
		
		for (Zimmer zimmer : suiteList) {
			for (Buchung dbbelegung : sbelegungList) {
				if(zimmer.getzID().equals(dbbelegung.getZiD())){
					
					dbdate_von = dbbelegung.getZeit_von();
					dbdate_bis = dbbelegung.getZeit_bis();
					
					udtv = dfuv.parse(userdate_von);
					udtb = dfub.parse(userdate_bis);
					dtv = dfv.parse(dbdate_von);
					dtb = dfb.parse(dbdate_bis);
					
					if(
						(udtv.before(dtv)&& (udtb.before(dtv)||udtv.equals(dtv)))
														||
						((udtv.after(dtb)||udtv.equals(dtb)) && udtb.after(dtb))
					  ) {
						System.out.println("Zimmer mit ID " + zimmer.getzID() + " ist im Moment gebucht aber frei für anderes datum.");
						continue;
					} else {
						Iterator<Zimmer> myIterator = suiteList.iterator();
						while (myIterator.hasNext()) {
							if(myIterator.next().getzID().equals(dbbelegung.getZiD())){
								toRemoveZimmer.add(zimmer);								
							}
						}
						System.out.println("Zimmer mit ID " + zimmer.getzID() + " ist im Moment gebucht CHANGE YOUR BOOKING DAY/PERIOD.");
					}
				}else{
					System.out.println("Das Zimmer mit ID -- " + zimmer.getzID() + " vs " +  dbbelegung.getZiD() + " -- ist nicht in DB Belegung.");
				}
			}
		}
		listSZimmerFrei.removeAll(toRemoveZimmer);
		return listSZimmerFrei;
	}
	
	//Gibt die ZAhl notwendig für die Nächste Buchungsnummer
	public int getNummerZahl() throws ClassNotFoundException, SQLException{
		Connection dbConn = new IOManager().getConnection();
		int zahl = 0;
		if (dbConn != null){
			String sql = "SELECT BUCHUNGSNUMMER FROM BUCHUNG ";			
			System.out.println(sql);
			Statement stmt = dbConn.createStatement();
			ResultSet dbRes = stmt.executeQuery(sql);
			System.out.println("SQL-Befehl erfolgreich ausgeführt");
			System.out.println();
		while(dbRes.next()){							
		String buchungsnummer = dbRes.getString("BUCHUNGSNUMMER");
		char c = buchungsnummer.charAt(0);
		String ersteChar = String.valueOf(c);
		zahl = Integer.valueOf(ersteChar);
		}
	}					
		System.out.println("Die Nummerzahl: " + zahl + " wurde zurückgegeben.");
		return zahl;
	}
	
	// Neue Buchung einfügen
		public void insertBuchung() throws SQLException, ClassNotFoundException{
			Connection dbConn = new IOManager().getConnection();
			String sql = "INSERT INTO buchung" +
						"(BUCHUNGSNUMMER, POSITION, BDATUM, STATUS, ZEIT_VON, ZEIT_BIS, ZIMMER_ID)" +
						"VALUES" +
						"(?,?,?,?,?,?,?)";
			System.out.println(sql);
//			Connection dbConn = new IOManager().getConnection();
			PreparedStatement prep = dbConn.prepareStatement(sql);
			prep.setString(1, buchung.getBuchungsnummer());
			prep.setInt(2, buchung.getPosition());
			prep.setString(3, buchung.getBdatum());
			prep.setString(4, buchung.getStatus());
			prep.setString(5, buchung.getZeit_von());
			prep.setString(6, buchung.getZeit_bis());
			prep.setObject(7,  buchung.getZiD());
			prep.executeUpdate();
			System.out.println("Buchung erfolgreich eingefügt");
		}
		// Update Methode für die Umbuchung
		public void umbuchen() throws SQLException, ClassNotFoundException{
			Connection dbConn = new IOManager().getConnection();
			String sql = "UPDATE buchung " 		+
						"SET BDATUM = ?, " 	+
						"STATUS = ? " 		+
						"ZIMMERID = ? " 		+
						"WHERE BUCHUNGSNUMMER = ? AND POSITION = ?;";
			System.out.println(sql);
//			Connection dbConn = new IOManager().getConnection();
			PreparedStatement prep = dbConn.prepareStatement(sql);
			prep.setString(1, buchung.getBdatum());
			prep.setString(2, buchung.getStatus());
			prep.setString(3, buchung.getZiD());		
			prep.executeUpdate();
			System.out.println("Gebuchte Räume werden erfolgreich geändert.");
		}
		// delete Methode für die Stornierung
		public void buchungStornieren() throws SQLException, ClassNotFoundException{
			Connection dbConn = new IOManager().getConnection();
			String sql = "delete from BUCHUNG WHERE BUCHUNGSNUMMER = ? AND POSITION = ?;";
			System.out.println(sql);
//			Connection dbConn = new IOManager().getConnection();
			PreparedStatement prep = dbConn.prepareStatement(sql);		
			prep.setString(1, buchung.getBuchungsnummer());
			prep.setInt(2, buchung.getPosition());
			prep.executeUpdate();
			System.out.println("Gebuchte Räume werden erfolgreich Storniert.");
		}
		public ArrayList<Zimmer> getEZFreielist(){
			int zahlE = buchung.getZahlE(); //anzahl der zu selektierende Zimmer
			ArrayList<Zimmer> selList = new ArrayList<Zimmer>(); // liste der selektierten Zimmer
			Iterator<Zimmer> iter = listZimmerFrei.iterator();
				
			for (int i = 0; i < zahlE; i++) {
				try {
					if (iter.hasNext()) {
						Zimmer zimmer = iter.next();
						selList.add(zimmer);
						}else{
							System.out.println("Es ist keine Einzelnzimmer mehr verfügbar");
						}
				} catch (NoSuchElementException nSe) {
					// TODO: handle exception
					nSe.printStackTrace();
					}	
			}
//			listZimmerFrei.clear();
			return selList;		
		}
		public ArrayList<Zimmer> getDZFreielist(){
			int zahlD = buchung.getZahlD();
			ArrayList<Zimmer> selList = new ArrayList<Zimmer>();
			Iterator<Zimmer> iter = listDZimmerFrei.iterator();

			for (int i = 0; i < zahlD; i++) {				
				try {
					if (iter.hasNext()) {
						Zimmer zimmer = iter.next();
						selList.add(zimmer);
						}else{
							System.out.println("Es ist keine DoppelZimmer mehr verfügbar");
						}
				} catch (NoSuchElementException nSe) {
					// TODO: handle exception
					nSe.printStackTrace();
				}
			}	
//			for (Zimmer zimmerfrei : listDZimmerFrei) {
//					frei += zimmerfrei.getZimmeralsHtml();
//				}
//			listDZimmerFrei.clear();
			return selList;		
		}
		public ArrayList<Zimmer> getSFreielist(){
			int zahlS = buchung.getZahlS();
			ArrayList<Zimmer> selList = new ArrayList<Zimmer>();
			Iterator<Zimmer> iter = listSZimmerFrei.iterator();

			for (int i = 0; i < zahlS; i++) {				
				try {
					if (iter.hasNext()) {
						Zimmer zimmer = iter.next();
						selList.add(zimmer);
						}else{
							System.out.println("Es ist keine Suite mehr verfügbar");
						}
				} catch (NoSuchElementException nSe) {
					// TODO: handle exception
					nSe.printStackTrace();
				}
			}	
//			listSZimmerFrei.clear();
			return selList;		
		}
		// Methode die prüft ob die Endzeit vor der Startzeit liegt	
				public boolean zeitCheck(String von, String bis) throws java.text.ParseException {
					Date dtv = new Date();
					Date dtb = new Date();
					SimpleDateFormat dfv = new SimpleDateFormat("dd.MM.yyyy");
					SimpleDateFormat dfb = new SimpleDateFormat("dd.MM.yyyy");
					try {
						dtv = dfv.parse(von);
					} catch (ParseException pe) {
						// TODO: handle exception
						pe.printStackTrace();
					}
					
					System.out.println();
					System.out.println(dtv + " ist dtv" );
					try {
						dtb = dfb.parse(bis);
					} catch (ParseException pex) {
						// TODO: handle exception
						pex.printStackTrace();
					}
					
					System.out.println(dtb + " ist dtb" );
					System.out.println();
					
					if(dtb.before(dtv) || (dtb.equals(dtv))){
						return false;
					}
					return true;
				}
	
	public Buchung getBuchung() {
		return buchung;
	}
	public void setBuchung(Buchung buchung) {
		this.buchung = buchung;
	}
	public ArrayList<Zimmer> getListZimmerFrei() {
		return listZimmerFrei;
	}
	public void setListZimmerFrei(ArrayList<Zimmer> listZimmerFrei) {
		this.listZimmerFrei = listZimmerFrei;
	}
	public ArrayList<Zimmer> getListDZimmerFrei() {
		return listDZimmerFrei;
	}
	public void setListDZimmerFrei(ArrayList<Zimmer> listDZimmerFrei) {
		this.listDZimmerFrei = listDZimmerFrei;
	}
	public ArrayList<Zimmer> getListSZimmerFrei() {
		return listSZimmerFrei;
	}
	public void setListSZimmerFrei(ArrayList<Zimmer> listSZimmerFrei) {
		this.listSZimmerFrei = listSZimmerFrei;
	}
	public ArrayList<Buchung> getEbelegungList() {
		return ebelegungList;
	}
	public void setEbelegungList(ArrayList<Buchung> ebelegungList) {
		this.ebelegungList = ebelegungList;
	}
	public ArrayList<Buchung> getDbelegungList() {
		return dbelegungList;
	}
	public void setDbelegungList(ArrayList<Buchung> dbelegungList) {
		this.dbelegungList = dbelegungList;
	}
	public ArrayList<Buchung> getSbelegungList() {
		return sbelegungList;
	}
	public void setSbelegungList(ArrayList<Buchung> sbelegungList) {
		this.sbelegungList = sbelegungList;
	}
	public ArrayList<Zimmer> getEinzelnZimmerList() {
		return einzelnZimmerList;
	}
	public void setEinzelnZimmerList(ArrayList<Zimmer> einzelnZimmerList) {
		this.einzelnZimmerList = einzelnZimmerList;
	}
	public ArrayList<Zimmer> getDoppelZimmerList() {
		return doppelZimmerList;
	}
	public void setDoppelZimmerList(ArrayList<Zimmer> doppelZimmerList) {
		this.doppelZimmerList = doppelZimmerList;
	}
	public ArrayList<Zimmer> getSuiteList() {
		return suiteList;
	}
	public void setSuiteList(ArrayList<Zimmer> suiteList) {
		this.suiteList = suiteList;
	}
	
}