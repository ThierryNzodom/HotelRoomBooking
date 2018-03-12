<%@page import="java.util.ArrayList"%>
<%@page import="manage.JavaClass.Zimmer"%>
<%@page import="manage.JavaBean.MsgBean"%>
<%@page import="manage.JavaBean.BuchungBean"%>
<%@page import="manage.JavaBean.UserBean"%>
<%@page import="manage.JavaBean.KundenBean"%>
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
%>

<form method="post" action="./ApplGastDaten.jsp">
<ul class="tabrow">
			<li class=""><a href="ViewStart.jsp">HOME</a></li>
			<li class=""><a href="ViewBelegung.jsp">SUCHE</a></li>
			<li class=""><a href="ViewWarenkorb.jsp">ANZEIGEN</a></li>
			<li class=""><input type="submit" name="logout" value="Logout" /></li>
</ul>
<h2>Persönliche Daten</h2>

<table>
	<tr>
		<td><h4 style="color: green;">Benutzer Daten</h4></td>	<tr>
	</tr>
	<tr>
		<td><input type="text" name="email" value=""/></td>
	</tr>
	<tr>
		<td><input type="password" name="password" width="45%" value=""/></td>
	</tr>
	<tr>
		<td><h4 style="color: green;">Persönliche Daten</h4></td>
	</tr>
	<tr>
		<td>
			<select name="anrede">						
  						<option value="Herr" selected="selected">Herr</option>
  						<option value="Frau">Frau</option>
  			</select>
  		</td>
	</tr>
	<tr>
		<td><input type="text" name="vorname" value="Vorname"/></td>
	</tr>
	<tr>
		<td><input type="text" name="nachname" value="Nachname"/></td>
	</tr>
	<tr>
		<td><input type="text" name="gdatum" value="11.03.1999"/></td>
	</tr>
	<tr>
		<td><input type="text" name="telnummer" value="+49(0)17689753223"/></td>
	</tr>
	<tr>
		<td><input type="text" name="strasse" value="mokolostr.11"/></td>
	</tr>
	<tr>
		<td><input type="text" name="plz_stadt" value="67059 Ludwigshafen"/></td>
	</tr>
	<tr>
		<td><input type="submit" name="register" value="Registrieren"></input></td>
	</tr>
</table>

</form>
</body>
</html>