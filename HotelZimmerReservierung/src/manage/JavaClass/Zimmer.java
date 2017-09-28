package manage.JavaClass;


public class Zimmer {

	String zID;
	String zeit_von;
	String zeit_bis;
	String groesse;
	double preis;
	String istBelegt;
	
	Zimmertyp ztyp;
//	ArrayList<User> belegungPersonen;
	
	public Zimmer() {
		super();
	}

	public Zimmer(String zID, Zimmertyp ztyp, String zeit_von,
			String zeit_bis, String groesse, double preis, String istBelegt) {
		super();
		this.zID = zID;
		this.ztyp = ztyp;
		this.zeit_von = zeit_von;
		this.zeit_bis = zeit_bis;
		this.groesse = groesse;
		this.preis = preis;
		this.istBelegt = istBelegt;
	}

	public String getzID() {
		return zID;
	}

	public void setzID(String zID) {
		this.zID = zID;
	}

	public String getZtyp(String zÍD) {		
		String typ = "";
		if(zID.contains("CL")){
			ztyp = Zimmertyp.Einzelzimmer;
			typ = "Einzelzimmer";
		}else if(zID.contains("SD")){
			ztyp = Zimmertyp.Doppelzimmer;
			typ = "Doppelzimmer";
		}else if(zID.contains("CD")){
			ztyp = Zimmertyp.Suite;
			typ = "Suite";
		}
		return typ;
	}

	public String getZeit_von() {
		return zeit_von;
	}

	public void setZeit_von(String zeit_von) {
		this.zeit_von = zeit_von;
	}

	public String getZeit_bis() {
		return zeit_bis;
	}

	public void setZeit_bis(String zeit_bis) {
		this.zeit_bis = zeit_bis;
	}

	public void setGroesse(String groesse) {
		this.groesse = groesse;
	}
	
	public String getGroesse() {
		return groesse;
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}
	
	public String getIstBelegt() {
		return istBelegt;
	}

	public void setIstBelegt(String istBelegt) {
		this.istBelegt = istBelegt;
	}

	@Override
	public String toString() {
		return "Zimmer [zID=" + zID + ", ztyp=" + ztyp + ", zeit_von=" + zeit_von + ", zeit_bis="
				+ zeit_bis + ", groesse=" + groesse + ", preis=" + preis + ", istBelegt=" + istBelegt
				+ "]";
	}
}
