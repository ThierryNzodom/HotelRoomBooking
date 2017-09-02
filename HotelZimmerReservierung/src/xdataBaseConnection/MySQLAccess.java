package xdataBaseConnection;

import java.sql.SQLException;

public class MySQLAccess extends JDBCAccess {

	@Override
	void setParms() {
		dbDrivername = "com.mysql.jdbc.Driver";		
		dbURL        = "jdbc:mysql://localhost:3306/roombooking";
		dbUserid 	 = "root";
		dbPassword   = "aSUwx6Qa2IcPY3kk";
//		dbSchema = "";
	}
	public MySQLAccess() throws NoConnectionException, SQLException {
		super();
	}
	
	public static void main(String[] args) throws NoConnectionException, SQLException {
		System.out.println("Herstellung einer Datenbankverbindung in Bearbeitung...");
		new MySQLAccess().getConnection();
	}

}