<%@page import="manage.JavaClass.Zimmer"%>
<%@page import="manage.JavaBean.UserBean"%>
<%@page import="manage.JavaBean.MsgBean"%>
<%@page import="manage.JavaBean.BuchungBean"%>
<%@page import="manage.JavaBean.WarenkorbBean"%>
<%@page import="manage.JavaBean.ZimmerVerwaltungBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
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
<jsp:useBean id="wkbean" class="manage.JavaBean.WarenkorbBean" scope="session"/>
<jsp:useBean id="zbean" class="manage.JavaBean.ZimmerVerwaltungBean" scope="session"/>
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
zbean = (ZimmerVerwaltungBean) session.getAttribute("zbean");
if(zbean == null){
	zbean = new ZimmerVerwaltungBean();
	session.setAttribute("zbean", zbean);
}
wkbean = (WarenkorbBean) session.getAttribute("wkbean");
if(wkbean == null){
	wkbean = new WarenkorbBean();
	session.setAttribute("wkbean", wkbean);
}

Zimmer zimmer = new Zimmer();
ArrayList<Zimmer> ziList = new ArrayList<Zimmer>();

String weiter = request.getParameter("weiter");
if(weiter == null) weiter = "";
String konfigaendern = request.getParameter("konfigaendern");
if(konfigaendern == null) konfigaendern = "";
String[] selectedRaeume = request.getParameterValues("zimmerfrei");
if(selectedRaeume != null && selectedRaeume.length != 0){
	for (int w = 0; w < selectedRaeume.length; w++) {
	String[] concatString = selectedRaeume[w].split(",");
//	String ziAttr = "";
	String cutString = "";
	String cutStringteils = "";
	for (int i = 0; i < concatString.length; i++) {
		cutString = concatString[i];
		String[] cutStringTeil = cutString.split(":");
		for (int j = 0; j < cutStringTeil.length; j++) {
			cutStringteils = cutStringTeil[j];
			if(j>0){
//				ziAttr =  cutStringTeil[j];
				if(i == 0){
					zimmer.setzID(cutStringTeil[j]);	
				}else if(i == 1){
					/* /* Zimmertyp zimmertyp = Zimmertyp.valueOf(cutStringTeil[j]); / */
					zimmer.setZtyp(cutStringTeil[j]);
				}else if(i == 2){
					zimmer.setGroesse(cutStringTeil[j]);
				}else zimmer.setPreis(Double.parseDouble(cutStringTeil[j]));
				
			}
			}			
		}
	ziList.add(zimmer);
	zimmer = new Zimmer();
	}
}

if (weiter.equals("Weiter zum Warenkorb")) {
	wkbean.setBuchung(bb.getBuchung());
	wkbean.setZimmerArrayList(ziList);
	
	response.sendRedirect("./ViewWarenkorb.jsp");
	
} else if (konfigaendern.equals("Zurück")){
	wkbean.setZimmerArrayList(new ArrayList<Zimmer>());
	response.sendRedirect("./ViewBelegung.jsp");
	
} else {
	response.sendRedirect("./ViewVerfuegbare.jsp");
}
	
%>
</body>
</html>