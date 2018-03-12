package manage.JavaClass;

public class Zimmer {

	String zID;
	String ztyp;
	String groesse;
	double preis;
	
//	ArrayList<User> belegungPersonen;
	
	public Zimmer() {
		super();
	}

	public Zimmer(String zID, String ztyp, String groesse, double preis) {
		super();
		this.zID = zID;
		this.ztyp = ztyp;
		this.groesse = groesse;
		this.preis = preis;
	}

	public String getzID() {
		return zID;
	}

	public void setzID(String zID) {
		this.zID = zID;
	}

	public String getZtyp() {
		return ztyp;
	}
	public void setZtyp(String ztyp) {
		this.ztyp = ztyp;
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
	public String getZimmeralsHtml(){
		return			    
					"<li>" + 
			    		"<label>" +	
			    			"<input type= \"checkbox\" name=\"zimmerfrei\" value = \""+this.toCopyString()+"\">" +
			    			"Zimmer:" + this.zID + ",Typ:" + this.ztyp + ",Groesse:" 
							+ this.groesse + ",Preis:" + this.preis  + " €" + 
			    		"</label>" +
			    	"</li>" + "</br>"; 		
	}
	public String getWKZimmeralsHtml(String von, String bis) {		
		String html = "<p>" + "Zimmer:" + this.zID + " Typ:" + this.ztyp + " Preis:" 
							+ this.preis  + " €" + " Ankunft:" + von + " Abreise:" + bis 
					+ "</p>" ;
		return html; 
	}

	public String getZiAlsHtml() {
		String html = "<p>" + "Zimmer:" + this.zID + " Typ:" + this.ztyp + " Preis:" 
							+ this.preis + " €" + "</p>";
		return html;
	}
	public String getZahlAndZiAlsHtml(int zahl) {
		String html = "<p>" + zahl + "x " + this.ztyp + " Preis: je " 
							+ this.preis + " €" + "</p>";
		return html;
	}
	public String getAAAlsHtml(String zeit_von, String zeit_bis) {
		String html = "<p>" + "Abfahrt:" + zeit_von + "</p>" 
					+ "<p>" + "Ankunft:" + zeit_bis + "</p>" ;
		return html;
	}

	public String toHtmlZeile(){
		String html = "<tr>\n";
//		html += "	<td>" + this.name   + "</td>\n";
//		html += "	<td>" + this.nachname + "</td>\n";
//		html += "	<td>" + this.passwort + "</td>\n";
//		html += "	<td>" + this.adresse    + "</td>\n";
//		html += "	<td>" + this.telefonnummer    + "</td>\n";
//		html += "	<td>" + this.email    + "</td>\n";
//		html += "	<td>" + this.iban    + "</td>\n";
//		html += "	<td>" + this.bic    + "</td>\n";
		html += "</tr>\n";
		return html;
	}

//	public String getBelegung(String zvon, String zbis) {
//		String string = "";
//		if(zvon == "00:00" && zbis == "00:00"){
//			this.setIstBelegt("NEIN");
//			string = "NEIN";
//			System.out.println("ZImmer nicht belegt");
//		} else {
//			this.setIstBelegt("JA");
//			System.out.println("Zimmer belegt");
//			string = "JA";
//		}
//		return string;
//	}

	@Override
	public String toString() {
		return "Zimmer [zID=" + zID + ", ztyp=" + ztyp + ", groesse=" + groesse + ", preis=" + preis + "]";
	}
	public String toCopyString() {
		return "Zimmer:" + this.zID + ",Typ:" + this.ztyp + ",Groesse:" 
				+ this.groesse + ",Preis:" + this.preis;
	}
}
