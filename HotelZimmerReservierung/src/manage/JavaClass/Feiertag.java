package manage.JavaClass;

public class Feiertag {
	
	String datum;
	String feier;
	
	public Feiertag() {
		super();
	}
	public Feiertag(String datum, String feier) {
		super();
		this.datum = datum;
		this.feier = feier;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public String getFeier() {
		return feier;
	}
	public void setFeier(String feier) {
		this.feier = feier;
	}
	@Override
	public String toString() {
		return "feiertag [datum=" + datum + ", feier=" + feier + "]";
	}
	
	

}
