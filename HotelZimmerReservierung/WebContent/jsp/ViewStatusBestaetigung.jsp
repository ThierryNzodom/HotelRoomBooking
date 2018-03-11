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

<form method="get" action="./ApplStatusBestaetigung.jsp">

<h4 style="color: green;">Login</h4>
<p>Bitte rechts oben unter "Login" einloggen</p>

<h4 style="color: green;">Noch nicht registriert?</h4>
<p>Bitte Links unten auf registrierung klicken</p>

<h4 style="color: green;">Oder</h4>
<input type="submit" name="gastStatus" value="Als Gast weiter">

</form>
</body>
</html>