package manage.JavaBean;

public class MsgBean {
	
	String infoMsg;
	String actionMsg;
	String timeErrorMsg;
	
	public MsgBean() {		
		this.setGeneralWelcome();
	}
	
	public void setLoginSuccess(){
		this.setInfoMsg("Sie sind eingeloggt, Glückwunsch");
		this.setActionMsg("");
	}
	public void setLoginFailed(){
		this.setInfoMsg("Deine Anmeldung hat leider nicht geklappt");
		this.setActionMsg("Versuche es nochmal, Registrieren wenn noch nicht getan");
	}
	public void setUseridLeer(){
		this.setInfoMsg("Dein Nickname muss mindestens ein Zeichen lang sein");
		this.setActionMsg("Wähle einen anständigen Nickname");
	}	
	public void setGeneralWelcome(){
		this.setInfoMsg("Ihr HS LU Hotel: Reservieren Sie!");
		this.setActionMsg("Bitte registrieren Sie sich oder loggen Sie sich ein!");
		this.setTimeErrorMsg("");
	}
	public void setLoginFirst(){
		this.setActionMsg("Bitte Logen Sie sich zuerst!");
	}
	public void setSystemfehler(){
		this.setInfoMsg("Es ist ein Fehler aufgetreten");
		this.setActionMsg("Sage Deinem Admin Bescheid");
	}
	public void setLogin(){
		this.setInfoMsg("Logen Sie sich erstmal bitte");
		this.setActionMsg("Erst dann ausloggen");
	}
	public void setZimmerNotAvailable(String zimmertyp){
		this.setInfoMsg("Es gibt keine " + zimmertyp + " mehr zur Verfügung");
		this.setActionMsg("Selektieren Sie was Anderes!");
	}
	public void setAlreadyExists(String email){
		this.setInfoMsg("Die Email " + email + " ist schon vergeben");
		this.setActionMsg("Geben Sie eine andere Email ein");
	}
	public void setRegSuccess(String nachname_email){
		this.setInfoMsg("Super, " + nachname_email + ", Du hast Dich erfolgreich registriert");
		this.setActionMsg("geh jetzt zum Login");
	}
	public void setPersonAttribute(String personAttribut){
		this.setInfoMsg("Bitte, " + personAttribut + " Bereich auffüllen");
		this.setActionMsg("Es darf nicht leer sein");
	}
	public void setlogout(){
		this.setInfoMsg("Sie haben sich ausgeloggt");
		this.setActionMsg("bis bald");
	}
	public void setUhrzeitVonFehlt(){
		this.setTimeErrorMsg("Bitte Uhrzeit im Format: dd.mm.yyyy angeben!");
	}
	public void setUhrzeitBisFehlt(){
		this.setTimeErrorMsg("Bitte Uhrzeit im Format: dd.mm.yyyy angeben!");
	}
	
	public String getInfoMsg() {
		return infoMsg;
	}

	public void setInfoMsg(String infoMsg) {
		this.infoMsg = infoMsg;
	}

	public String getActionMsg() {
		return actionMsg;
	}

	public void setActionMsg(String actionMsg) {
		this.actionMsg = actionMsg;
	}
	
	public String getTimeErrorMsg() {
		return timeErrorMsg;
	}

	public void setTimeErrorMsg(String timeErrorMsg) {
		this.timeErrorMsg = timeErrorMsg;
	}

}
