<%@page import="manage.JavaClass.Zimmer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="manage.JavaClass.Kunde"%>
<%@page import="manage.JavaBean.UserBean"%>
<%@page import="manage.JavaBean.MsgBean"%>
<%@page import="manage.JavaBean.KundenBean"%>
<%@page import="manage.JavaBean.BuchungBean"%>
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
	session.setAttribute("bb", bb);
}
kb = (KundenBean) session.getAttribute("kb");
if(kb == null){
	kb = new KundenBean();
	session.setAttribute("kb", kb);
}

Kunde kunde = new Kunde();

String anrede= request.getParameter("anrede");
String vorname = request.getParameter("vorname");
String nachname = request.getParameter("nachname");
String gdatum = request.getParameter("gdatum");
String telnummer = request.getParameter("telnummer");
String email = request.getParameter("email");
String password = request.getParameter("password");
String strasse = request.getParameter("strasse");
String plz_stadt = request.getParameter("plz_stadt");
String register = request.getParameter("register");
if(register == null) register = "";

if(register.equals("Registrieren")){
	
	//Persönliche Daten der Kunde Objekt füllen
	kunde.setAnrede(anrede);
	kunde.setVorname(vorname);
	kunde.setNachname(nachname);
	kunde.setGdatum(gdatum);
	kunde.setTelnummer(telnummer);
	kunde.setAdresse(strasse + ", " + plz_stadt);
	//Kunde VAriable in KundenBean speichern
	if(kunde.getVorname() == ""){
		msg.setPersonAttribute("vorname");
		response.sendRedirect("./ViewStart.jsp");
	}else if(kunde.getNachname() == ""){
		msg.setPersonAttribute("nachname");
		response.sendRedirect("./ViewStart.jsp");
	}else if(kunde.getGdatum() == ""){
		msg.setPersonAttribute("gdatum");
		response.sendRedirect("./ViewStart.jsp");
	}else if(kunde.getTelnummer() == ""){
		msg.setPersonAttribute("telnummer");
		response.sendRedirect("./ViewStart.jsp");
	}else if(strasse == ""){
		msg.setPersonAttribute("strasse");
		response.sendRedirect("./ViewStart.jsp");
	}else if(plz_stadt == ""){
		msg.setPersonAttribute("PLZ_Stadt");
		response.sendRedirect("./ViewStart.jsp");
	}else {
		kb.setKunde(kunde);
		if(kb.insertKundeIfNotExists()){
			msg.setRegSuccess(kunde.getNachname());
		}else{
			msg.setAlreadyExists(kunde.getNachname());
			
			}	

		//BenutzerDaten der Kunde Objekt füllen
		kunde.setKnr(kb.getNummerZahl());
		user.setKunde(kunde);
		user.setEmail(email);
		user.setPassword(password);
		if(user.insertUserIfNotExists()){
			msg.setRegSuccess(kunde.getNachname());
		}else{
			msg.setAlreadyExists(email);
			
			}
		
		response.sendRedirect("./ViewStart.jsp");
	}
	
} else
	response.sendRedirect("./ViewGastDaten.jsp");
%>
</body>
</html>