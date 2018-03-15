<%@page import="java.util.ArrayList"%>
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
	
	//Button KundenBereich
	String kbereich = request.getParameter("kbereich");
	if(kbereich == null) kbereich = "null";
	String bbereich = request.getParameter("bbereich");
	if(bbereich == null) bbereich = "null";
	String logout = request.getParameter("logout");
	if(logout == null) logout = "null";
	
	if(kbereich.equals("Zum Kundenbereich")){
		msg.setInfoMsg("Wilkommen auf Admin Seite");
		msg.setActionMsg("Du Kannst den Kunden Löschen oder seine Daten ändern.");
		response.sendRedirect("./ViewKundenBereich.jsp");
	
	}else if(bbereich.equals("Zum Buchungsbereich")){
		msg.setInfoMsg("Wilkommen auf Admin Seite");
		msg.setActionMsg("Du Kannst die Buchung mithilfe der Rechnungsnummer stornieren");
		response.sendRedirect("./ViewBuchungsBereich.jsp");
	
	}else if(logout.equals("Logout")){
		if(!user.isLogIn()){
			msg.setLogin();
			response.sendRedirect("./ViewStart.jsp");
		}else{
			user.setLogIn(false);
			user.logoutUser();
			msg.setlogout();
			response.sendRedirect("./ViewStart.jsp");	
		} 
	}else
		response.sendRedirect("./ViewAdminFunktion.jsp");

%>
</body>
</html>