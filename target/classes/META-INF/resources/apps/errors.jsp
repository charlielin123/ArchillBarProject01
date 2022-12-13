<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html lang="en">

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>統一異常處理頁面</title>
	</head>

	<body>
		<%@ include file="layout/navbar.jsp" %>
			<div class="container text-center mt-3">
				<div class="card w-75 m-auto">
					<div class="card-header bg-dark text-white ">
						<h1>錯誤資訊:</h1>
					</div>
					<div class="card-body">
						<h2 class="text-danger">${ex}</h2>
					</div>
					<div class="card-footer">
						<button type="button" class="btn btn-dark"><a href="/" class="text-light">回首頁</a></button>
					</div>
				</div>
			</div>
	</body>

	</html>