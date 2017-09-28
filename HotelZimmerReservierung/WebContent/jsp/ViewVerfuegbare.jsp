<%@page import="manage.JavaClass.Zimmer"%>
<%@page import="manage.JavaBean.MsgBean"%>
<%@page import="manage.JavaBean.UserBean"%>
<%@page import="manage.JavaBean.LoginBean"%>
<%@page import="manage.JavaBean.ZimmerVerwaltungBean"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date,java.io.*,java.util.Enumeration"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/Projekt.css" />
<script language="javascript" type="text/javascript" src="projekt.js"></script>
<script src="../js/datePicker.js"></script>
<style type="text/css">@import "http://ajax.googleapis.com/ajax/libs/dojo/1.5/dijit/themes/claro/claro.css";</style>
<script src="http://ajax.googleapis.com/ajax/libs/dojo/1.6/dojo/dojo.xd.js"></script>
<script>
dojo.ready(function(){
  dojo.byId("greeting").innerHTML += ", from " + dojo.version;
});
</script>
</head>
<body>
<!-- HTML-Kommentare laden beim Client ISO-8859-1-->
<%-- Java-Kommentare laden nicht beim Client --%>
<% Date dtv = new Date();%>

<jsp:useBean id="user" class="manage.JavaBean.UserBean" scope="session"/>
<jsp:useBean id="msg" class="manage.JavaBean.MsgBean" scope="session"/>
<jsp:useBean id="lb" class="manage.JavaBean.LoginBean" scope="session"/>
<jsp:useBean id="zbean" class="manage.JavaBean.ZimmerVerwaltungBean" scope="session"/>

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
	zbean = (ZimmerVerwaltungBean) session.getAttribute("zbean");
	if(zbean == null){
		zbean = new ZimmerVerwaltungBean();
		session.setAttribute("zbean", zbean);
	}
%>
<% 
if (!user.isLoggedIn()){
	msg.setLoginFirst();
//	response.sendRedirect("./ViewVerfuegbare.jsp?comeFrom=ViewStart");
}else{
	Zimmer zimmer = new Zimmer();
	List<Zimmer> myLIst = new ArrayList<Zimmer>();
	myLIst = zbean.getRauemeFromDb();	
}
%>
<h1><jsp:getProperty property="infoMsg" name="msg"/></h1>
<h2>Verfügbare Zimmer</h2>

<form action="./ApplVerfuegbare.jsp" method="get">

	<ul class="tabrow">
		<li class=""><a href="ViewStart.jsp">HOMEPAGE</a></li>
		<li class=""><a href="ViewBelegung.jsp">SUCHE</a></li>
		<li class="selected"><a href="ViewVerfuegbare.jsp">ANZEIGEN</a></li>
	</ul>
	
	<table>
		<tr>
			<td>ZIMMERID</td>
			<td>ZIMMERTYP</td>
			<td>ZEIT_VON</td>
			<td>ZEIT_BIS</td>
			<td>GROESSE</td>
			<td>PREIS</td>
			<td>IST_BELEGT</td>
	</tr>
<!-- Meine Kommentare -->
	</table>	
		<textarea name="ausgabe" readonly="readonly" style="width: 694px; height: 330px;">
				<jsp:getProperty name="zbean" property="freieRaeumeAsHtml" />
		</textarea>
			<table>
			<tr>
				<td><input type="submit" name=neuekonfig value="Zum Warenkorb und Neue Suche"/></td>
				<td><input type="submit" name=konfigloeschen value="Konfiguration zum Warenkorb"/></td>
				<td><input type="submit" name=konfigaendern value="Zurück"/></td>
			</tr>
			</table>
	</form>
</body>
</html>