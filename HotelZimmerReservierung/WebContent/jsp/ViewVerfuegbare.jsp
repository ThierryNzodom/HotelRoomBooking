<%@page import="manage.JavaClass.Zimmer"%>
<%@page import="manage.JavaBean.MsgBean"%>
<%@page import="manage.JavaBean.UserBean"%>
<%@page import="manage.JavaBean.LoginBean"%>
<%@page import="manage.JavaBean.BuchungBean"%>
<%@page import="manage.JavaBean.WarenkorbBean"%>
<%@page import="manage.JavaBean.ZimmerVerwaltungBean"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
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
<jsp:useBean id="bb" class="manage.JavaBean.BuchungBean" scope="session"/>
<jsp:useBean id="wkbean" class="manage.JavaBean.WarenkorbBean" scope="session"/>
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
	bb = (BuchungBean) session.getAttribute("bb");
	if(bb == null){
		bb = new BuchungBean();
		session.setAttribute("bb", msg);
	}
	wkbean = (WarenkorbBean) session.getAttribute("wkbean");
	if(wkbean == null){
		wkbean = new WarenkorbBean();
		session.setAttribute("wkbean", wkbean);
	}
	zbean = (ZimmerVerwaltungBean) session.getAttribute("zbean");
	if(zbean == null){
		zbean = new ZimmerVerwaltungBean();
		session.setAttribute("zbean", zbean);
	}

%>
<% 
if (user.getLoggedIn()!=1){
	msg.setActionMsg("Bitte Logen Sie sich zuerst!");
	response.sendRedirect("./ViewStart.jsp");
	//response.sendRedirect("./ViewVerfuegbare.jsp?comeFrom=ViewStart");
}

Zimmer zimmer = new Zimmer();
ArrayList<Zimmer> myLIst = new ArrayList<Zimmer>();
%>

<h1><jsp:getProperty property="infoMsg" name="msg"/></h1>
<h2>Verfügbare Zimmer</h2>

<form method="get" action="./ApplVerfuegbare.jsp">

	<ul class="tabrow">
		<li class=""><a href="ViewStart.jsp">HOMEPAGE</a></li>
		<li class=""><a href="ViewBelegung.jsp">SUCHE</a></li>
		<li class="selected"><a href="ViewVerfuegbare.jsp">ANZEIGEN</a></li>
	</ul>
			<jsp:getProperty name="bb" property="freieRaeumeEAsHtml" />
			<jsp:getProperty name="bb" property="freieRaeumeDAsHtml" />
			<jsp:getProperty name="bb" property="freieRaeumeSAsHtml" />

			<table>
			<tr>
				<td><input type="submit" name=konfigaendern value="Zurück"/></td>
				<td><input type="submit" name=weiter value="Weiter zum Warenkorb"/></td>				
			</tr>
			</table>
	</form>
</body>
</html>