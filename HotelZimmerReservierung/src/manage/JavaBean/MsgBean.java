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
		this.setActionMsg("Ihre Daten sehen wie folgt an:");
	}
	public void setLoginFailed(){
		this.setInfoMsg("Deine Anmeldung hat leider nicht geklappt");
		this.setActionMsg("Versuche es nochmal, Registrieren wenn noch nicht getan.");
	}
	public void setUseridLeer(){
		this.setInfoMsg("Dein Nickname muss mindestens ein Zeichen lang sein");
		this.setActionMsg("Wähle einen anständigen Nickname");
	}	
	public void setGeneralWelcome(){
		this.setInfoMsg("Ihr WI_Master Hotel: Zimmer Reservierung!");
		this.setActionMsg("Bitte registieren Sie sich oder loggen Sie sich ersmal ein");
		this.setTimeErrorMsg("");
	}
	public void setLoginFirst(){
		this.setInfoMsg("Bitte Logen Sie sich zuerst!");
		this.setActionMsg("Sonst können Sie nicht auf diese Tabreiter");
	}
	public void setSystemfehler(){
		this.setInfoMsg("Es ist ein Fehler aufgetreten");
		this.setActionMsg("Sage Deinem Admin Bescheid");
	}
	public void setAlreadyExists(String email){
		this.setInfoMsg("Die Email " + email + " ist schon vergeben");
		this.setActionMsg("Wähle einen anderen Nickname aus");
	}
	public void setRegSuccess(String email){
		this.setInfoMsg("Super, " + email + ", Du hast Dich erfolgreich registriert");
		this.setActionMsg("geh jetzt zum Login");
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
