<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Prasad
  Date: 7/29/2022
  Time: 9:56 AM
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

<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${emp != null}">
                <form action="update" method="post"/>
            </c:if>
            <c:if test="${emp == null}">
                <form action="insert" method="post"/>
            </c:if>

            <caption>
                <c:if test="${emp != null}">
                    <h3 class="text-center">Edit Employee</h3>
                </c:if>
                <c:if test="${emp == null}">
                    <h3 class="text-center">Add New Employee</h3>
                </c:if>
            </caption>

            <c:if test="${emp != null}">
                <input type="hidden" name="id" value="<c:out value='${emp.id}' />" />
            </c:if>

            <fieldset class="form-group">
                <label>Employee name</label> <input type="text"
                                                 value="<c:out value='${emp.name}' />" class="form-control"
                                                 name="name" required="required" minlength="5">
            </fieldset>
            <fieldset class="form-group">
                <label>Position</label> <input type="text"
                                                 value="<c:out value='${emp.position}' />" class="form-control"
                                                 name="position" required="required" minlength="5">
            </fieldset>
            <fieldset class="form-group">
                <label>Department</label> <input type="text"
                                                 value="<c:out value='${emp.department}' />" class="form-control"
                                                 name="department" required="required" minlength="5">
            </fieldset>

            <fieldset class="form-group">
                <label>Hire Date</label> <input type="date"
                                                       value="<c:out value='${emp.hire_date}' />" class="form-control"
                                                       name="hire_date" required="required">
            </fieldset>
            <button type="submit" class="btn btn-success">Save</button>
        </div>

    </div>
</div>
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
