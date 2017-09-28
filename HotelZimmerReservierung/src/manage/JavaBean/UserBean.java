package manage.JavaBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import xdataBaseConnection.IOManager;
import xdataBaseConnection.MySQLAccess;
import xdataBaseConnection.NoConnectionException;

public class UserBean {

//	String userid;
//	String active;
//	String admin;
//	String username;
	String password;
	String email;
	boolean loggedIn;
	Connection dbConn;
	
	public UserBean() throws ClassNotFoundException, SQLException, NoConnectionException{
		dbConn = new MySQLAccess().getConnection();
		
		email    = "";
		password = "";
		this.loggedIn = false;
//		userid   = "";
//		active   = "";
//		admin    = "";
//		username = "";
		
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
//		if (this.userid.length() > 16) userid = userid.substring(0,16);
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
				"(EMAIL, PASSWORD) " +
				"VALUES " +
				"(?,?)";
		System.out.println(sql);
		
		try {
			
		PreparedStatement prepStat = dbConn.prepareStatement(sql);
		
//		prepStat.setString(1, this.getUserid());
		prepStat.setString(1, this.getEmail());
		prepStat.setString(2, this.getPassword());
//		prepStat.setString(3, this.getActive());
//		prepStat.setString(4, this.getAdmin());
//		prepStat.setString(5, this.getUsername());

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
	
	public String toString(){
		String s = "UserBean mit ";
				s+= "email: " + this.getEmail();	
				s+= "password: " + this.getPassword() + ", ";
//				s+= "active: " + this.getActive() + ", ";
//				s+= "admin: " + this.getAdmin() + ", ";
//				s+= "username: " + this.getUsername() + ", ";
//				s+= "userid: " + this.getUserid() + ", ";
							
		return s;
	}
	
//	public String getUserid() {
//		return userid;
//	}
//	public void setUserid(String userid) {
//		this.userid = userid;
//	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
//	}
//	public String getUsername() {
//		return username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

}
