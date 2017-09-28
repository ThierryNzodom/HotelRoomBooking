package manage.JavaClass;

public class User {
	String email;
	String password;
//	String userid;
//	String active;
//	String admin;
//	String username;


//	String schema;

	public User(){
		
	}
	
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
//		this.userid = userid;
//		this.active = active;
//		this.admin = admin;
//		this.username = username;

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
}
