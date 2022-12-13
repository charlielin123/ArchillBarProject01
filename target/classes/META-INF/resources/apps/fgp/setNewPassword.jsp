<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
      <c:set var="contextRoot" value="${pageContext.request.contextPath}" />
      <!DOCTYPE html>
      <html>

      <head>
        <meta charset="UTF-8">
        <title>SetNewPassword</title>
      </head>

      <body>
        <jsp:include page="../layout/navbar.jsp"></jsp:include>
        <div class="container mt-2">
          <div class="card  w-100 w-md-50 m-auto">
            <div class="card-header bg-dark text-light">
              修改密碼
            </div>
            <form action="${contextRoot}/setNewPasword" method="post" enctype="multipart/form-data">
              <div class="card-body w-75 m-auto">
                <div class="form-group mb-3 row">
                  <label for="pswd" class="input-group-text bg-dark text-light">新密碼</label>
                  <input id="newpswd" name="newpassword" type="password"
                    pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,16}$" required="required"
                    oninput="setCustomValidity('');" oninvalid="setCustomValidity('請輸入正確的密碼格式:含大小英文、數，長度6~16個字元');"
                    placeholder="含大小英文、數，長度6~16個字元" />
                </div>
                <div class="form-group mb-3 row">
                  <label for="ConfirmPassword" class="input-group-text bg-dark text-light">確認密碼</label>
                  <input name="ConfirmPassword" id="ConfirmPassword" placeholder="確認密碼" type="password"
                    required="required" oninput="setCustomValidity('');"
                    onchange="if(document.getElementById('newpswd').value != document.getElementById('ConfirmPassword').value){setCustomValidity('密碼不吻合');}" />
                </div>
                <div class="form-group mb-3 row">
                  <input type="submit" class="btn btn-dark" value="送出">
                </div>
              </div>
            </form>
          </div>
        </div>
      </body>

      </html>