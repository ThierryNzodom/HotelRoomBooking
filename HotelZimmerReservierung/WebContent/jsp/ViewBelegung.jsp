<%@page import="manage.JavaBean.MsgBean"%>
<%@page import="manage.JavaBean.UserBean"%>
<%@page import="manage.JavaBean.BuchungBean"%>
<%@page import="java.util.Date"%>
<%@ page import="java.util.Date,java.io.*,java.util.Enumeration"%>
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
<%-- Java-Kommentare laden nicht beim Client --%>

<jsp:useBean id="user" class="manage.JavaBean.UserBean" scope="session"/>
<jsp:useBean id="msg" class="manage.JavaBean.MsgBean" scope="session"/>
<jsp:useBean id="bb" class="manage.JavaBean.BuchungBean" scope="session"/>

<%user = (UserBean) session.getAttribute("user");
	if(user == null){
		user = new UserBean();
		session.setAttribute("user", user);
	}
	bb = (BuchungBean) session.getAttribute("bb");
	if(bb == null){
		bb = new BuchungBean();
		session.setAttribute("bb", msg);
	}
  msg = (MsgBean) session.getAttribute("msg");
	if(msg == null){
		msg = new MsgBean();
		session.setAttribute("msg", msg);
	}
%>
<%
	if (user.isLogIn()==false){
		msg.setActionMsg("Bitte Logen Sie sich zuerst!");
		response.sendRedirect("./ViewStart.jsp");
	}
%>
<h1><jsp:getProperty property="infoMsg" name="msg"/></h1>
<h3><jsp:getProperty property="actionMsg" name="msg"/></h3>

<form method="post" action="./ApplBelegung.jsp" >

<ul class="tabrow">
			<li class=""><a href="ViewStart.jsp">HOME</a></li>
			<li class="selected"><a href="ViewBelegung.jsp">SUCHE</a></li>
			<li class=""><a href="ViewWarenkorb.jsp">ANZEIGEN</a></li>
			<li class=""><input type="submit" name="logout" value="Logout" /></li>
		</ul>

	<h2>Zimmerkonfiguration</h2>
	<table>
		<tr>
			<td><select name="EinzelnZimmer">
						<option value="0" selected="selected">kein Classic</option>
  						<option value="1">1 Classic</option>
  						<option value="2">2 Classic</option>
  						<option value="3">3 Classic</option>
  						<option value="4">4 Classic</option>
  						<option value="5">5 Classic</option>
  						<option value="6">6 Classic</option>
  						<option value="7">7 Classic</option>
  						<option value="8">8 Classic</option>
  						<option value="9">9 Classic</option>
  						<option value="10">10 Classic</option>
				</select></td>		
			<td><select name="ezimmergroesse" size="1" id="ezgr">
  						<option value="1" selected="selected">je 1 Person</option>
				</select></td>
			<td><select name="eanzahlkinder">
  						<option value="0k">kein Kind</option>
  						<option value="1k">1 Kind</option>
  						<option value="2k" selected="selected">2 Kinder</option>
  						<option value="3k">3 Kinder</option>
  						<option value="4k">4 Kinder</option>  											 
				</select></td>
		</tr>
		<tr></tr><tr></tr>
		<tr>
			<td><select name="DoppelZimmer">
						<option value="0" selected="selected">kein Comfort</option>
  						<option value="1">1 Comfort</option>
  						<option value="2">2 Comfort</option>
  						<option value="3">3 Comfort</option>
  						<option value="4">4 Comfort</option>
  						<option value="5">5 Comfort</option>
  						<option value="6">6 Comfort</option>
  						<option value="7">7 Comfort</option>
  						<option value="8">8 Comfort</option>
  						<option value="9">9 Comfort</option>
  						<option value="10">10 Comfort</option>  										 						 
				</select></td>		
			<td><select name="dzimmergroesse" size="1" id="dzgr">
  						<option value="1">1 Person</option>
  						<option value="2" selected="selected">je 2 Personen</option>
  						<option value="4">4 Personen</option>
				</select></td>
			<td><select name="danzahlkinder">
  						<option value="0">kein Kind</option>
  						<option value="1k">1 Kind</option>
  						<option value="2k" selected="selected">2 Kinder</option>
  						<option value="2k">3 Kinder</option>
  						<option value="4k">4 Kinder</option>   											 
				</select></td>
		</tr>
		<tr></tr><tr></tr>
		<tr>
			<td><select name="Suite">
  						<option value="0" selected="selected">kein Suite</option>
  						<option value="1">1 Suite</option>
  						<option value="2">2 Suite</option>
  						<option value="3">3 Suite</option>
  						<option value="4">4 Suite</option>
  						<option value="5">5 Suite</option>  										 						 
				</select></td>		
			<td><select name="szimmergroesse" size="1" id="szgr">
  						<option value="1">1 Person</option>
  						<option value="2" >4 Personen</option>
  						<option value="4" selected="selected"> je 4 Personen</option>
				</select></td>
			<td><select name="sanzahlkinder">
  						<option value="0">kein Kind</option>
  						<option value="1k">1 Kind</option>
  						<option value="2k" selected="selected">2 Kinder</option>
  						<option value="3k">3 Kinder</option>
  						<option value="4k">4 Kinder</option> 											 
				</select></td>
		</tr>
		<tr>			
			<td><input type="submit" name="zimmersuchen" value="suchen"/></td>			
		</tr>	
	</table>
</form>
</body>
</html>