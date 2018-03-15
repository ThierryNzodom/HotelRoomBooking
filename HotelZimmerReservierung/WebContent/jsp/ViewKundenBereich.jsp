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
<%= new java.util.Date() %>
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

<form method="post" action="./ApplKundenBereich.jsp" >
<ul class="tabrow">
		<li class=""><input type="submit" name="logout" value="Logout" /></li>
</ul>
		
	<table border="1">
		<tr>
			<td>
			<h2>Kundenbereich</h2>
			<h3>Kunde suchen</h3>
				<table>
					<tr>
						<td><input type="email" name="kundenMail" value=""/></td>
						<td><input type="submit" name="ksuchen" value="Suchen"/></td>
					</tr>
					<tr><td><b>Übersicht</b></td></tr>
					<tr>
						<td><div>
							<input type="checkbox" id="kundenAnrede" name="seldata" value="">
    						<label for="kundenAnrede"><input type="text" name="kanrede" value="<jsp:getProperty property="anrede" name="kb"/>"/></label>
    						</div>
    					</td>
					</tr>
					<tr>
						<td><div>
							<input type="checkbox" id="kundenVorname" name="seldata" value="">
    						<label for="kundenVorname"><input type="text" name="kvorname" value="<jsp:getProperty property="vorname" name="kb"/>"/></label>
    						</div>
    					</td>
					</tr>
					<tr>
						<td><div>
							<input type="checkbox" id="kundenNachname" name="seldata" value="">
    						<label for="kundenNachname"><input type="text" name="knachname" value="<jsp:getProperty property="nachname" name="kb"/>"/></label>
    						</div>
    					</td>
					</tr>
					<tr>
						<td><div>
							<input type="checkbox" id="kundenEmail" name="seldata" value="">
    						<label for="kundenEmail"><input type="text" name="kdmail" value="<jsp:getProperty property="mail" name="kb"/>"/></label>
    						</div>
    					</td>
					</tr>
					<tr>
						<td><div>
							<input type="checkbox" id="kundenGdatum" name="seldata" value="">
    						<label for="kundenGdatum"><input type="text" name="kgdatum" value="<jsp:getProperty property="gdatum" name="kb"/>"/></label>
    						</div>
    					</td>
					</tr>
					<tr>
						<td><div>
							<input type="checkbox" id="kundenTelnummer" name="seldata" value="">
    						<label for="kundenTelnummer"><input type="text" name="ktelnummer" value="<jsp:getProperty property="telnummer" name="kb"/>"/></label>
    						</div>
    					</td>
					</tr>
					<tr>
						<td><div>
							<input type="checkbox" id="kundenStrasse" name="seldata" value="">
    						<label for="kundenStrasse"><input type="text" name="kstrasse" value="<jsp:getProperty property="strasse" name="kb"/>"/></label>
    						</div>
    					</td>
					</tr>
					<tr>
						<td><div>
							<input type="checkbox" id="kundenPlzO" name="seldata" value="">
    						<label for="kundenPlzO"><input type="text" name="kplzO" value="<jsp:getProperty property="plzO" name="kb"/>"/></label>
    						</div>
    					</td>
					</tr>
					<tr>
						<td><input type="submit" name="aendern" value="Update"/></td>
						<td><input type="submit" name="loeschen" value="Loeschen"/></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>

</body>
</html>