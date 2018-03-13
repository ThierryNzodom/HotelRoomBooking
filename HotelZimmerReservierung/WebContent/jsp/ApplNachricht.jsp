<%@page import="manage.JavaClass.Zimmer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="manage.JavaBean.KundenBean"%>
<%@page import="manage.JavaClass.Kunde"%>
<%@page import="manage.JavaBean.UserBean"%>
<%@page import="manage.JavaBean.MsgBean"%>
<%@page import="manage.JavaBean.BuchungBean"%>
<%@page import="manage.JavaBean.KundenBean"%>
<%@page import="manage.JavaBean.WarenkorbBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PuserLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
	session.setAttribute("bb", bb);
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

Kunde kunde = new Kunde();
ArrayList<Zimmer> wkZimmerList = new ArrayList<Zimmer>();
wkZimmerList = wkbean.getZimmerArrayList();

String zurueckStart = request.getParameter("zurueckStart");
if(zurueckStart == null) zurueckStart = "";
String logout = request.getParameter("logout");
if(logout == null) logout = "";

if(zurueckStart.equals("Zur Startseite")){	
	msg.setNeueBuchung();
	response.sendRedirect("./ViewStart.jsp");
}else if(logout.equals("Logout")){
	user.setLogIn(false);
	user.logoutUser();
	msg.setlogout();
	response.sendRedirect("./ViewStart.jsp");
	
} else {
	response.sendRedirect("./ViewNachricht.jsp");
}



%>
</body>
</html>