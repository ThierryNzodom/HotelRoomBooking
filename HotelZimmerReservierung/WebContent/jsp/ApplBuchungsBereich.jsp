<%@page import="manage.JavaClass.Rechnung"%>
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
<jsp:useBean id="rb" class="manage.JavaClass.Rechnung" scope="session"/>

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
	rb = (Rechnung) session.getAttribute("rb");
	if(rb == null){
		rb = new Rechnung();
		session.setAttribute("rb", rb);
	}
	
	Kunde kunde = new Kunde();

	ArrayList<Buchung> buchList;
	
	//BuchungsBereich
	String rechnr = request.getParameter("rechnr");
	//Button BuchungsBereich
	String bsuchen = request.getParameter("bsuchen");
	if(bsuchen == null) bsuchen = "";
	String stornierung = request.getParameter("stornierung");	
	if(stornierung == null) stornierung = "";
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
}else if(bsuchen.equals("Suchen")){
	if(rechnr == ""){
		msg.setBnrEinagbe();
		response.sendRedirect("./ViewBuchungsBereich.jsp");
	}else{
		rb.setRechnungsnummer(rechnr);
		String myString = "";
		String kplzO = "";
		String kstrasse = "";
		Buchung buchung = new Buchung();
		Kunde bkunde = new Kunde();
		buchList = bb.getBuchungKundeFromRnr(rechnr);
		myString = bb.getMyString(buchList);
		if(myString==""){
			msg.setActionMsg("Geben Sie bitte die richtige Rechnungsnummer an.");
			response.sendRedirect("./ViewBuchungsBereich.jsp");
		}else{
		bb.setStringZimmer(myString);
		buchung = bb.getBuchung();
		bkunde = bb.getKunde();
		kb.setKunde(bkunde);
		
		bb.setBuchnr(buchung.getBuchungsnummer());bb.setBuchdatum(buchung.getBdatum());
		kb.setAuftragsumme(bkunde.getAuftragsumme());bb.setZvon(buchung.getZeit_von());
		bb.setZbis(buchung.getZeit_bis());kb.setKundenname(bkunde.getAnrede()+" "+bkunde.getVorname()+" "+bkunde.getNachname());
		
		kb.setAdresse(bkunde.getAdresse());
		String[] kdadresse = kb.getAdresse().split(",");
		kstrasse = kdadresse[0];
		kb.setStrasse(kstrasse);
		kplzO = kdadresse[1];
		kb.setPlzO(kplzO);
		response.sendRedirect("./ViewBuchungsBereich.jsp");
		}	
	}
	
}else if(stornierung.equals("Buchung Stornieren")){
	if(bb.getBuchnr()== "" || bb.getBuchdatum()== ""){
		msg.setBnrEinagbe();
		response.sendRedirect("./ViewBuchungsBereich.jsp");
	}else {
 		rb.stornierung();
		bb.updateStatusBuchung(bb.getBuchnr());
		msg.setActionMsg("Buchung erfolgreich stoniert.");
		bb.setBuchnr("");bb.setBuchdatum("");bb.setStringZimmer("");bb.setZvon("");bb.setZbis("");
		kb.setAdresse("");kb.setAuftragsumme(0);kb.setKundenname("");kb.setStrasse("");kb.setPlzO("");
		response.sendRedirect("./ViewBuchungsBereich.jsp");	
	}
	
}else
	response.sendRedirect("./ViewBuchungsBereich.jsp");
%>
</body>
</html>