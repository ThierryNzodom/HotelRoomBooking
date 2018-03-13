package manage.JavaBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import manage.JavaClass.Kunde;
import xdataBaseConnection.IOManager;
import xdataBaseConnection.MySQLAccess;
import xdataBaseConnection.NoConnectionException;

public class UserBean {

	String password;
	String email;
	int loggedIn;
	
	boolean logIn;
	
	Kunde kunde;

	Connection dbConn;
	
	public UserBean() throws ClassNotFoundException, SQLException, NoConnectionException{
		dbConn = new MySQLAccess().getConnection();
		
		email    = "";
		password = "";
		loggedIn = 0;
		logIn = false;
		
	}
	
	public boolean insertUserIfNotExists() throws SQLException, NoConnectionException{
		// true = User wurde eingefügt
		if (this.checkUserExists()){
			return false;
		}else{
			this.insertUserNoCheck();
			return true;
		}
	}
	
	//Bei der Anmeldung
	public boolean checkUserExists() throws SQLException{
		String sql = "SELECT email " + 
				"FROM USER " +
				"WHERE email = ?";
		System.out.println(sql);
		PreparedStatement prepStat = dbConn.prepareStatement(sql);
		prepStat.setString(1, this.getEmail());
		ResultSet dbRes = prepStat.executeQuery();
		// return dbRes.next();
		if (dbRes.next()){ 
			System.out.println(this.getEmail() + " schon registriert, Login geklappt!");
			return true;
		} else { 
			System.out.println(this.getEmail() + " noch nicht registriert, Login failed!");
			return false;
		}
	}
	// USer Exist für Admin
	public boolean checkUExists(String mail) throws SQLException{
		String sql = "SELECT email " + 
				"FROM USER " +
				"WHERE email = ?";
		System.out.println(sql);
		PreparedStatement prepStat = dbConn.prepareStatement(sql);
		prepStat.setString(1, mail);
		ResultSet dbRes = prepStat.executeQuery();
		// return dbRes.next();
		if (dbRes.next()){ 
			System.out.println(this.getEmail() + " schon registriert, Login geklappt!");
			return true;
		} else { 
			System.out.println(this.getEmail() + " noch nicht registriert, Login failed!");
			return false;
		}
	}
	
	public boolean checkEmailPassword() throws Exception{
		// true  - User/PW-Kombination existiert
		// false - User/PW-Kombination existiert nicht

//		if (this.userid.length() > 16) userid = userid.substring(0,16);
//		if (this.password.length() > 32) password = password.substring(0,32);
		
		String sql = "SELECT EMAIL FROM USER "
						+ "WHERE EMAIL = ? AND PASSWORD = ?";
		System.out.println(sql);
		Connection dbConn = new IOManager().getConnection();
		PreparedStatement prep = dbConn.prepareStatement(sql);
		prep.setString(1, this.email);
		prep.setString(2, this.password);
		ResultSet dbRes = prep.executeQuery();
		return dbRes.next();
	}
	
	public void prepareAttributesForDB() {
//		if (this.knr.length() > 16) knr = knr.substring(0,16);
//		if (this.username.length() > 256) username = username.substring(0,256);
		if (this.email.length() > 256) email = email.substring(0,256);
		if (this.password.length() > 32) password = password.substring(0,32);

//		if (admin  == null) admin  = "N";
//		if (active == null) active = "N";
		
//		if (admin.equalsIgnoreCase("Y") 
//				|| admin.equalsIgnoreCase("yes")
//				|| admin.equalsIgnoreCase("j")
//				|| admin.equalsIgnoreCase("ja")) admin = "Y";
//		else admin = "N";
//		if (active.equalsIgnoreCase("Y") 
//				|| active.equalsIgnoreCase("yes")
//				|| active.equalsIgnoreCase("j")
//				|| active.equalsIgnoreCase("ja")) active = "Y";
//		else active = "N";
	}

	public int insertUserNoCheck() throws SQLException, NoConnectionException {
		this.prepareAttributesForDB();
		// Feldlängen testen
		Connection dbConn = new MySQLAccess().getConnection();
		
		if(dbConn != null){
		String sql = "INSERT INTO USER " +
				"(EMAIL, PASSWORD, LOGGEDIN, KNR) " +
				"VALUES " +
				"(?,?,?,?)";
		System.out.println(sql);
		
		try {
			
		PreparedStatement prepStat = dbConn.prepareStatement(sql);
		prepStat.setString(1, this.getEmail());
		prepStat.setString(2, this.getPassword());
		prepStat.setInt(3, this.getLoggedIn());
		prepStat.setInt(4, this.kunde.getKnr());


		prepStat.executeUpdate();
		System.out.println("User Email: " + this.getEmail() + " erfolgreich eingefügt!");
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
	public int loginKnrUser() throws SQLException, ClassNotFoundException {

		dbConn = new IOManager().getConnection();

		String sqlQuery = "SELECT EMAIL, PASSWORD, KNR FROM USER WHERE EMAIL = ? AND PASSWORD= ?";
		PreparedStatement prepStat = dbConn.prepareStatement(sqlQuery);
		int knr = 0;
		prepStat.setString(1, this.email);
		prepStat.setString(2, this.password);
		ResultSet results = prepStat.executeQuery();
		System.out.println(sqlQuery);
		while (results.next()) {
			String email = results.getString("EMAIL");
			String password = results.getString("PASSWORD");
			knr = results.getInt("KNR");
			if (this.email.equals(email.trim()) && this.password.equals(password.trim())) {
				System.out.println("Kunde ist schon angemeldet.");
				String sqlUpdate = "UPDATE USER SET LOGGEDIN = ? WHERE EMAIL = ?";
				PreparedStatement prepStat2 = dbConn.prepareStatement(sqlUpdate);
				prepStat2.setInt(1, 1);
				prepStat2.setString(2, email);
				prepStat2.execute();
				System.out.println(sqlUpdate);
				return knr;
			} else {
				System.out.println("Der USER konnte nicht gefunden werden");
				return knr;
			}
		}
		System.out.println("User nicht gefunden");
		return knr;
	}
	//Gibt Kunde mit bestimmter Email Adresse
	public Kunde getKundeFromEmail(String kundemail) throws SQLException, ClassNotFoundException {

		dbConn = new IOManager().getConnection();
		
		Kunde selkunde = new Kunde();

		String sqlQuery = "SELECT EMAIL, KNR FROM USER WHERE EMAIL = ?";
		PreparedStatement prepStat = dbConn.prepareStatement(sqlQuery);
		prepStat.setString(1, kundemail);
		ResultSet results = prepStat.executeQuery();
		System.out.println(sqlQuery);
		while (results.next()) {
			String kemail = results.getString("EMAIL");
			int knr = results.getInt("KNR");
			if (this.email.equals(kemail.trim())) {
				System.out.println("Du bist der als Admin angemeldet.");
			}else{
				String sqlUpdate = "SELECT * FROM KUNDE WHERE KNR = ?";
				PreparedStatement prepStat2 = dbConn.prepareStatement(sqlUpdate);
				prepStat2.setInt(1, knr);
				ResultSet rset = prepStat2.executeQuery();
				System.out.println(sqlUpdate);
				while(rset.next()){
					int nr = rset.getInt("KNR");
					String anrede = rset.getString("ANREDE");
					String vorname = rset.getString("VORNAME");
					String nachname = rset.getString("NACHNAME");
					String gdatum = rset.getString("GDATUM");
					String telnummer = rset.getString("TELNUMMER");
					String adresse = rset.getString("ADRESSE");
					
					//Kunde Values füllen
					selkunde.setKnr(nr);
					selkunde.setAnrede(anrede);
					selkunde.setVorname(vorname);
					selkunde.setNachname(nachname);
					selkunde.setGdatum(gdatum);
					selkunde.setTelnummer(telnummer);
					selkunde.setAdresse(adresse);			
				}
			}
		}
		System.out.println("Kunde wurde erfolgreich selectiert");
		return selkunde;
	}
		//Loesche Email from DB
			public boolean loescheEmail(String mail) throws Exception {
		        PreparedStatement statement = null;
		        boolean tf = false;
		        try {
		            String query = "DELETE FROM USER WHERE EMAIL = ?;";
		            Connection connection = new IOManager().getConnection();
		            statement = connection.prepareStatement(query);
		            statement.setString(1, mail);
		            int r = statement.executeUpdate();
		            /** Check if the Bill was successfully deleted. */
		            if (r > 0) {
		                System.out.println("EmailAccount [" + mail + "] was successfully deleted.");
		                tf = true;
		            } else {
		                System.out.println("Failed to delete emailAccount: " + mail);
		            }

		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		            if (statement != null) {
		                statement.close();
		            }
		        }
		        return tf;
		    }
	// Methode zum Ausloggen der Kunde
	public boolean logoutUser() throws ClassNotFoundException, SQLException {
		dbConn = new IOManager().getConnection();
		String sqlUpdate = "UPDATE USER SET LOGGEDIN = ? WHERE EMAIL = ?;";
		PreparedStatement prepStat = dbConn.prepareStatement(sqlUpdate);
		prepStat.setInt(1, 0);
		prepStat.setString(2, this.email);
		prepStat.execute();
		System.out.println(sqlUpdate);
		System.out.println(this.email);
		return true;
	}
	public String toString(){
		String s = "UserBean mit ";
				s+= "email: " + this.getEmail();	
				s+= "password: " + this.getPassword() + ", ";
				s+= "knr: " + this.kunde.getKnr() + ", ";
							
		return s;
	}
	
	public boolean isLogIn() {
		return logIn;
	}
	public void setLogIn(boolean logIn) {
		this.logIn = logIn;
	}
	public Kunde getKunde() {
		return kunde;
	}
	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(int loggedIn) {
		this.loggedIn = loggedIn;
	}

}
