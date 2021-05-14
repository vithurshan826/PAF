<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@page import="model.customer"%>
<%
//Save---------------------------------
if (request.getParameter("cname") != null)
{
customer cusObj = new customer();
String stsMsg = "";
//Insert--------------------------
if (request.getParameter("hidcIDSave") == "")
{
stsMsg = cusObj.customerdetails(request.getParameter("cname"),
request.getParameter("cphone"),
request.getParameter("cgender"),
request.getParameter("cemail"));
}
else//Update----------------------
{
stsMsg = cusObj.updatecustomerdeatils(request.getParameter("hidcIDSave"),
request.getParameter("cname"),
request.getParameter("cphone"),
request.getParameter("cgender"),
request.getParameter("cemail"));
}
session.setAttribute("statusMsg", stsMsg);
}
//Delete-----------------------------
if (request.getParameter("hidcIDDelete") != null)
{
customer cusObj = new customer();
String stsMsg =
cusObj.deletecustomerdetails(request.getParameter("hidcIDDelete"));
session.setAttribute("statusMsg", stsMsg);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Views/bootstrap.min.css">

<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/customer.js"></script>
<title>Customer Management</title>
</head>
<body>
<h1>Customer Management</h1>

<form id="formcustomer" name="formcustomer" method="post" action="customer.jsp">
 Customer name:
<input id="cname" name="cname" type="text"
 class="form-control form-control-sm">
<br> Phone:
<input id="cphone" name="cphone" type="text"
 class="form-control form-control-sm">
<br> Gender:
<input id="cgender" name="cgender" type="text"
 class="form-control form-control-sm">
<br> Email:
<input id="cemail" name="cemail" type="text"
 class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
<input type="hidden" id="hidcIDSave" name="hidcIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>


<br>
<div id="divItemsGrid">
<%
customer cusObj = new customer();
 out.print(cusObj.readcustomerdetails());
%>
</div>
</body>
</html>