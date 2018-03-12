<%@page import="java.util.ArrayList"%>
<%@page import="manage.JavaClass.Zimmer"%>
<%@page import="manage.JavaBean.MsgBean"%>
<%@page import="manage.JavaBean.BuchungBean"%>
<%@page import="manage.JavaBean.UserBean"%>
<%@page import="manage.JavaBean.KundenBean"%>
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
<jsp:useBean id="kb" class="manage.JavaBean.KundenBean" scope="session"/>
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
kb = (KundenBean) session.getAttribute("kb");
	if(kb == null){
		kb = new KundenBean();
		session.setAttribute("kb", kb);
}
wkbean = (WarenkorbBean) session.getAttribute("wkbean");
	if(wkbean == null){
		wkbean = new WarenkorbBean();
		session.setAttribute("wkbean", wkbean);
}
%><%
	if (user.getLoggedIn()!=1){
		msg.setActionMsg("Bitte Logen Sie sich zuerst!");
		response.sendRedirect("./ViewStart.jsp");
		//response.sendRedirect("./ViewBelegung.jsp?comeFrom=ViewStart");
	}
%>

<h1><jsp:getProperty property="infoMsg" name="msg"/></h1>
<h2><jsp:getProperty property="actionMsg" name="msg"/></h2>

<form method="get" action="./ApplBuchung.jsp">

<%
	ArrayList<Zimmer> wkZimmerList = new ArrayList<Zimmer>();
	wkZimmerList = wkbean.getZimmerArrayList();
	Double auftragsumme = wkbean.getGesamtPreis(wkZimmerList);
	kb.setAuftragsumme(auftragsumme);
%>

<h4 style="color: green;">Ausgewählte Zimmer</h4>

<jsp:getProperty name="wkbean" property="freieZiWKAsHtml" />
<jsp:getProperty name="wkbean" property="freieAAAlsHtml" />

<h4 style="color: green;">Persönliche Daten</h4>
<p><%=kb.getKunde().getAnrede()+" "+kb.getKunde().getVorname()+" "+kb.getKunde().getNachname()%></p>
<p><%=kb.getKunde().getAdresse()%></p>

<p>GESAMTPREIS: <%=auftragsumme + " €"%></p>

<input type="submit" name="zurueck" value="Zurück">
<input type="submit" name="verbindBuchen" value="Verbindlich buchen">

</form>
</body>
</html>