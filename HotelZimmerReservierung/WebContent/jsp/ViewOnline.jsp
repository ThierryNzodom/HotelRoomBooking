<%@page import="manage.JavaBean.MsgBean"%>
<%@page import="manage.JavaBean.UserBean"%>
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
%>

<h1><%= msg.getInfoMsg() %></h1>
<h2><%= msg.getActionMsg() %></h2>

<%
out.print("Userid: " + user.getUserid() + "<br/>");
out.print("Password: " + user.getPassword()+ "<br/>");
out.print("Username: " + user.getUsername() + "<br/>");
out.print("E-Mail: " + user.getEmail() + "<br/>");
%>

</body>
</html>