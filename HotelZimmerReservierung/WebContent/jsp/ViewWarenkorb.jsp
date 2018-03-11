<%@page import="java.util.ArrayList"%>
<%@page import="manage.JavaClass.Zimmer"%>
<%@page import="manage.JavaBean.MsgBean"%>
<%@page import="manage.JavaBean.BuchungBean"%>
<%@page import="manage.JavaBean.UserBean"%>
<%@page import="manage.JavaBean.WarenkorbBean"%>
<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
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
<jsp:useBean id="wkbean" class="manage.JavaBean.WarenkorbBean" scope="session"/>
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
wkbean = (WarenkorbBean) session.getAttribute("wkbean");
	if(wkbean == null){
		wkbean = new WarenkorbBean();
		session.setAttribute("wkbean", wkbean);
}
	if (user.isLoggedIn()){
		msg.setActionMsg("Ihre Daten sehen wie folgt aus:" + "\n");
	}
String leer = "";
%>

<h1><jsp:getProperty property="infoMsg" name="msg"/></h1>
<h2>Zimmer Suche</h2>
<h2><jsp:getProperty property="actionMsg" name="msg"/></h2>

<form method="get" action="./ApplWarenkorb.jsp">
<%
out.print("TEST.............................................."+ "<br/>");
out.print("E-Mail: " + user.getEmail() + "<br/>");
out.print("Password: " + user.getPassword()+ "<br/>");
out.print("Datumvon: " + bb.getBuchung().getZeit_von() + "<br/>");
out.print("Datumbis: " + bb.getBuchung().getZeit_bis() + "<br/>");
out.print("TEST.............................................."+ "<br/>");
for (Zimmer z : wkbean.getZimmerArrayList()) {
	out.print(z.toCopyString() + "<br/>");
}
out.print("TEST.............................................."+ "<br/>");
%>

<h1 style="background-color: yellow; color: red;" align="center">Ihr Warenkorb beinhaltet folgende freie Zimmer:</h1>
<h3>Ausgewählte Zimmer:</h3>
<h4><%=leer%></h4>

<%
	ArrayList<Zimmer> wkZimmerList = new ArrayList<Zimmer>();
	wkZimmerList = wkbean.getZimmerArrayList();
%>

	<jsp:getProperty name="wkbean" property="freieRaeumeWKAsHtml" />
	<p style="color: red;">GESAMTPREIS: <%=String.valueOf(wkbean.getGesamtPreis(wkZimmerList))+" €" %></p>
	
	<table>
		<tr>
			<td><input type="submit" name=selaendern value="Zurueck/Ändern"/></td>
			<td><input type="submit" name=zimmerbuchen value="Weiter"/></td>							
		</tr>
	</table>
	
</form>
</body>
</html>