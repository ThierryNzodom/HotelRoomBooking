<%@page import="java.util.Date"%>
<%@page import="manage.JavaClass.User"%>
<%@page import="manage.JavaClass.Buchung"%>
<%@page import="manage.JavaClass.Kunde"%>
<%@page import="manage.JavaBean.UserBean"%>
<%@page import="manage.JavaBean.MsgBean"%>
<%@page import="manage.JavaBean.BuchungBean"%>
<%@page import="manage.JavaBean.KundenBean"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.GregorianCalendar"%>

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
	session.setAttribute("bb", msg);
}
kb = (KundenBean) session.getAttribute("kb");
if(kb == null){
	kb = new KundenBean();
	session.setAttribute("kb", kb);
}

String email = request.getParameter("email");
String password = request.getParameter("password");
String datumVon = request.getParameter("datumVon");
String timeVon = request.getParameter("timeVon");
String datumBis = request.getParameter("datumBis");
String timeBis = request.getParameter("timeBis");
String zumlogin = request.getParameter("zumlogin");
if(zumlogin == null) zumlogin = "null";
String registrieren = request.getParameter("registrieren");
if(registrieren == null) registrieren = "null";
String zurBuchung = request.getParameter("zurBuchung");
if(zurBuchung == null) zurBuchung = "null";

Date buchungdt = new Date();
SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY");
String buchungsDate = sdf.format(buchungdt);

//Login
if(registrieren.equals("Registrieren")){
	
	response.sendRedirect("./ViewGastDaten.jsp?comeFrom=ApplStart");
	
} else if(zumlogin.equals("Zum Login")){
	if(email == "" || password == "") {
		msg.setActionMsg("Fügen Sie bitte zuersmal ein Email oder Password ein!");
		response.sendRedirect("./ViewStart.jsp");
	} else {
	user.setEmail(email);
	user.setPassword(password);
	try{
		boolean rc = user.checkEmailPassword();
		if (rc) {//email/pw passt
			user.setLogIn(true);
			user.setLoggedIn(1);
			int knr = user.loginKnrUser();
			
			//Kunde mit Knr = ? aus DB holen
			Kunde kunde = new Kunde();
			kunde = kb.getAngemeldeteKunde(knr);
			kunde.setKnr(knr);
			kb.setKunde(kunde); // kunde in kb speichern
			
			msg.setLoginSuccess();
			response.sendRedirect("./ViewStart.jsp");
		}else{//email/pw passt nicht
			user.setLogIn(false);
			msg.setLoginFailed();
			response.sendRedirect("./ViewStart.jsp");
		}
	}catch(Exception e){
		e.printStackTrace();
		msg.setSystemfehler();
		response.sendRedirect("./ViewStart.jsp");
	}
}

} else if(zurBuchung.equals("Zur Buchung gehen")){
	if (!user.isLogIn()){
		msg.setActionMsg("Bitte Logen Sie sich zuerst!");
		response.sendRedirect("./ViewStart.jsp");
		//response.sendRedirect("./ViewBelegung.jsp?comeFrom=ViewStart");
	}else if(!bb.zeitCheck(datumVon, datumBis)){
		//Prüfen ob Zeitvon before ZeitBis ist
		msg.setActionMsg("Abreisedatum immer nach Ankunkfsdatum, bitte Datum ändern!");
		response.sendRedirect("./ViewStart.jsp");
	}else{
		// Zeitspanne abfangen
		Buchung buchung = new Buchung();
		buchung.setStatus("aktiv");
		buchung.setZeit_von(datumVon);
		buchung.setZeit_bis(datumBis);
		buchung.setBdatum(buchungsDate);
		bb.setBuchung(buchung);
		
		response.sendRedirect("./ViewBelegung.jsp?comeFrom=ApplStart");	
	}
	
} else {
	response.sendRedirect("./ViewStart.jsp");
}
%>
</body>
</html>