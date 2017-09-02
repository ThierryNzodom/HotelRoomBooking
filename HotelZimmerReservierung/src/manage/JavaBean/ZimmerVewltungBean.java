package manage.JavaBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import xdataBaseConnection.IOManager;
import manage.JavaClass.Zimmer;
import manage.JavaClass.Feiertag;

public class ZimmerVewltungBean {

	Zimmer zimmer;
	Feiertag feiertag;
	String fehler;
	
	// Variabeln für Datumsangaben	
		Date dt = new Date();
		SimpleDateFormat df = new SimpleDateFormat( "dd.MM.yyyy" );
		
	// Arrays mit vorhandenen Zimmern	
		String[] alleZimmerClassic = {"CL001", "CL002", "CL003", "CL004", "CL005"};
		String[] alleZimmerStandard = {"SD001", "SD002", "SD003", "SD004", "SD005"};
		String[] alleZimmerComfort = {"CD001", "CD002", "CD003", "CD004", "CD005"};			
		String[] feiertage = {"31.10.2017", "01.11.2017", "22.11.2017", "25.12.2017",
				"26.12.2017", "01.01.2018", "30.03.2018", "01.04.2018", "02.04.2018",
				"01.05.2018", "10.05.2018", "20.05.2018", "21.05.2018", "31.05.2018",
				"03.10.2018", "01.11.2018", "25.12.2018", "26.12.2018"};
	// Hashsets für Ausgaben	
		Set<String> alleCL = new HashSet<String>(Arrays.asList(alleZimmerClassic));
		Set<String> alleSD = new HashSet<String>(Arrays.asList(alleZimmerStandard));
		Set<String> alleCD = new HashSet<String>(Arrays.asList(alleZimmerComfort));
		
		Set<String> zimmerfrei = new HashSet<String>();
		Set<String> zimmerprint = new HashSet<String>();
		
		Set<String> daten = new HashSet<String>();
		Set<String> daten2 = new HashSet<String>();		
		
		Set<String> feiertageSet = new HashSet<String>();
		Set<String> zimmerSet = new HashSet<String>();
	
		// Selectmethode um freie Zimmer zu selektieren	
		public int selectZimmer() throws ClassNotFoundException, SQLException{
			Connection dbConn = new IOManager().getConnection();
			if (dbConn != null){
				String sql = "SELECT * FROM zimmer "
						+ "WHERE ZIMMERID = ? "
						+ "AND ZIMMERTYP = ?"
						+ "AND IST_BELEGT is NEIN;";
						 
				System.out.println(sql);
				try {
					PreparedStatement prep = dbConn.prepareStatement(sql);
					prep.setString(1, zimmer.getzID());
					prep.setString(2, zimmer.getZtyp(zimmer.getzID()));
					ResultSet dbRes = prep.executeQuery();
					System.out.println("SQL-Befehl erfolgreich ausgeführt");
					
				// Belegte Zimmer werden in Hashset eingefügt um sie in der View ausgeben zu können
					while (dbRes.next()){
						String ziid = dbRes.getString("ZIMMERID");
						String zt = dbRes.getString("ZIMMERTYP");
						String zv = dbRes.getString("ZEIT_VON");
						String zb = dbRes.getString("ZEIT_BIS");
						String isf = dbRes.getString("IST_BELEGT");
						String zfreiprint = "Zimmer: " + ziid + " ist ein " + zt + " ab: " + zv + " bis: " + zb + " ist frei: " + isf;
						zimmerfrei.add(ziid);
						zimmerprint.add(zfreiprint);
					}
					
				// Selektierte belegte Zimmer werden von den gesamten Zimmern abgezogen, dass nur freie Zimmer bleiben					
					zimmerSet.removeAll(zimmerfrei);
					if(zimmerSet.isEmpty()){
						System.out.println("Kein Zimmer frei in der gewählten Zeit");
					}else{
						
						System.out.println("Für den gewählten Zeitraum sind folgende Zimmer verfügbar: ");
						for (String zfrei : zimmerSet)
							System.out.println(zfrei);
							return 1;
					}										
					return 0;	
				} catch (SQLException e) {
					e.printStackTrace();
					int rc = e.getErrorCode();
					if (rc < 0) {
						return rc;
					}else {
						return 99;
					}
				}
			}else{
			return 99;
			}
		}	
	// Neue Zimmer anfügen
		public void insertZimmer() throws SQLException, ClassNotFoundException{
			String sql = "INSERT INTO zimmer" +
						"(ZIMMERID, ZIMMERTYP, ZEIT_VON, ZEIT_BIS, GROESSE, PREIS, IST_BELEGT, BELEGUNGPERSON)" +
						"VALUES" +
						"(?,?,?,?,?)";
			System.out.println(sql);
			Connection dbConn = new IOManager().getConnection();
			PreparedStatement prep = dbConn.prepareStatement(sql);
			prep.setString(1, zimmer.getzID());
			prep.setString(2, zimmer.getZtyp(zimmer.getzID()));
			prep.setString(3, zimmer.getZeit_von());
			prep.setString(4, zimmer.getZeit_bis());
			prep.setInt(5, 	  zimmer.getGroesse());
			prep.setDouble(6, zimmer.getPreis());
			prep.setString(7, zimmer.getIstBelegt());
			prep.setString(8, zimmer.getBelegungPerson());
			prep.executeUpdate();
			System.out.println("Satz erfolgreich eingefügt");
		}
	// Zeit zu der ein Zimmer gebucht ist, wird geändert	
		public void updateZeit() throws SQLException, ClassNotFoundException{
			String sql = "UPDATE zimmer " 		+
						"SET ZEIT_VON = ?, " 	+
						"ZEIT_BIS = ? " 		+
						"WHERE ZIMMERID = ? " 	+
						"AND IST_BELEGT = JA;";
			System.out.println(sql);
			Connection dbConn = new IOManager().getConnection();
			PreparedStatement prep = dbConn.prepareStatement(sql);
			prep.setString(1, zimmer.getZeit_von());
			prep.setString(2, zimmer.getZeit_bis());
			prep.setString(3, zimmer.getzID());		
			prep.executeUpdate();
			System.out.println("Gebuchter Zeitraum wird erfolgreich geändert.");
		}
	// gebuchtes Zimmer wird geändert	
		public void updateRaum() throws SQLException, ClassNotFoundException{
			String sql = "UPDATE zimmer " +
						"SET ZIMMERID = ? " +
						"where IST_BELEGT = JA;";
			System.out.println(sql);
			Connection dbConn = new IOManager().getConnection();
			PreparedStatement prep = dbConn.prepareStatement(sql);
			prep.setString(1, zimmer.getzID());			
			prep.executeUpdate();
			System.out.println("Zimmer erfolgreich geändert.");
		}
	// Zuvor selektierte Termine werden ausgegeben	
//		public String getVorlesungenAsHtml(){
//			String html = "";
//			String free = "Für den gewählten Zeitraum finden folgende Vorlesungen statt: " + "\n";
//			
//				Set<String> alle = zimmerfrei;
//				Iterator<String> iter = alle.iterator();
//				while (iter.hasNext()){
//					String key   = iter.next();
//					
//					html += key + "\n";  
//				}
//				free = free +  html;
//				zimmerfrei.clear();
//				return free;
//		}
	// Zuvor selektierte freie Zimmer werden ausgegeben	
		public String getFreieRaeumeAsHtml(){
			String zID = null;
			String html = "";
			String frei = "Für den gewählten Zeitraum sind folgende Räume verfügbar: " + "\n";
			if(zimmer.getZtyp(zID) == "Einzelzimmer"){			
				Set<String> alle = zimmerSet;
				Iterator<String> iter = alle.iterator();
				while (iter.hasNext()){
					if(iter.next().contains("CL")){
						html += iter.next() + " ";
					}	
				}
			}else
			if(zimmer.getZtyp(zID) == "Doppelzimmer"){
				Set<String> alle = zimmerSet;
				Iterator<String> iter = alle.iterator();
				while (iter.hasNext()){
					if(iter.next().contains("SD")){
						html += iter.next() + " ";
					}  
				}
			}else
			if(zimmer.getZtyp(zID) == "Suite"){
				Set<String> alle = zimmerSet;
				Iterator<String> iter = alle.iterator();
				while (iter.hasNext()){
					if(iter.next().contains("CD")){
						html += iter.next() + " ";
					}  
				}
			}	
			frei = frei + html;
			zimmerSet.clear();
			return frei;			
		}
	// Methode die prüft ob die Endzeit vor der Startzeit liegt	
		public boolean zeitCheck(String von, String bis) throws ParseException{
			Date dtv = new Date();
			Date dtb = new Date();
			SimpleDateFormat dfv = new SimpleDateFormat("dd.MM.yyyy");
			SimpleDateFormat dfb = new SimpleDateFormat("dd.MM.yyyy");
			dtv = dfv.parse(von);
			dtb = dfb.parse(bis);
			
			if(dtb.before(dtv)){
				return false;
			}
			return true;
		}
	// Methode, die die Anzahl Übernachtungen ermittelt
		public int anzahlUebernachtung(String von, String bis) throws ParseException{			
			int diffDays = 0;
			try {
				DateFormat dfvon = new SimpleDateFormat("dd.MM.yyyy");
				DateFormat dfbis = new SimpleDateFormat("dd.MM.yyyy");
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
	//Preis eines Zimmers über eine Periode
		public double preriodPreis(int periode) {
			double preis;
			preis = zimmer.getPreis() * periode;
			zimmer.setPreis(preis);
			return preis;
		}
		// Methode die vor dem Update prüft ob überhaupt ein Eintrag vorliegt	
				public boolean preCheck() throws SQLException, ClassNotFoundException{
					Connection dbConn = new IOManager().getConnection();
					if (dbConn != null){
						String sql = "SELECT * FROM zimmer "
								+ "WHERE Zeit_von = ? "
								+ " AND Zeit_bis = ?  "
								+ " AND ZIMMERID = ? ;";
						System.out.println(sql);
					
							PreparedStatement prep = dbConn.prepareStatement(sql);
							prep.setString(1, zimmer.getZeit_von());
							prep.setString(2, zimmer.getZeit_bis());
							prep.setString(3, zimmer.getzID());
							ResultSet dbRes = prep.executeQuery();
							System.out.println("SQL-Befehl erfolgreich ausgeführt");
							int index = 0;
							while (dbRes.next()){
								index ++;
							}
							if (index > 0){
								return false;	
							}else
								return true;
					}
					return false;
				}	
			// Methode die vor dem Insert prüft ob zu den eingegebenen Daten schon ein Termin existiert	
				public boolean preCheckInsert() throws SQLException, ClassNotFoundException{
					Connection dbConn = new IOManager().getConnection();
					if (dbConn != null){
						String sql = "SELECT * FROM zimmer "
								+ "WHERE (Zeit_von BETWEEN ? AND ? "
								+ " OR Zeit_bis BETWEEN  ? AND ? ) "						
								+ " AND ZIMMERID = ? ;";
						System.out.println(sql);
							PreparedStatement prep = dbConn.prepareStatement(sql);
							prep.setString(1, zimmer.getZeit_von());
							prep.setString(2, zimmer.getZeit_bis());
							prep.setString(3, zimmer.getZeit_von());
							prep.setString(4, zimmer.getZeit_bis());
							prep.setString(5, zimmer.getzID());
							ResultSet dbRes = prep.executeQuery();
							System.out.println("SQL-Befehl erfolgreich ausgeführt");
							int index = 0;
							while (dbRes.next()){
								index ++;
							}
							if (index > 0){
								return false;
							
							}else{
								return true;
							}
				}
					return false;
				}
			// Methode die prüft ob es sich beim angegebenen Datum um einen Feiertag handelt	
				public boolean feiertagCheckEinfach(){
					boolean check = false;
					for (int i = 0; i < feiertage.length; i++) {
						if(zimmer.getZeit_von().equals(feiertage[i]) || zimmer.getZeit_bis().equals(feiertage[i])){
							i = feiertage.length;
							check = true;
						}
					}
					if(check == true){
						return false;
					}else{
						return true;
					}
				}
				
				public void getFeiertageFromDb() throws ClassNotFoundException, SQLException{
					Connection dbConn = new IOManager().getConnection();
					if (dbConn != null){
						String sql = "SELECT DATUM, FEIER FROM FEIERTAGE"
								+ "WHERE datum = ? ;";
						System.out.println(sql);
						PreparedStatement prep = dbConn.prepareStatement(sql);
						prep.setString(1, feiertag.getDatum());				
						ResultSet dbRes = prep.executeQuery();
						System.out.println("SQL-Befehl erfolgreich ausgeführt");
						while(dbRes.next()){
							feiertageSet.add(dbRes.getString(feiertag.getDatum()));
						}
					}
				}
				
				public void getRaumFromDb() throws ClassNotFoundException, SQLException{
					Connection dbConn = new IOManager().getConnection();
					if (dbConn != null){
						String sql = "SELECT * FROM zimmer "
								+ "WHERE ZIMMERTYP LIKE ? ";
						System.out.println(sql);
						PreparedStatement prep = dbConn.prepareStatement(sql);
						String zÍD = null;
						prep.setObject(1, zimmer.getZtyp(zÍD));
						ResultSet dbRes = prep.executeQuery();
						System.out.println("SQL-Befehl erfolgreich ausgeführt");
						while(dbRes.next()){
							String b = dbRes.getString(zimmer.getzID());
							zimmerSet.add(b);
						}
					}
				}
				
				public String getFehler() {
					return fehler;
				}

				public void setFehler(String fehler) {
					this.fehler = fehler;
				}
}
