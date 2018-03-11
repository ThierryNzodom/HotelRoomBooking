package manage.JavaBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import xdataBaseConnection.IOManager;
import manage.JavaClass.Feiertag;
import manage.JavaClass.Zimmer;

public class ZimmerVerwaltungBean {

	String zID;
	String ztyp;
	String zeit_von;
	String zeit_bis;
	String groesse;
	double preis;
	
	List<Zimmer> zimmerList = new ArrayList<Zimmer>();
	Feiertag feiertag;
	String fehler;
	
	// Variabeln f�r Datumsangaben	
		Date dt = new Date();
		SimpleDateFormat df = new SimpleDateFormat( "dd.mm.yyyy" );
			
		String[] feiertage = {"2017-08-08" , "2017-08-15" , "2017-10-03" ,"2017-10-31", 
				"2017-11-01", "2017-11-22", "2017-12-25", "2017-12-26"};
		
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
						+ "AND ZIMMERTYP = ?";
						 
				System.out.println(sql);
				try {
					PreparedStatement prep = dbConn.prepareStatement(sql);
					prep.setString(1, this.getzID());
					prep.setString(2, this.getZtyp());
					ResultSet dbRes = prep.executeQuery();
					System.out.println("SQL-Befehl erfolgreich ausgef�hrt");
					
				// Belegte Zimmer werden in Hashset eingef�gt um sie in der View ausgeben zu k�nnen
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
						System.out.println("Kein Zimmer frei in der gew�hlten Zeit");
					}else{
						
						System.out.println("F�r den gew�hlten Zeitraum sind folgende Zimmer verf�gbar: ");
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
	// Neue Zimmer anf�gen
		public void insertZimmer() throws SQLException, ClassNotFoundException{
			String sql = "INSERT INTO zimmer" +
						"(ZIMMERID, ZIMMERTYP, ZEIT_VON, ZEIT_BIS, GROESSE, PREIS, IST_BELEGT)" +
						"VALUES" +
						"(?,?,?,?)";
			System.out.println(sql);
			Connection dbConn = new IOManager().getConnection();
			PreparedStatement prep = dbConn.prepareStatement(sql);
			prep.setString(1, this.getzID());
			prep.setString(2, this.getZtyp());
			prep.setString(3, this.getZeit_von());
			prep.setString(4, this.getZeit_bis());
			prep.setString(5, this.getGroesse());
			prep.setDouble(6, this.getPreis());
			prep.executeUpdate();
			System.out.println("Satz erfolgreich eingef�gt");
		}
	// Zeit zu der ein Zimmer gebucht ist, wird ge�ndert	
		public void updateZeit() throws SQLException, ClassNotFoundException{
			String sql = "UPDATE zimmer " 		+
						"SET ZEIT_VON = ?, " 	+
						"ZEIT_BIS = ? " 		+
						"WHERE ZIMMERID = ? " 	+
						"AND IST_BELEGT = JA;";
			System.out.println(sql);
			Connection dbConn = new IOManager().getConnection();
			PreparedStatement prep = dbConn.prepareStatement(sql);
			prep.setString(1, this.getZeit_von());
			prep.setString(2, this.getZeit_bis());
			prep.setString(3, this.getzID());		
			prep.executeUpdate();
			System.out.println("Gebuchter Zeitraum wird erfolgreich ge�ndert.");
		}
	// gebuchtes Zimmer wird ge�ndert	
		public void updateRaum() throws SQLException, ClassNotFoundException{
			String sql = "UPDATE zimmer " +
						"SET ZIMMERID = ? " +
						"where IST_BELEGT = JA;";
			System.out.println(sql);
			Connection dbConn = new IOManager().getConnection();
			PreparedStatement prep = dbConn.prepareStatement(sql);
			prep.setString(1, this.getzID());			
			prep.executeUpdate();
			System.out.println("Zimmer erfolgreich ge�ndert.");
		}
	// Zuvor selektierte Termine werden ausgegeben	
//		public String getVorlesungenAsHtml(){
//			String html = "";
//			String free = "F�r den gew�hlten Zeitraum finden folgende Vorlesungen statt: " + "\n";
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
	
	// Methode die pr�ft ob die Endzeit vor der Startzeit liegt	
		public boolean zeitCheck(String von, String bis) throws ParseException{
			Date dtv = new Date();
			Date dtb = new Date();
			SimpleDateFormat dfv = new SimpleDateFormat("dd.mm.yyyy");
			SimpleDateFormat dfb = new SimpleDateFormat("dd.mm.yyyy");
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
			
			if(dtb.before(dtv)){
				return false;
			}
			return true;
		}
	// Methode, die die Anzahl �bernachtungen ermittelt
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
	//Preis eines Zimmers �ber eine Periode
		public double periodPreis(int periode) {
			double preis;
			preis = this.getPreis() * periode;
			this.setPreis(preis);
			return preis;
		}
		// Methode die vor dem Update pr�ft ob �berhaupt ein Eintrag vorliegt	
				public boolean preCheck() throws SQLException, ClassNotFoundException{
					Connection dbConn = new IOManager().getConnection();
					if (dbConn != null){
						String sql = "SELECT * FROM zimmer "
								+ "WHERE Zeit_von = ? "
								+ " AND Zeit_bis = ?  "
								+ " AND ZIMMERID = ? ;";
						System.out.println(sql);
					
							PreparedStatement prep = dbConn.prepareStatement(sql);
							prep.setString(1, this.getZeit_von());
							prep.setString(2, this.getZeit_bis());
							prep.setString(3, this.getzID());
							ResultSet dbRes = prep.executeQuery();
							System.out.println("SQL-Befehl erfolgreich ausgef�hrt");
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
			// Methode die vor dem Insert pr�ft ob zu den eingegebenen Daten schon ein Termin existiert	
				public boolean preCheckInsert() throws SQLException, ClassNotFoundException{
					Connection dbConn = new IOManager().getConnection();
					if (dbConn != null){
						String sql = "SELECT * FROM zimmer "
								+ "WHERE (Zeit_von BETWEEN ? AND ? "
								+ " OR Zeit_bis BETWEEN  ? AND ? ) "						
								+ " AND ZIMMERID = ? ;";
						System.out.println(sql);
							PreparedStatement prep = dbConn.prepareStatement(sql);
							prep.setString(1, this.getZeit_von());
							prep.setString(2, this.getZeit_bis());
							prep.setString(3, this.getZeit_von());
							prep.setString(4, this.getZeit_bis());
							prep.setString(5, this.getzID());
							ResultSet dbRes = prep.executeQuery();
							System.out.println("SQL-Befehl erfolgreich ausgef�hrt");
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
			// Methode die pr�ft ob es sich beim angegebenen Datum um einen Feiertag handelt	
				public boolean feiertagCheckEinfach(){
					boolean check = false;
					for (int i = 0; i < feiertage.length; i++) {
						if(this.getZeit_von().equals(feiertage[i]) || this.getZeit_bis().equals(feiertage[i])){
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
						System.out.println("SQL-Befehl erfolgreich ausgef�hrt");
						while(dbRes.next()){
							feiertageSet.add(dbRes.getString(feiertag.getDatum()));
						}
					}
				}
				
				public List<Zimmer> getRauemeFromDb() throws ClassNotFoundException, SQLException{
					Connection dbConn = new IOManager().getConnection();
					if (dbConn != null){
						String sql = "SELECT * FROM ZIMMER WHERE GROESSE = ?";
						System.out.println(sql);
						PreparedStatement prep = dbConn.prepareStatement(sql);
						prep.setString(1, this.getGroesse());
						ResultSet dbRes = prep.executeQuery();
						System.out.println("SQL-Befehl erfolgreich ausgef�hrt");
						System.out.println();
						while(dbRes.next()){							
							Zimmer zimmer = new Zimmer();
							String zimmerid = dbRes.getString("ZIMMERID");
							String zimmertyp = dbRes.getString("ZIMMERTYP");
							String groesse = dbRes.getString("GROESSE");
							double preis = dbRes.getDouble("PREIS");
							
							//Zimmer Values f�llen
							zimmer.setzID(zimmerid);
							zimmer.setZtyp(zimmertyp);
							zimmer.setGroesse(groesse);
							zimmer.setPreis(preis);
							
							
							String zimmerDruecken = "Zimmer: " + zimmerid + "; Typ: " + zimmertyp + "; Groesse: " 
							+ groesse + "; Preis: " + preis;
							
							zimmerList.add(zimmer);
							
							zimmerSet.add(zimmerDruecken);
							
						}
					}					
					System.out.println("Select from Zimmer erfolgreicht.");
					
					return zimmerList;
				}
				// Zuvor selektierte freie Zimmer werden ausgegeben	
				public String getFreieRaeumeAsHtml(){
//					String html = "\n";
					String frei = "F�r den gew�hlten Zeitraum sind folgende Zimmer verf�gbar: " + "\n";
						for (Zimmer zimmerfrei : zimmerList) {
							frei += zimmerfrei.getZimmeralsHtml();
						}
//						Set<String> alle = zimmerSet;
//						Iterator<String> iter = alle.iterator();
//						while (iter.hasNext()){
//								html += iter.next() + "\n";							
//						}	
//					frei = frei + html;
					zimmerList.clear();
					return frei;		
				}
				public String[] getFeiertage() {
					return feiertage;
				}
				public void setFeiertage(String[] feiertage) {
					this.feiertage = feiertage;
				}
				public String getzID() {
					return zID;
				}
				public void setzID(String zID) {
					this.zID = zID;
				}
				public String getZtyp() {
					return ztyp;
				}
				public void setZtyp(String ztyp) {
					this.ztyp = ztyp;
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
				public String getGroesse() {
					return groesse;
				}
				public void setGroesse(String groesse) {
					this.groesse = groesse;
				}
				public double getPreis() {
					return preis;
				}
				public void setPreis(double preis) {
					this.preis = preis;
				}
				public String getFehler() {
					return fehler;
				}

				public void setFehler(String fehler) {
					this.fehler = fehler;
				}
}
