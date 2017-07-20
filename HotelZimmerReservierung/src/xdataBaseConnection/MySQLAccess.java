package xdataBaseConnection;

public class MySQLAccess extends JDBCAccess {

	@Override
	void setParms() {
		dbDrivername = "com.mysql.jdbc.Driver";		
		dbURL        = "jdbc:mysql://localhost:3306/roombooking";
		dbUserid 	 = "root";
		dbPassword   = "";
	}
	public MySQLAccess() throws NoConnectionException {
		super();
	}
	
	public static void main(String[] args) throws NoConnectionException {
		System.out.println("Herstellung einer Datenbankverbindung...");
		new MySQLAccess().getConnection();
	}

}
