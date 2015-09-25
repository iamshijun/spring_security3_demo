<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:include page="../common/header.jsp">
	<jsp:param name="pageTitle" value="Change Password"/>
</jsp:include>

<h1>Change Password</h1>
<form method="post">
	<%-- Ch 4 UserDetailsManager --%>
	<label for="oldPassword">Old Password</label>:
	<input id="oldPassword" name="oldPassword" size="20" maxlength="50" type="password"/>
	<br />

	<label for="password">New Password</label>:
	<input id="password" name="password" size="20" maxlength="50" type="password"/>
	<br />

	<input type="submit" value="Change Password"/>	
</form>

<jsp:include page="../common/footer.jsp"/>