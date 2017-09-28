<%@page import="manage.JavaBean.MsgBean"%>
<%@page import="manage.JavaBean.UserBean"%>
<%@page import="java.util.Date"%>
<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html PuserLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/Projekt.css" />
<script language="javascript" type="text/javascript" src="projekt.js"></script>
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
<%-- Java-Kommentare laden nicht beim Client --%>
<%= new java.util.Date() %> <br/>
<jsp:useBean id="user" class="manage.JavaBean.UserBean" scope="session"/>
<jsp:useBean id="msg" class="manage.JavaBean.MsgBean" scope="session"/>

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
%>

<h1><jsp:getProperty property="infoMsg" name="msg"/></h1>
<h2><jsp:getProperty property="actionMsg" name="msg"/></h2>

<form method="get" action="./ApplStart.jsp" >
	<ul class="tabrow">
		<li class="selected"><a href="ViewStart.jsp">HOMEPAGE</a></li>
		<li class=""><a href="ViewBelegung.jsp">SUCHE</a></li>
		<li class=""><a href="ViewVerfuegbare.jsp">ANZEIGEN</a></li>
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
			<td><input type="submit" name="registrieren" value="Registrieren"/></td>
		</tr>
	</table>
</form>


</body>
</html>