package xdataBaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class JDBCAccess {
	Connection dbConn;
	String dbDrivername;
	String dbURL;
	String dbUserid;
	String dbPassword;
//	String dbSchema;
	
	JDBCAccess() throws NoConnectionException, SQLException {
		this.setParms();
//		this.createConnection();
//		this.setSchema();
	}
	
	abstract void setParms();
	
//	public void setSchema() throws SQLException {
//		String sql = "SET SCHEMA ?";
//		System.out.println(sql);
//		PreparedStatement prepStat = dbConn.prepareStatement(sql);
//		prepStat.setString(1, dbSchema);
//		prepStat.executeUpdate();
//		System.out.println("Schema " + dbSchema + " erfolgreich gesetzt");
//	}
	
	void createConnection() throws NoConnectionException{
		
	try{			
		System.out.println("mySQL-Treiber wurden erfolgreich geladen");
		System.out.println("------------------------------");
		System.out.println("DriverName: " + Class.forName(dbDrivername));
		dbConn = DriverManager.getConnection(
				dbURL,
				dbUserid,
				dbPassword);			
		System.out.println("Datenbankverbindung erfolgreich hergestellt :)");
		System.out.println("------------------------------");
		System.out.println("Sie sind verbunden!");
	
	}catch(Exception e){
		e.printStackTrace();
		throw new NoConnectionException("Aufbau der Datenbankverbindung fehlgeschlagen");
		}
	}
	
	public Connection getConnection() throws NoConnectionException {		
		this.createConnection();
		return dbConn;
	}
}