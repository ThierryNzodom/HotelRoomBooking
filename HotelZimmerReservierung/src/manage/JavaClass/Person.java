package manage.JavaClass;

public class Person {

	String vorname;
	String nachname;
	
	public Person() {
		super();
	}
	public Person(String vorname, String nachname) {
		super();
		this.vorname = vorname;
		this.nachname = nachname;
	}
	
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	@Override
	public String toString() {
		return "Person [vorname=" + vorname + ", nachname=" + nachname + "]";
	}
	
	
}
