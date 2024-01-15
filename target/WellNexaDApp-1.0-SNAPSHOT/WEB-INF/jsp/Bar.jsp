<header>
    <c:if test="${sessionScope.user == null}">
        <div class="navbar">
            <a class="left" href="${pageContext.request.contextPath}/index.jsp">HOME</a>
            <a class="right" href="${pageContext.request.contextPath}/AccountServlet/login_page">Login</a>
            <a class="right" href="${pageContext.request.contextPath}/AccountServlet/registration_page">Registrazione</a>
        </div>
    </c:if>

    <c:if test="${sessionScope.user != null}"> <!--pageContext.request.getSession().getAttribute('user') != null -->
        <div class="navbar">
            <a class="left" href="${pageContext.request.contextPath}/index.jsp">HOME</a>
            <a class="right" href="${pageContext.request.contextPath}/AccountServlet/logout_page">Logout</a>
        </div>
    </c:if>
</header>



