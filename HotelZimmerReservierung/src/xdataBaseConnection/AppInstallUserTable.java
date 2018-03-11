package xdataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AppInstallUserTable {

	Connection dbConn;
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, NoConnectionException {
		AppInstallUserTable myApp = new AppInstallUserTable();
		myApp.doSomething();
	}
	
	public AppInstallUserTable() throws ClassNotFoundException, SQLException, NoConnectionException {
		dbConn = new MySQLAccess().getConnection();
	}

	public void doSomething() throws ClassNotFoundException, SQLException{
//		this.dropUserTable();
//		this.createUserTable();
		this.createTable();
//		this.deleteFirstUser();
//		this.deleteTestusers();
//		this.insertFirstUser();
//		this.insertMoreUsers();
//		this.insertEinfach();
//		this.insertMoreUsersEinfach();
	}
	public void insertMoreUsers() throws SQLException{
		String sql = "INSERT INTO USER" +
					"(USERID, PASSWORD, ACTIVE, ADMIN, USERNAME, EMAIL)" +
					"VALUES" +
					"(?,?,?,?,?,?)";
		System.out.println(sql);
		PreparedStatement prepStat = dbConn.prepareStatement(sql);
		for(int index = 2; index <= 5; index++){
			String userid   = "testus" + index;
			String password = "geheim";
			String active   = "Y";
			String admin    = "N";
			String username = "Testuser " + index;
			String email    = userid + "@abd.ce";
			prepStat.setString(1, userid);
			prepStat.setString(2, password);
			prepStat.setString(3, active);
			prepStat.setString(4, admin);
			prepStat.setString(5, username);
			prepStat.setString(6, email);
			prepStat.executeUpdate();
			System.out.println(userid + " erfolgreich eingefügt");						
		}
	}
	public void deleteTestusers() throws SQLException{
		String sql = "DELETE FROM USER " +
					"WHERE USERID LIKE ?";
		System.out.println(sql);
		PreparedStatement prepStat = dbConn.prepareStatement(sql);
		prepStat.setString(1, "testus%");
		int rc = prepStat.executeUpdate();
		if (rc == 0){
			System.out.println("kein testus... in der Tabelle");
		}else{
			System.out.println(rc + " testus... erfolgreich gelöscht");
		}
	}
	public void insertMoreUsersEinfach() throws SQLException{
		String sql = "INSERT INTO USER" +
					"(USERID, PASSWORD, ACTIVE, ADMIN, USERNAME, EMAIL)" +
					"VALUES" +
					"('testus6', 'geheim', 'Y', 'N', 'Testuser 6', 'testus6@abc.de')," +
					"('testus7', 'geheim', 'Y', 'N', 'Testuser 7', 'testus7@abc.de')," +
					"('testus8', 'geheim', 'Y', 'N', 'Testuser 8', 'testus8@abc.de')";
		System.out.println(sql);
		Statement stat = dbConn.createStatement();
		stat.executeUpdate(sql);
		System.out.println("testus6 - testus8 erfolgreich eingefügt");
	}
	public void insertEinfach() throws SQLException{
		String sql = "INSERT INTO feiertage" +
					"(DATUM, FEIER)" +
					"VALUES" +					
					"('31.10.2017', 'Reformationstag')," +
					"('01.11.2017', 'Allerheiligen')," +
					"('22.11.2017', 'Buß- und Bettag')," +
					"('25.12.2017', '1. Weihnachtstag')," +
					"('26.12.2017', '2. Weihnachtstag')," +
					"('01.01.2018', 'Neujahr')," +
					"('30.03.2018', 'Karfreitag')," +
					"('01.04.2018', 'Ostersonntag')," +
					"('02.04.2018', 'Ostermontag')," +
					"('01.05.2018', 'Tag der Arbeit')," +
					"('10.05.2018', 'Christi Himmelfahrt')," +
					"('20.05.2018', 'Pfingstsonntag')," +
					"('21.05.2018', 'Pfingstmontag')," +
					"('31.05.2018', 'Fronleichnam')," +											
					"('03.10.2018', 'Tag der deutschen Einheit')," +
					"('01.11.2018', 'Allerheiligen')," +
					"('25.12.2018', '1. Weihnachtstag')," +
					"('26.12.2018', '2. Weihnachtstag')";
		System.out.println(sql);
		Statement stat = dbConn.createStatement();
		stat.executeUpdate(sql);
		System.out.println("Feiertage erfolgreich eingefügt");
	}
	public void deleteFirstUser() throws SQLException{
		String sql = "DELETE FROM USER " +
					"WHERE USERID = ?";
		System.out.println(sql);
		PreparedStatement prepStat = dbConn.prepareStatement(sql);
		prepStat.setString(1, "testus1");
		prepStat.executeUpdate();
		System.out.println("testus1 erfolgreich gelöscht");
	}
	public void insertFirstUser() throws SQLException{
		String sql = "INSERT INTO USER" +
					"(USERID, PASSWORD, ACTIVE, ADMIN, USERNAME, EMAIL)" +
					"VALUES" +
					"(?,?,?,?,?,?)";
		System.out.println(sql);
		PreparedStatement prepStat = dbConn.prepareStatement(sql);
		prepStat.setString(1, "testus1");
		prepStat.setString(2, "geheim");
		prepStat.setString(3, "Y");
		prepStat.setString(4, "N");
		prepStat.setString(5, "Testuser 1");
		prepStat.setString(6, "testus1@abc.de");
		prepStat.executeUpdate();
		System.out.println("testus1 erfolgreich eingefügt");
	}

	public void createUserTable() throws SQLException{
		String sql = "CREATE TABLE USER(" +
						"USERID   CHAR(16)     NOT NULL PRIMARY KEY, " +
						"PASSWORD CHAR(32)     NOT NULL            , " +
						"ACTIVE   CHAR(1)      NOT NULL DEFAULT 'N', " +
						"ADMIN    CHAR(1)      NOT NULL DEFAULT 'N', " +
						"USERNAME VARCHAR(256) NOT NULL            , " +
						"EMAIL    VARCHAR(256) NOT NULL" +
					")";
		System.out.println(sql);

		Statement stmt = dbConn.createStatement();
		stmt.executeUpdate(sql);
		System.out.println("SQL-Befehl erfolgreich ausgeführt");
	}
	
	public void createTable() throws SQLException{
		String sql = "CREATE TABLE buchung(" +
						"BUCHUNGSNUMMER VARCHAR(16) NOT NULL, " +
						"POSITION INT(2) NOT NULL, " +
						"STATUS VARCHAR(16) NOT NULL, " +
						"ZIMMER_ID	VARCHAR(5)	NOT NULL, " +
						"PRIMARY KEY (BUCHUNGSNUMMER,POSITION)" +
					")";
		System.out.println(sql);

		Statement stmt = dbConn.createStatement();
		stmt.executeUpdate(sql);
		System.out.println("SQL-Befehl erfolgreich ausgeführt");
	}
	
	public void dropUserTable() throws SQLException{
		String sql = "DROP TABLE USER";
		System.out.println(sql);

		Statement stmt = dbConn.createStatement();
		stmt.executeUpdate(sql);
		System.out.println("SQL-Befehl erfolgreich ausgeführt");
	}
}
