<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <c:set var="contextRoot" value="${pageContext.request.contextPath}" />
    <c:set var="dataRoot" value="${pageContext.request.contextPath}/apps" />

    <!DOCTYPE html>
    <html>

    <head>
      <meta charset="UTF-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <link rel="stylesheet" href="${dataRoot}\menu\styles\navAndBg.css">
    </head>

    <body>
      <script src="${dataRoot}/js/jquery-3.6.1.min.js"></script>
      <script src="${dataRoot}/menu/js/navAndBg.js"></script>
    </body>

    </html>
