<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8">
			<meta http-equiv="X-UA-Compatible" content="IE=edge">
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
			<title>update</title>
		</head>

		<body>
			<jsp:include page="../layout/navbar.jsp"></jsp:include>
			<div class="container text-light">
				<h2>更新成功</h2>
				<p>3秒後自動轉跳回首頁</p>
				<a type="button" class="btn btn-dark" href="${contextRoot}/">回首頁</a>
				<script>
					window.setInterval("location='${contextRoot}/'", 3000)
				</script>
			</div>
		</body>

		</html>