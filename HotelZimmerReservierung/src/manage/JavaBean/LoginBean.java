package manage.JavaBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import xdataBaseConnection.IOManager;

public class LoginBean {
	
	String email;
	String password;
	boolean loggedIn;
	
	public LoginBean(){
		this.loggedIn = false;
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

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
}
