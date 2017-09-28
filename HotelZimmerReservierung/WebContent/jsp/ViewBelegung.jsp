<%@page import="manage.JavaBean.MsgBean"%>
<%@page import="manage.JavaBean.UserBean"%>
<%@page import="manage.JavaBean.LoginBean"%>
<%@page import="manage.JavaBean.ZimmerVerwaltungBean"%>
<%@page import="java.util.Date"%>
<%@ page import="java.util.Date,java.io.*,java.util.Enumeration"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.GregorianCalendar"%>
<%@ page import="java.util.Calendar"%>
<%@ page language="java" pageEncoding="UTF-8"%>
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
<% Date dtv = new Date();
SimpleDateFormat dfv = new SimpleDateFormat("dd.MM.yyyy");
SimpleDateFormat uhrv = new SimpleDateFormat("HH:mm");
String datumVon = dfv.format(dtv);
String timeVon = uhrv.format(dtv);

Date date = new Date();
SimpleDateFormat dfb = new SimpleDateFormat("dd.MM.yyyy");
Calendar calendar = new GregorianCalendar();
calendar.setTime(date);
// add 2 days to calendar
calendar.add(Calendar.DAY_OF_MONTH, 2);
Date futureday = calendar.getTime();
String datumBis = dfb.format(futureday);
%> <br/>

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
	//response.sendRedirect("./ViewStart.jsp");
	//response.sendRedirect("./ViewBelegung.jsp?comeFrom=ViewStart");
}
%>
<h1><jsp:getProperty property="infoMsg" name="msg"/></h1>
<h2>Zimmer Suche</h2>

<form method="get" action="./ApplBelegung.jsp" >
<ul class="tabrow">
			<li class=""><a href="ViewStart.jsp">HOME</a></li>
			<li class="selected"><a href="ViewBelegung.jsp">SUCHE</a></li>
			<li class=""><a href="ViewVerfuegbare.jsp">ANZEIGEN</a></li>
		</ul>
	<table>
		<tr><td>Ankunftsdatum</td></tr>
		<tr>				
			<td width="45%"><input name="datumVon" type="date" id="datumVon" size="10" maxlength="10" 
				value=<%= datumVon %>/>
					<img src="../bilder/calendar.jpg" onClick="displayDatePicker('datumVon');"></td>
			<td title="Bitte im Format hh:mm eingeben">Um:<input type="time" name="timeVon" 
				value=<%= timeVon %>/></td>
			<td><h4><jsp:getProperty property="timeErrorMsg" name="msg"/></h4></td>			
		</tr>
		<tr><td>Abreisedatum</td></tr>
		<tr>			
			<td width="45%"><input name="datumBis" type="date" id="datumBis" size="10" maxlength="10" 
				value=<%= datumBis %>/>
					<img src="../bilder/calendar.jpg" onClick="displayDatePicker('datumBis');"></td>
			<td title="Bitte im Format hh:mm eingeben">Um:<input type="time" name="timeBis" 
				value="12:00"/></td>
			<td><h4><jsp:getProperty property="timeErrorMsg" name="msg"/></h4></td>	
		</tr>
	</table>
	<br/>
	<h2>Zimmerkonfiguration</h2>
	<table>
		<tr>
			<td><select name="anzahlZimmer">
  						<option value="1z" selected="selected">1 Zimmer</option>  										 						 
				</select></td>		
			<td><select name="zimmergroesse">
  						<option value="1">1 Person</option>
  						<option value="2" selected="selected">2 Personen</option>
  						<option value="4">4 Personen</option>
				</select></td>
			<td><select name="anzahlkinder">
  						<option value="0">kein Kind</option>
  						<option value="1k">1 Kind</option>
  						<option value="2k" selected="selected">2 Kinder</option>
  						<option value="2k">3 Kinder</option>  											 
				</select></td>
		</tr>
		
		<tr>			
			<td><input type="submit" name="zimmersuchen" value="suchen"/></td>			
		</tr>	
	</table>
</form>
</body>
</html>