<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html >
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<c:url value="/styles/style.css" var="cssUrl"/>
		<link rel="stylesheet" type="text/css" href="${cssUrl}"/>
		<title>JBCP Pets: ${pageTitle}</title>
	</head>
	<body>
		<div id="header"><ul>
			<c:url value="/" var="homeUrl"/>
			<li><a href="${homeUrl}">Home</a></li>
		
				
			<%-- <c:url value="/login.do" var="loginUrl"/>
			<li><a href="${loginUrl}">Log In</a></li> --%>
					
			<%-- <c:if test="${showLoginLink}">
				<c:url value="/login.do" var="loginUrl"/>
				<li><a href="${loginUrl}">Log In 2</a></li>
			</c:if> --%>
			
			<!-- Ch 5 taglib experimentation  -->
			
			<%-- <sec:authorize url="/login.do">abst
				<c:url value="/login.do" var="loginUrl"/>
				<li><a href="${loginUrl}">Log In</a>(with 'url' attr)</li>
			</sec:authorize> --%>
			
			<!-- spring security2 -->
			<sec:authorize ifNotGranted="ROLE_USER">
				<c:url value="/login.do" var="loginUrl"/>
				<li><a href="${loginUrl}">Log In</a></li>
			</sec:authorize>
		
		
			<sec:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
				<c:url value="/logout" var="logoutUrl"/>
				<li><a href="${logoutUrl}">Log Out</a></li>
			</sec:authorize>
			
			
			<%-- <c:url value="/account/home.do" var="accountUrl"/>
			<li><a href="${accountUrl}">My Account</a></li> --%>
			
			<!-- 使用SpEL和authorize中的 url属性的话 是spring security3的新增功能 -->
			<%-- 
			<sec:authorize url="/account/home.do" method="GET">
				<c:url value="/account/home.do" var="accountUrl"/>
				<li><a href="${accountUrl}">My Account</a> (with 'url' attr)</li>
			</sec:authorize> 
			 --%>
			
			 <sec:authorize access="hasRole('ROLE_USER') and fullyAuthenticated">
				<c:url value="/account/home.do" var="accountUrl"/>
				<li><a href="${accountUrl}">My Account</a> (with 'access' attr)</li>
			</sec:authorize>	
				
			<sec:authorize ifAllGranted="ROLE_USER,ROLE_CUSTOMER,ROLE_PURCHASER">
				<c:url value="/account/orders.do" var="ordersUrl"/>
				<li><a href="${ordersUrl}">My Orders</a></li>
			</sec:authorize>
			
			<c:url value="/wishlist/home.do" var="wishlistUrl"/>
			<li><a href="${wishlistUrl}">My Wishlist</a></li>
		</ul>
		<br />
	</div>
	</body>
</html>

