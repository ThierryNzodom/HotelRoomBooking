<%@page import="manage.JavaClass.Zimmer"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="manage.JavaBean.UserBean"%>
<%@page import="manage.JavaBean.MsgBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
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
List<Zimmer> ziLIst = new ArrayList<Zimmer>();
ziLIst = zbean.getRauemeFromDb();

String neuekonfig = request.getParameter("neuekonfig");
if(neuekonfig == null) neuekonfig = "";
String konfigloeschen = request.getParameter("konfigloeschen");
if(konfigloeschen == null) konfigloeschen = "";
String konfigaendern = request.getParameter("konfigaendern");
if(konfigaendern == null) konfigaendern = "";

if(neuekonfig.equals("Zum Warenkorb und Neue Suche")){
	
	response.sendRedirect("./ViewBelegung.jsp");
} else if (konfigaendern.equals("Zurück")){
	ziLIst = new ArrayList<Zimmer>();
	response.sendRedirect("./ViewBelegung.jsp");
}else if (konfigloeschen.equals("Konfiguration zum Warenkorb")) {
	
	response.sendRedirect("./ViewWarenkorb.jsp");
}  else {
	response.sendRedirect("./ViewVerfuegbare.jsp");
}
	
%>
</body>
</html>