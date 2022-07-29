<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Prasad
  Date: 7/29/2022
  Time: 10:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee Add & Edit</title>

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: #1f2c46">
        <div>
            <a href="/Employe_Management_war_exploded/" class="navbar-brand">Employee Management</a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">Employees</a></li>
        </ul>

        <ul class="navbar-nav navbar-collapse justify-content-end">
            <li><a href="<%=request.getContextPath()%>/logout"
                   class="nav-link">Logout</a></li>
        </ul>
    </nav>
</header>
 <div class="container">
        <h3 class="text-center">List Of Employees</h3>
        <hr>

        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/add"
               class="btn btn-success">Add Employee</a>
        </div>
        <br>

        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Name</th>
                <th>Position</th>
                <th>Department</th>
                <th>Hire Date</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="emp" items="${listEmp}">

                <tr>
                    <td><c:out value="${emp.name}" /></td>
                    <td><c:out value="${emp.position}" /></td>
                    <td><c:out value="${emp.department}" /></td>
                    <td><c:out value="${emp.hire_date}" /></td>

                    <td><a href="edit?id=<c:out value='${emp.id}'/>">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a href="remove?id=<c:out value='${emp.id}' />">Remove</a></td>

                </tr>
            </c:forEach>
            </tbody>

        </table>

    </div>
<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>