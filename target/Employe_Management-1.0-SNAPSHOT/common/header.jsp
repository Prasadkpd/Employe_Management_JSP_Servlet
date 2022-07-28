<%--
  Created by IntelliJ IDEA.
  User: Prasad
  Date: 7/28/2022
  Time: 5:22 PM
  To change this template use File | Settings | File Templates.
--%>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background:#1f2c46;color:white">
        <div>
            <a class="navbar-brand">Employee Management</a>
        </div>

        <ul class="navbar-nav navbar-collapse justify-content-end" style="display: flex; flex-direction: row;align-items: center;">
            <li>
                <a href="<%= request.getContextPath()%>/login" class="nav-link">Login</a>
            </li>
            <li>
                <a href="<%= request.getContextPath()%>/register" class="nav-link">Signup</a>
            </li>
        </ul>
    </nav>
</header>