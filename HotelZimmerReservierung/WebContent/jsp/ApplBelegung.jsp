<%@page import="manage.JavaClass.Buchung"%>
<%@page import="manage.JavaBean.BuchungBean"%>
<%@page import="manage.JavaBean.UserBean"%>
<%@page import="manage.JavaBean.MsgBean"%>
<%@page import="manage.JavaBean.LoginBean"%>
<%@page import="manage.JavaClass.Zimmer"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
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
<jsp:useBean id="bb" class="manage.JavaBean.BuchungBean" scope="session"/>
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
bb = (BuchungBean) session.getAttribute("bb");
if(bb == null){
	bb = new BuchungBean();
	session.setAttribute("bb", msg);
}
%>
<%
//Liste der Zimmer from Database
ArrayList<Zimmer> zE = new ArrayList<Zimmer>();
ArrayList<Zimmer> zD = new ArrayList<Zimmer>();
ArrayList<Zimmer> zS = new ArrayList<Zimmer>();
//Liste der Buchungen from Database.Buchung
ArrayList<Buchung> bE = new ArrayList<Buchung>();
ArrayList<Buchung> bD = new ArrayList<Buchung>();
ArrayList<Buchung> bS = new ArrayList<Buchung>();
//Liste der verfügbaren Zimmer je nach ZimmerTyp
ArrayList<Zimmer> vE = new ArrayList<Zimmer>();
ArrayList<Zimmer> vD = new ArrayList<Zimmer>();
ArrayList<Zimmer> vS = new ArrayList<Zimmer>();

String einzelnZimmerZahl = request.getParameter("EinzelnZimmer");
String doppelZimmerZahl = request.getParameter("DoppelZimmer");
String suiteZahl = request.getParameter("Suite");
String zimmersuchen = request.getParameter("zimmersuchen");
if(zimmersuchen == null) zimmersuchen = "";

//Zimmer Konfiguration machen 
if(zimmersuchen.equals("suchen")){		
		int efor = Integer.valueOf(einzelnZimmerZahl);
		int dfor = Integer.valueOf(doppelZimmerZahl);
		int sfor = Integer.valueOf(suiteZahl);
	// Zeitspanne abfangen
	Buchung buchung = new Buchung();
	
	//Anzahl ZImmertyp abfangen
	buchung.setZahlE(efor);
	buchung.setZahlD(dfor);
	buchung.setZahlS(sfor);
	bb.setBuchung(buchung);
	
	// AnzahlPersonen setzen
	//zbean.setZimmer(zimmer);
	if(!einzelnZimmerZahl.equals("0")){		
		zE = bb.getEZimmerFromDb();
		bE = bb.getEBelegungFromDb();
		vE = bb.zimmerfrei(zE, bE);
	}
	bb.setListZimmerFrei(vE);
	if(!doppelZimmerZahl.equals("0")){
		zD = bb.getDZimmerFromDb(); 
		bD = bb.getDBelegungFromDb();
		vD = bb.zimmerDfrei(zD, bD);
	}
	bb.setListDZimmerFrei(vD);
	if(!suiteZahl.equals("0")){
		zS = bb.getSZimmerFromDb(); 
		bS = bb.getSBelegungFromDb();
		vS = bb.zimmerSfrei(zS, bS);
	}
	bb.setListSZimmerFrei(vS);
			
//out.print("Zimmergroesse..." + zimmergroesse + "<br/>");
//out.print("Datumvon: " + datumVon + "<br/>");
//out.print("Datumbis: " + datumBis + "<br/>");
	response.sendRedirect("./ViewVerfuegbare.jsp");
}


%>
</body>
</html>