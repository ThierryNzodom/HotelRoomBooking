<%@page import="manage.JavaClass.User"%>
<%@page import="manage.JavaBean.UserBean"%>
<%@page import="manage.JavaBean.MsgBean"%>
<%@page import="manage.JavaBean.LoginBean"%>
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
<jsp:useBean id="lb" class="manage.JavaBean.LoginBean" scope="session"/>
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

String email = request.getParameter("email");
String password = request.getParameter("password");
String zumlogin = request.getParameter("zumlogin");
if(zumlogin == null) zumlogin = "null";
String registrieren = request.getParameter("registrieren");
if(registrieren == null) registrieren = "null";

//Login
if(registrieren.equals("Registrieren")){
	
	session.setAttribute("user", user);	//user Daten in der session speichern.
	if(email == "" || password == "") {
		msg.setActionMsg("Bitte fügen Sie eine Email und(oder) ein Password ein!");
		response.sendRedirect("./ViewStart.jsp");
	} else{
	
	if(user.insertUserIfNotExists()){
		msg.setRegSuccess(email);
	}else{
		msg.setAlreadyExists(email);	
	}
	response.sendRedirect("./ViewStart.jsp");
	}
} else if(zumlogin.equals("Zum Login")){
	user.setEmail(email);
	user.setPassword(password);
	try{
		boolean rc = user.checkEmailPassword();
		if (rc) {//email/pw passt
			user.setLoggedIn(true);
			msg.setLoginSuccess();
			response.sendRedirect("./ViewBelegung.jsp?comeFrom=ApplStart");
		}else{//email/pw passt nicht
			user.setLoggedIn(false);
			msg.setLoginFailed();
			response.sendRedirect("./ViewStart.jsp");
		}
	}catch(Exception e){
		e.printStackTrace();
		msg.setSystemfehler();
		response.sendRedirect("./ViewStart.jsp");
	}
	//if(ub.checkUserExists()){
		//msg.setLoginSuccess();
		//response.sendRedirect("./ViewStart.jsp");
	//}else{
		//msg.setLoginFailed();
		//response.sendRedirect("./ViewStart.jsp");
	//}
} else {	
	response.sendRedirect("./ViewStart.jsp");
}
%>
</body>
</html>