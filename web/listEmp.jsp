<%@ page import="java.util.List" %>
<%@ page import="com.twp.servletemp.entity.Employee" %>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<html>
<head>
    <title>emplist</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>
<body>
<div id="wrap">
    <div id="top_content">
        <div id="header">
            <div id="rightheader">
                <p>
                    2009/11/20
                    <br/>
                </p>
            </div>
            <div id="topheader">
                <h1 id="title">
                    <a href="#">main</a>
                </h1>
            </div>
            <div id="navigation">
            </div>
        </div>
        <div id="content">
            <p id="whereami">
            </p>
            <h1>
                Welcome!
            </h1>
            <table class="table">
                <tr class="table_header">
                    <td>
                        ID
                    </td>
                    <td>
                        Name
                    </td>
                    <td>
                        Salary
                    </td>
                    <td>
                        Age
                    </td>
                    <td>
                        Operation
                    </td>
                </tr>
                <%
                    List<Employee> all = (List<Employee>) request.getAttribute("emps");
                    for (int i = 0; i < all.size(); i++) {
                        Employee e = all.get(i); %>
                <tr class="<%= i%2==0? "row1":"row2"%>">
                    <td><%= e.getId()%>
                    </td>
                    <td><%=e.getName()%>
                    </td>
                    <td><%=e.getSalary()%>
                    </td>
                    <td><%=e.getAge()%>
                    </td>
                    <td>
                        <a href="delete.do?id=<%=e.getId()%>">delete emp</a>&nbsp;
                        <a href="load.do?id=<%=e.getId()%>">update emp</a>
                    </td>
                </tr>
                <%
                    }
                %>
            </table>
            <p>
                <input type="button" class="button" value="增加员工" onclick="location='addEmp.html'"/>
            </p>
        </div>
    </div>
    <div id="footer">
        <div id="footer_bg">
            ABC@126.com
        </div>
    </div>
</div>
</body>
</html>
