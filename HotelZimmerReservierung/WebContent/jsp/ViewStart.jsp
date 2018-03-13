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
<%= new java.util.Date() %> <br/>
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
%>

<h1><jsp:getProperty property="infoMsg" name="msg"/></h1>
<h2><jsp:getProperty property="actionMsg" name="msg"/></h2>

<form method="post" action="./ApplStart.jsp" >
	<ul class="tabrow">
		<li class="selected"><a href="ViewStart.jsp">HOMEPAGE</a></li>
		<li class=""><a href="ViewBelegung.jsp">SUCHE</a></li>
		<li class=""><a href="ViewWarenkorb.jsp">ANZEIGEN</a></li>
		<li class=""><input type="submit" name="logout" value="Logout" /></li>
	</ul>
	
	<table>
		<tr>
			<td>Email:</td>
			<td><input type="email" name="email" width="45%" value="<jsp:getProperty property="email" name="user"/>" /></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><input type="password" name="password" width="45%" value=""/></td>
		</tr>				
		<tr>		
			<td><input type="submit" name="zumlogin" value="Zum Login"/></td>
			<td><input type="submit" name="admin" value="Admin Login" /></td>
			<td><input type="submit" name="registrieren" value="Registrieren"/></td>
		</tr>
	</table>
	<br/>
	<h2>Zimmer Suche</h2>
	<table>
		<tr><td>Ankunftsdatum</td></tr>
		<tr>				
			<td width="45%"><input name="datumVon" type="date" id="datumVon" size="10" maxlength="10" 
				value=<%= datumVon %>/>
					<img src="../bilder/calendar.jpg" onClick="displayDatePicker('datumVon');"></td>
			<td title="Bitte im Format hh:mm eingeben">Um:<input type="time" name="timeVon" 
				value="12:00"/></td>
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
		<tr>			
			<td><input type="submit" name="zurBuchung" value="Zur Buchung gehen"/></td>			
		</tr>	
	</table>
	
</form>


</body>
</html>