<%@page import="manage.JavaClass.User"%>
<%@page import="manage.JavaBean.UserBean"%>
<%@page import="manage.JavaBean.MsgBean"%>
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

String nickname = request.getParameter("nickname");
String password = request.getParameter("password");
String username = request.getParameter("username");
String email = request.getParameter("email");
String zumlogin = request.getParameter("zumlogin");
if(zumlogin == null) zumlogin = "null";
String registrieren = request.getParameter("registrieren");
if(registrieren == null) registrieren = "null";

user.setUserid(nickname);
user.setPassword(password);
user.setUsername(username);
user.setEmail(email);

if(registrieren.equals("Registrieren")){
	
	session.setAttribute("user", user);	//user Daten in der seesion speichern.
	
	if(user.insertUserIfNotExists()){
		user.insertUserNoCheck();
		msg.setRegSuccess(nickname);
	}else{
		msg.setAlreadyExists(nickname);	
	}
	response.sendRedirect("./StartView.jsp");
	
} else if(zumlogin.equals("Zum Login")){
	if(user.checkUserExists()){
		msg.setLoginSuccess();
		response.sendRedirect("./OnlineView.jsp");
	}else{
		msg.setLoginFailed();
		response.sendRedirect("./StartView.jsp");
	}
} else {	
	response.sendRedirect("./StartView.jsp");
}
%>
</body>
</html>