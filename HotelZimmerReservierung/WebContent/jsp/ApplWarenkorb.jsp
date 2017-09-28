<%@page import="com.itextpdf.text.List"%>
<%@page import="manage.JavaClass.Zimmer"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="manage.JavaBean.UserBean"%>
<%@page import="manage.JavaBean.MsgBean"%>
<%@page import="manage.JavaBean.ZimmerVerwaltungBean"%>
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
<jsp:useBean id="zbean" class="manage.JavaBean.ZimmerVerwaltungBean" scope="session"/>
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
zbean = (ZimmerVerwaltungBean) session.getAttribute("zbean");
if(zbean == null){
	zbean = new ZimmerVerwaltungBean();
	session.setAttribute("zbean", zbean);
}

Zimmer zimmer = new Zimmer();
//Lis myLIst = new List();

String datumVon = request.getParameter("datumVon");
String timeVon = request.getParameter("timeVon");
String datumBis = request.getParameter("datumBis");
String timeBis = request.getParameter("timeBis");
String anzahlZimmer = request.getParameter("anzahlZimmer");
String zimmergroesse = request.getParameter("zimmergroesse");
String anzahlkinder = request.getParameter("anzahlkinder");
String zimmerVerfuegbar = request.getParameter("zimmerVerfuegbar");
if(zimmerVerfuegbar == null) zimmerVerfuegbar = "";

if(zimmerVerfuegbar.equals("Zur Buchung")){
	//Prüfen ob Zeitvon before ZeitBis ist
	if(zbean.zeitCheck(datumVon, datumBis) == false){};
	//Suche nach anzahl Personen
	if(zimmergroesse == "1"){
		zbean.setGroesse("1");
		//zbean.setZimmer(zimmer);
	}else if(zimmergroesse == "2"){
		zbean.setGroesse("2");
		//zbean.setZimmer(zimmer);
	}else{
		zbean.setGroesse("4");
		//zbean.setZimmer(zimmer);
	} 
			//myLIst = zbean.getRauemeFromDb();
out.print("Right Selection..." + "<br/>");
out.print("Anzahlkinder..." + anzahlkinder + "<br/>");
out.print("Anzahlzimmer..." + anzahlZimmer + "<br/>");
out.print("Zimmergroesse..." + zimmergroesse + "<br/>");
out.print("..........................................." + "<br/>");
out.print("Datumvon: " + datumVon + "<br/>");
out.print("Timevon: " + timeVon + "<br/>");
out.print("..........................................." + "<br/>");
out.print("Datumbis: " + datumBis + "<br/>");
out.print("Timebis: " + timeBis + "<br/>");
}


%>
</body>
</html>