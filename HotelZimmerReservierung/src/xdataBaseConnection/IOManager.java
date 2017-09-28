package xdataBaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

 //Import-Anweisungen wurden zwecks Übersichtlichkeit entfernt
public class IOManager{
Connection dbConn;
String host = "localhost";
String database = "roombooking";
String user = "root";
String passwd = "aSUwx6Qa2IcPY3kk";
String dbDrivername = "com.mysql.jdbc.Driver";

 
public IOManager() throws ClassNotFoundException, SQLException {
 this.createConnection();
}

 

public void createConnection() throws ClassNotFoundException, SQLException{
	Class.forName(dbDrivername);
	System.out.println("Treiber erfolgreich geladen");

	dbConn = DriverManager.getConnection(
			"jdbc:mysql://"+host+"/"+database+"?user="+user+"&password="+passwd
										);
	System.out.println("Datenbankverbindung erfolgreich angelegt");
}

public Connection getConnection() throws SQLException, ClassNotFoundException {
	return dbConn;
}


}
