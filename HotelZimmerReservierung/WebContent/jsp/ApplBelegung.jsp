<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="manage.JavaClass.Zimmer"%>
<%@page import="manage.JavaBean.UserBean"%>
<%@page import="manage.JavaBean.MsgBean"%>
<%@page import="manage.JavaBean.LoginBean"%>
<%@page import="manage.JavaBean.ZimmerVerwaltungBean"%>
<%@ page import="java.util.Date,java.io.*,java.util.Enumeration"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.GregorianCalendar"%>
<%@ page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PuserLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="ub" class="manage.JavaBean.UserBean" scope="session"/>
<jsp:useBean id="msg" class="manage.JavaBean.MsgBean" scope="session"/>
<jsp:useBean id="lb" class="manage.JavaBean.LoginBean" scope="session"/>
<jsp:useBean id="zbean" class="manage.JavaBean.ZimmerVerwaltungBean" scope="session"/>
<%
ub = (UserBean) session.getAttribute("ub");
if(ub == null){
	ub = new UserBean();
	session.setAttribute("ub", ub);
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
%>
<%
String datumVon = request.getParameter("datumVon");
String timeVon = request.getParameter("timeVon");
String datumBis = request.getParameter("datumBis"); 
String timeBis = request.getParameter("timeBis");
String anzahlZimmer = request.getParameter("anzahlZimmer");
String zimmergroesse = request.getParameter("zimmergroesse");
String anzahlkinder = request.getParameter("anzahlkinder");
String zimmersuchen = request.getParameter("zimmersuchen");
if(zimmersuchen == null) zimmersuchen = "";



//Zimmer KOnfiguration machen 
if(zimmersuchen.equals("suchen")){		
	//Prüfen ob Zeitvon before ZeitBis ist
	if(zbean.zeitCheck(datumVon, datumBis) == false){
		// Muss eine Nachricht/Msg geworfen werden
		
	};
	// Zeitspanne abfangen
	zbean.setZeit_von(datumVon);
	zbean.setZeit_bis(datumBis);
	//Suche nach anzahl Personen
	if(zimmergroesse == "1"){
		zbean.setGroesse("1");
		//zbean.setZimmer(zimmer);
	}else if(zimmergroesse == "2"){
		zbean.setGroesse("2");
		//zbean.setZimmer(zimmer);
	}else if (zimmergroesse == "4"){
		zbean.setGroesse("4");
		//zbean.setZimmer(zimmer);
	} else zbean.setGroesse("2");
response.sendRedirect("./ViewVerfuegbare.jsp");
}


%>
</body>
</html>