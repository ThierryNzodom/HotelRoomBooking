<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
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
<%
	if (user.isLogIn()==false){
		msg.setActionMsg("Bitte Logen Sie sich zuerst!");
		response.sendRedirect("./ViewStart.jsp");
	}
%>
<h1><jsp:getProperty property="infoMsg" name="msg"/></h1>
<h2><jsp:getProperty property="actionMsg" name="msg"/></h2>

<form method="post" action="./ApplBuchungsBereich.jsp" >
<ul class="tabrow">
		<li class=""><input type="submit" name="logout" value="Logout" /></li>
</ul>
	
	<table border="1">
		<tr>
			<td>
			<h2>Buchungsbereich</h2>
			<h3>Buchung suchen</h3>
				<table>
					<tr>
						<td><input type="text" name="rechnr" /></td>
						<td><input type="submit" name="bsuchen" value="Suchen"/></td>
					</tr>
					<tr><td><b>Übersicht</b></td></tr>
					
					<tr><td><b>Buchungsnummer:</b> <%=" "%><jsp:getProperty property="buchnr" name="bb"/></td></tr>
					
					<tr><td><b>Buchungsdatum:</b><%=" "%><jsp:getProperty property="buchdatum" name="bb"/></td></tr>
					
					<tr><td><b>Zimmer:</b> <%=" "%><jsp:getProperty property="stringZimmer" name="bb"/></td></tr>
					
					<tr><td><b>Gesamtpreis:</b><%=" "%><jsp:getProperty property="auftragsumme" name="kb"/></td></tr>
					
					<tr><td><b>Ankunft:</b><%=" "%><jsp:getProperty property="zvon" name="bb"/></td></tr>
					
					<tr><td><b>Abfahrt:</b><%=" "%><jsp:getProperty property="zbis" name="bb"/></td></tr>									
					
					<tr><td><b>Kundenname:</b><%=" "%><jsp:getProperty property="kundenname" name="kb"/></td></tr>
					
					<tr><td><b>Strasse:</b><%=" "%><jsp:getProperty property="strasse" name="kb"/></td></tr>
					
					<tr><td><b>PLZ und Ort:</b><%=" "%><jsp:getProperty property="plzO" name="kb"/></td></tr>
										
					<tr><td><input type="submit" name="stornierung" value="Buchung Stornieren"/></td></tr>
				</table>
			</td>
		</tr>
	</table>
	
	
	
</form>

</body>
</html>