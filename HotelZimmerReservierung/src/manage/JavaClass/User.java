package manage.JavaClass;

public class User {
	String userid;
	String password;
	String active;
	String admin;
	String username;
	String email;
//	int telnummer;
//	String adresse;
//	Date gdatum;
//	String anrede;
//	String vorname;
//	String nachname;
//	String schema;

	public User(){
		
	}
	
	public User(String userid, String password, String active,
			         String admin, String username, String email) {
		super();
		this.userid = userid;
		this.password = password;
		this.active = active;
		this.admin = admin;
		this.username = username;
		this.email = email;
	}

	public String toString(){
		String s = "UserBean mit ";
				s+= "userid: " + this.getUserid() + ", ";
				s+= "password: " + this.getPassword() + ", ";
				s+= "active: " + this.getActive() + ", ";
				s+= "admin: " + this.getAdmin() + ", ";
				s+= "username: " + this.getUsername() + ", ";
				s+= "email: " + this.getEmail();				
		return s;
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
}
