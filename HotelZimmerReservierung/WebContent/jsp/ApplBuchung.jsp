<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="manage.JavaClass.RechnungToClient"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="manage.JavaClass.Buchung"%>
<%@page import="manage.JavaClass.Zimmer"%>
<%@page import="manage.JavaClass.Kunde"%>
<%@page import="manage.JavaClass.Rechnung"%>
<%@page import="manage.JavaClass.RechnungToClient"%>
<%@page import="manage.JavaBean.KundenBean"%>
<%@page import="manage.JavaBean.UserBean"%>
<%@page import="manage.JavaBean.MsgBean"%>
<%@page import="manage.JavaBean.BuchungBean"%>
<%@page import="manage.JavaBean.KundenBean"%>
<%@page import="manage.JavaBean.WarenkorbBean"%>
<%@page import="manage.JavaBean.SendEmailBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PuserLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="user" class="manage.JavaBean.UserBean" scope="session"/>
<jsp:useBean id="msg" class="manage.JavaBean.MsgBean" scope="session"/>
<jsp:useBean id="bb" class="manage.JavaBean.BuchungBean" scope="session"/>
<jsp:useBean id="kb" class="manage.JavaBean.KundenBean" scope="session"/>
<jsp:useBean id="wkbean" class="manage.JavaBean.WarenkorbBean" scope="session"/>
<%
user = (UserBean) session.getAttribute("user");
if(user == null){
	user = new UserBean();
	session.setAttribute("user", user);
}
msg = (MsgBean) session.getAttribute("msg");
if(msg == null){
	msg = new MsgBean();
	session.setAttribute("msg", msg);
}
bb = (BuchungBean) session.getAttribute("bb");
if(bb == null){
	bb = new BuchungBean();
	session.setAttribute("bb", bb);
}
kb = (KundenBean) session.getAttribute("kb");
if(kb == null){
	kb = new KundenBean();
	session.setAttribute("kb", kb);
}
wkbean = (WarenkorbBean) session.getAttribute("wkbean");
if(wkbean == null){
	wkbean = new WarenkorbBean();
	session.setAttribute("wkbean", wkbean);
}

ArrayList<Zimmer> wkZimmerList = new ArrayList<Zimmer>();
wkZimmerList = wkbean.getZimmerArrayList();

//Email Setting from Service offer.
/*  final String emailSMTPserver = "smtp.gmail.com";
final String emailServerPort = "465"; */

String zurueck = request.getParameter("zurueck");
String verbindBuchen = request.getParameter("verbindBuchen");

if(zurueck == null) zurueck = "";
if(verbindBuchen == null) verbindBuchen = "";

if(zurueck.equals("Zurück")){
	
	
	response.sendRedirect("./ViewGastDaten.jsp");
} else if (verbindBuchen.equals("Verbindlich buchen")){
	
	//Buchunug bzw. Bündelung mit Buchungsnummer erstellen. (Insert in DB)
	Buchung buchung = bb.getBuchung();
	Kunde kunde = kb.getKunde();
	//Get letzte Nummerzahl from DB
	int letzeBuchungzahl = bb.getNummerZahl();
	int nummerzahl = letzeBuchungzahl+1;
	String teilBuchungsnummer = buchung.toStringTeilBuchungsnummer(kunde.getVorname(), kunde.getNachname());
	//Set Buchungsnummer für die Buchung
	buchung.setBuchungsnummer(nummerzahl+teilBuchungsnummer);	
	
	//Zieht die Positionen, Setzt die ZID
	Iterator<Zimmer> iter = wkZimmerList.iterator();
	int i = 0;
	 while (iter.hasNext()) {
		i++;	
		 Zimmer zimmer = (Zimmer) iter.next();
		 String zimmerID = zimmer.getzID();
			int buchungPos = i;
			String ausgabeListe = "Position:" + buchungPos + " ZimmerID:" + zimmerID + " ";
			buchung.setPosition(buchungPos);
			buchung.setZiD(zimmerID);
			bb.setBuchung(buchung);
			bb.insertBuchung();
		}
	
	//insert Kunde in der DB
 	if(kb.isLoggedIn() == false){
		kb.insertKundeNoCheck();
	}
	
	//Rechnung mit Rechungnunner erstellen (Insert in DB)
	Rechnung rechnung = new Rechnung();
	int letzKnrFromDB = kb.getNummerZahl();
	kunde.setKnr(letzKnrFromDB);
	rechnung.setBuchung(buchung);
	rechnung.setKunde(kunde);
	rechnung.setGesamtpreis(kb.getAuftragsumme());
	//Get letzte Nummerzahl from DB
	int letzeRechnungzahl = rechnung.getNummerZahlRechnung();
	int nummerzahlRechnung = letzeRechnungzahl+1;
	String teilRechnungssnummer = rechnung.toStringTeilRechungsnummer(kunde.getVorname(), kunde.getNachname());
	//Set Buchungsnummer für die Buchung
	rechnung.setRechnungsnummer(nummerzahlRechnung+teilRechnungssnummer);
	rechnung.insertRechnung();
	
	//Rechnung als PDF erstellen
	String rechnungsnummer = rechnung.getRechnungsnummer();
	String kundeName = kb.getKunde().getVorname()+" "+kb.getKunde().getNachname();
	Double gesamtpreis = kb.getAuftragsumme();
	String[] k_adr = kb.getKunde().getAdresse().split(",");
	String k_adresse = k_adr[0];
	String k_ort = k_adr[1];
	int anzahlNacht = bb.getBuchung().anzahlUebernachtung(buchung.getZeit_von(), buchung.getZeit_bis());
	RechnungToClient rechnungToClient = new RechnungToClient(rechnungsnummer, kundeName, wkZimmerList, gesamtpreis, k_adresse, k_ort, anzahlNacht);
	
	
	//Email Senden (Attachement: Rechnung.pdf)
	String toEmail =  kb.getKunde().getEmail();
		String emailSubject = "Ihre Rechnung zur Zimmer Reservierung";
		String emailBody = "Guten Tag "+kb.getKunde().getTitel()+" "+kb.getKunde().getNachname()+","+ " \n"
				+ "anbei erhalten Sie Ihre Rechnung für Ihre Buchung in unserem Hotel HS LU Hotel. \n"
				+ "Vielen Dank für Ihr Vertrauen. \n"
				+ "Wir freuen uns darauf Sie bei uns begrüßen zu dürfen. \n"
				+ "Mit freundlichen Grüßen, \n"
				+ "Ihr Hotelteam \n"
				+ "+49(0)12345678901 \n"
				+ "greatforother@gmail.com";
	SendEmailBean sendEmailBean = new SendEmailBean(toEmail, emailSubject, emailBody, rechnungsnummer);		
	
	response.sendRedirect("./ViewNachricht.jsp");	
} else {
	response.sendRedirect("./ViewBuchung.jsp");
}

%>
</body>
</html>