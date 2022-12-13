<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
      <c:set var="contextRoot" value="${pageContext.request.contextPath}" />
      <!DOCTYPE html>
      <html>

      <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>edit member</title>
      </head>

      <body>


        <jsp:include page="../layout/navbar.jsp"></jsp:include>
        <form action="${contextRoot}/member/editmember"></form>
        <div class="container mt-2">
          <div class="card w-100 w-md-50 m-auto">
            <div class="card-header  bg-dark text-light">
              編輯會員資料
            </div>
            <div class="card-body w-75 m-auto">
              <form action="${contextRoot}/member/update/${LoginOK.m_id}" method="post" enctype="multipart/form-data">
                <div class="text-center mb-3">
                  <c:choose>
                    <c:when test="${LoginOK.img == 'data:image/jpeg;base64,'}">
                      <img width="200px" id="previewImg" src="${contextRoot}/apps/images/membericon.png">
                    </c:when>
                    <c:otherwise>
                      <img width="200px" id="previewImg" src="${LoginOK.img}">
                    </c:otherwise>
                  </c:choose>
                </div>
                <div class="form-group mb-3 row">
                  <label for="image" class="input-group-text bg-dark text-light">頭像</label>
                  <input type="file" class="form-control-file" id="image" name="photo">
                </div>
                <div class="form-group mb-3 row">
                  <label for="Name" class="input-group-text bg-dark text-light">姓名 : </label>
                  <input type="text" name="memberName" id="Name" value="${LoginOK.memberName}" required="required"
                    pattern="^[A-Za-z0-9\u4e00-\u9fa5]+$">
                </div>
                <div class="form-group ">
                  <input type="hidden" name="password" id="pswd" value="${LoginOK.password}">
                </div>
                <div class="form-group mb-3 row">
                  <c:choose>
                    <c:when test="${LoginOK.sex == 'M'}">
                      <label for="sex" class="input-group-text bg-dark text-light">性別</label>
                      <select name="sex" id="sex">
                        <option value="M" selected>男</option>
                        <option value="F">女</option>
                        <option value="N">不便透露</option>
                      </select>
                    </c:when>
                    <c:when test="${LoginOK.sex == 'F'}">
                      <label for="sex" class="input-group-text bg-dark text-light">性別</label>
                      <select name="sex" id="sex">
                        <option value="M">男</option>
                        <option value="F" selected>女</option>
                        <option value="N">不便透露</option>
                      </select>
                    </c:when>
                    <c:otherwise>
                      <label for="sex" class="input-group-text bg-dark text-light">性別</label>
                      <select name="sex" id="sex">
                        <option value="M">男</option>
                        <option value="F">女</option>
                        <option value="N" selected>不便透露</option>
                      </select>
                    </c:otherwise>
                  </c:choose>
                </div>
                <div class="form-group mb-3 row">
                  <label for="phoneNum" class="input-group-text bg-dark text-light">手機 :</label>
                  <input type="text" name="phone" id="phoneNum" value="${LoginOK.phone}" required="required"
                    maxlength="11" pattern="09\d{2}-\d{6}" oninput="setCustomValidity('');"
                    oninvalid="setCustomValidity('請輸入正確的手機號瑪格式:09xx-xxxxxx');" />
                </div>
                <div class="form-group ">
                  <input type="hidden" name="email" id="email" value="${LoginOK.email}">
                </div>
                <div class="form-group mb-3 row">
                  <label for="birthday" class="input-group-text bg-dark text-light">生日 :</label>
                  <input type="date" name="birthday" id="birthday" required="required" min="1900-01-01" max="2004-12-31"
                    value='<fmt:formatDate pattern="yyyy-MM-dd" value="${LoginOK.birthday}"/>'>
                </div>
                <div class="form-group">
                  <input type="hidden" name="regDate" id="regDate" value="<fmt:formatDate pattern=" yyyy-MM-dd"
                    value="${LoginOK.regDate}" />" >
                </div>
                <div class="form-group mb-3 row">
                  <input type="submit" class="btn btn-dark" value="送出">
                </div>
                <div class="form-group mb-3 row">
                  <a type="button" class="btn btn-dark" href="${contextRoot}/member/viewmember">回前頁</a>
                </div>

              </form>
            </div>
          </div>
        </div>
        <script>

          $("#image").change(function () {
            //上傳圖片檔案改變後觸發
            console.log('hi')
            readURL(this);  // this代表<input id="imgInp">
          })
          //API FileReader讀取圖片file進行預覽
          function readURL(input) {
            if (input.files && input.files[0]) {
              var reader = new FileReader();
              reader.onload = function (e) {
                $("#previewImg").attr('src', e.target.result);
              }
              reader.readAsDataURL(input.files[0]);
            }
          }


        </script>


      </body>

      </html>