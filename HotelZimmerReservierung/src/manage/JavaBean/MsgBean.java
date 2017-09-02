package manage.JavaBean;

public class MsgBean {
	
	String infoMsg;
	String actionMsg;
	
	public MsgBean() {		
		this.setGeneralWelcome();
	}
	
	public void setLoginSuccess(){
		this.setInfoMsg("Sie sind eingeloggt, Glückwunsch");
		this.setActionMsg("Ihre Daten sehen wie folgt an:");
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
		this.setInfoMsg("Willkommen zur JSP Anwendung");
		this.setActionMsg("Bitte registirern Sie sich oder loggen Sie sich ein");
	}
	
	public void setSystemfehler(){
		this.setInfoMsg("Es ist ein Fehler aufgetreten");
		this.setActionMsg("Sage Deinem Admin Bescheid");
	}
	public void setAlreadyExists(String userid){
		this.setInfoMsg("Der Nickname " + userid + " ist schon vergeben");
		this.setActionMsg("Wähle einen anderen Nickname aus");
	}
	public void setRegSuccess(String userid){
		this.setInfoMsg("Super, " + userid + ", Du hast Dich erfolgreich registriert");
		this.setActionMsg("geh jetzt zum Login");
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

}
