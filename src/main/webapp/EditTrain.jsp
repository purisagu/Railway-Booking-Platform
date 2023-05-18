<%@page import="java.util.Arrays"%>
<%@page import="dto.Train"%>
<%@page import="dao.TrainDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Train</title>
</head>
<body>
<% TrainDao dao=new TrainDao();
Train train =dao.fetch(Integer.parseInt(request.getParameter("number")));
%>

<form action="updatetrain" method="post">
<table>
Train Number:
<input type="number" name="tnumber" value="<%=train.getNumber() %>" readonly="readonly"><br><br>
Train Name:
<input type="text" name="tname" value="<%=train.getName() %>"><br><br>
Number of seats avail:
<input type="number" name="tseat" value="<%=train.getSeat() %>"><br><br>
Price:
<textarea rows="5" cols="30" name="tprice" >
<%
for(String price:train.getPrice())
{
	out.print(price+",");
}
%>
</textarea><br><br>
Stations:
<textarea rows="5" cols="30" name="tstation">
<%
for(String station:train.getStations())
{
	out.print(station+",");
}
%>
</textarea><br><br>
Time:
<textarea rows="5" cols="30" name="ttime">
<%
for(String days:train.getTime())
{
	out.print(days+",");
}
%>
</textarea><br><br>
Days:
<textarea rows="5" cols="30" name="tdays" >
<%
for(String days:train.getDays())
{
	out.print(days+",");
}
%>
</textarea><br><br>
<button type="reset">Cancel</button><button>Update</button>
</table>
</form>

</body>
</html>