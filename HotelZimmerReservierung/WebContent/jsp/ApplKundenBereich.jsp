<%@page import="java.util.ArrayList"%>
<%@page import="manage.JavaClass.Buchung"%>
<%@page import="manage.JavaClass.Kunde"%>
<%@page import="manage.JavaBean.MsgBean"%>
<%@page import="manage.JavaBean.UserBean"%>
<%@page import="manage.JavaBean.BuchungBean"%>
<%@page import="manage.JavaBean.KundenBean"%>
<%@page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.GregorianCalendar"%>
<%@ page import="java.util.Calendar"%>
<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html PuserLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/Projekt.css" />
<script type="text/javascript" src="projekt.js"></script>
<script src="../js/datePicker.js"></script>
<style type="text/css">
@import "http://ajax.googleapis.com/ajax/libs/dojo/1.5/dijit/themes/claro/claro.css";
</style>
<script src="http://ajax.googleapis.com/ajax/libs/dojo/1.6/dojo/dojo.xd.js"></script>
<script>
dojo.ready(function(){
  dojo.byId("greeting").innerHTML += ", from " + dojo.version;
});
</script>
</head>
<body>
<!-- HTML-Kommentare laden beim Client ISO-8859-1-->
<%-- Java-Kommentare laden nicht beim Client lazozure@gmail.com heritage --%>
<%= new java.util.Date() %>
<jsp:useBean id="user" class="manage.JavaBean.UserBean" scope="session"/>
<jsp:useBean id="msg" class="manage.JavaBean.MsgBean" scope="session"/>
<jsp:useBean id="bb" class="manage.JavaBean.BuchungBean" scope="session"/>
<jsp:useBean id="kb" class="manage.JavaBean.KundenBean" scope="session"/>

<%user = (UserBean) session.getAttribute("user");
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
		session.setAttribute("bb", msg);
	}
	kb = (KundenBean) session.getAttribute("kb");
	if(kb == null){
		kb = new KundenBean();
		session.setAttribute("kb", kb);
	}
	
	Kunde kunde = new Kunde();
	String myString = "";
	ArrayList<Buchung> buchList;
	
	//KundenBereich
	String kundenMail = request.getParameter("kundenMail");
	String kanrede = request.getParameter("kanrede");
	String kvorname = request.getParameter("kvorname");
	String knachname = request.getParameter("knachname");
	String kdmail = request.getParameter("kdmail");
	String kgdatum = request.getParameter("kgdatum");
	String ktelnummer = request.getParameter("ktelnummer");
	String kstrasse = request.getParameter("kstrasse");
	String kplzO = request.getParameter("kplzO");
	String[] seldata = request.getParameterValues("seldata");

	//Button KundenBereich
	String ksuchen = request.getParameter("ksuchen");
	if(ksuchen == null) ksuchen = "";
	String aendern = request.getParameter("aendern");
	if(aendern == null) aendern = "";
	String loeschen = request.getParameter("loeschen");
	if(loeschen == null) loeschen = "";
	String logout = request.getParameter("logout");
	if(logout == null) logout = "";
	
	 if(logout.equals("Logout")){
			if(!user.isLogIn()){
				msg.setLogin();
				response.sendRedirect("./ViewStart.jsp");
			}else{
				user.setLogIn(false);
				user.logoutUser();
				msg.setlogout();
				response.sendRedirect("./ViewStart.jsp");	
			} 
	}else if(ksuchen.equals("Suchen")){
	if(kundenMail==""){
		msg.setEmailEinagbe();
		response.sendRedirect("./ViewKundenBereich.jsp");
	}else if(!user.checkUExists(kundenMail)){
		msg.setInfoMsg("Sorry!");
		msg.setActionMsg("Kunde mit Email: " + kundenMail + " ist noch nicht registriert.");
		response.sendRedirect("./ViewKundenBereich.jsp");
	}else{
		kunde = user.getKundeFromEmail(kundenMail);
		kb.setAnrede(kunde.getAnrede());kb.setVorname(kunde.getVorname());
		kb.setNachname(kunde.getNachname());kb.setGdatum(kunde.getGdatum());
		kb.setTelnummer(kunde.getTelnummer());kb.setAdresse(kunde.getAdresse());
		kb.setMail(kundenMail);
		String[] kdadresse = kb.getAdresse().split(",");
			kstrasse = kdadresse[0];
			kb.setStrasse(kstrasse);
			kplzO = kdadresse[1].trim();
			kb.setPlzO(kplzO);
		kb.setKunde(kunde);
		response.sendRedirect("./ViewKundenBereich.jsp");	
	}	
}else if(aendern.equals("Update")){	
	if(kb.getKunde().getNachname() == ""){
		msg.setEmailEinagbe();
		response.sendRedirect("./ViewKundenBereich.jsp");
	}else{
	kunde.setAnrede(kb.getAnrede());kunde.setVorname(kb.getVorname());kunde.setNachname(kb.getNachname());
	kunde.setGdatum(kb.getGdatum());kunde.setTelnummer(kb.getTelnummer());
	kunde.setAdresse(kb.getStrasse()+","+kb.getPlzO());
	kb.setKunde(kunde);
	/* kb.setAnrede(kunde.getAnrede());kb.setVorname(kunde.getVorname());
	kb.setNachname(kunde.getNachname());kb.setGdatum(kunde.getGdatum());
	kb.setTelnummer(kunde.getTelnummer());kb.setAdresse(kunde.getAdresse());
	kb.setMail(kundenMail);*/
	/* String[] kdadresse = kb.getAdresse().split(",");
		kstrasse = kdadresse[0];
		kb.setStrasse(kstrasse + ", ");
		kplzO = kdadresse[1];
		kb.setPlzO(kplzO); */
		kb.updateKundeInDB();
		msg.setInfoMsg("Geschafft!");
		msg.setActionMsg("Kunde " + kb.getNachname() + " Erfolgreich aktualisiert");
		kb.setAnrede("");kb.setVorname("");kb.setNachname("");kb.setMail("");
		kb.setGdatum("");kb.setStrasse("");kb.setPlzO("");kb.setTelnummer("");
		response.sendRedirect("./ViewKundenBereich.jsp");
	}	
}else if(loeschen.equals("Loeschen")){
	if(kb.getKunde().getNachname() == ""){
		msg.setEmailEinagbe();
		response.sendRedirect("./ViewKundenBereich.jsp");
	}else{
		kunde = user.getKundeFromEmail(kb.getMail());
		
		user.loescheEmail(kb.getMail());
		kb.loescheKunde(kb.getNachname(), kb.getGdatum());
		
		msg.setInfoMsg("Geschafft!");
		msg.setActionMsg("Kunde erfolgreich gelöscht.");
		
		kb.setAnrede("");kb.setVorname("");kb.setNachname("");
		kb.setGdatum("");kb.setMail("");
		kb.setStrasse("");kb.setPlzO("");kb.setTelnummer("");
		response.sendRedirect("./ViewKundenBereich.jsp");
	}
	
}else
	response.sendRedirect("./ViewKundenBereich.jsp");

%>
</body>
</html>