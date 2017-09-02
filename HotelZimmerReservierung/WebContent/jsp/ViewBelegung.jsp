<%@page import="manage.JavaBean.MsgBean"%>
<%@page import="manage.JavaBean.UserBean"%>
<%@page import="java.util.Date"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
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
<h2>Zimmer Suche</h2>

<form method="get" action="./BelegungAppl.jsp" >
	<table>
		<tr><td>Ankunftsdatum</td></tr>
		<tr>				
			<td width="45%"><input name="datumVon" type="date" id="datumVon" size="10" maxlength="10" />
					<img src="../bilder/calendar.jpg" onClick="displayDatePicker('datumVon');"></td>
			<td title="Bitte im Format hh:mm eingeben">Um:<input type="time" name="timeVon" 
				value="00:00"/></td>			
		</tr>
		<tr><td>Abreisedatum</td></tr>
		<tr>			
			<td width="45%"><input name="datumBis" type="date" id="datumBis" size="10" maxlength="10" />
					<img src="../bilder/calendar.jpg" onClick="displayDatePicker('datumBis');"></td>
						<td title="Bitte im Format hh:mm eingeben">Um:<input type="time" name="timeBis" 
				value="12:00"/></td>	
		</tr>
	</table>
	<br/>
	<h2>Zimmerkonfiguration</h2>
	<table>
		<tr>
			<td><select name="zimmerTyp">
  						<option value="einzelZ" selected="selected">Classic</option>
  						<option value="dopppelStandard">Standard Doppel</option>
  						<option value="doppelComfort">Comfort Doppel</option>					 						 
				</select></td>
			<td><select name="anzahlZimmer">
  						<option value="1z" selected="selected">1 Zimmer</option>
  						<option value="2z">2 Zimmer</option>					 						 
				</select></td>			
			<td><select name="anzahlErw">
  						<option value="1Erw">1 Erw</option>
  						<option value="2Erw" selected="selected">2 Erw</option>
  						<option value="3Erw">3 Erw</option>
  						<option value="4Erw">4 Erw</option>
				</select></td>
			<td><select name="anzahlKinder">
  						<option value="0k">kein Kind</option>
  						<option value="1k">1 Kind</option>
  						<option value="2k" selected="selected">2 Kinder</option>
  						<option value="2k">3 Kinder</option>  											 
				</select></td>
		</tr>
		
		<tr>
			<td><input type="submit" name="zimmerKonfig" value="Weitere Zimmerkonfig"/></td>				
			<td><input type="submit" name="zimmerVerfuegbare" value="Zur Buchung"/></td>			
		</tr>	
	</table>
</form>
</body>
</html>