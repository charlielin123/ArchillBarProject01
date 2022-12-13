<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

      <c:set var="contextRoot" value="${pageContext.request.contextPath}" />


      <!DOCTYPE html>
      <html>

      <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>signup</title>
      </head>

      <body>
        <jsp:include page="../layout/navbar.jsp"></jsp:include>
        <div class="container mt-2">
          <div class="card w-75 m-auto signcard">
            <div class="card-header bg-dark text-light">
              註冊會員
            </div>
            <div class="card-body w-75 m-auto">
              <form action="${contextRoot}/member/insert" method="post" enctype="multipart/form-data">
                <div class="text-center mb-3">
                  <img width="200px" id="previewImg" src="${contextRoot}/apps/images/membericon.png" alt="">
                </div>
                <div class="mb-3">
                  <label for="image" class="form-label">頭像</label>
                  <input type="file" class="form-control" id="image" name="photo"
                    value="${contextRoot}/apps/images/membericon.png" accept="image/*">
                </div>
                <div class="mb-3 ">
                  <label for="Name" class="form-label ">姓名 </label><span style="color: red;">*</span>
                  <input type="text" class="form-control" name="memberName" id="Name" required="required"
                    pattern="^[A-Za-z0-9\u4e00-\u9fa5]+$" placeholder="不可使用特殊字符">
                </div>
                <div class="mb-3 ">
                  <label for="pswd" class="form-label">密碼</label><span style="color: red;">*</span>
                  <input id="pswd" name="password" class="form-control" type="password"
                    pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,16}$" required="required"
                    oninput="setCustomValidity('');" oninvalid="setCustomValidity('請輸入正確的密碼格式:含大小英文、數，長度6~16個字元');"
                    placeholder="含大小英文、數，長度6~16個字元" />
                </div>
                <label for="email" class="form-label">電子信箱</label><span style="color: red;">*</span>
                <div class="input-group mb-3">
                  <input type="email" class="form-control" name="email" id="email" placeholder="電子郵件地址">
                  <button class="btn btn-dark " type="button" id="emailcheckbtn">檢查信箱</button>
                </div>
                <div class="mb-3">
                  <span id="emailCheck"></span>
                </div>

                <div class=" mb-3 ">
                  <label for="sex" class="form-label">性別</label>
                  <select name="sex" id="sex" class="form-control">
                    <option value="M">男</option>
                    <option value="F">女</option>
                    <option value="N" selected>不便透露</option>
                  </select>
                </div>
                <div class="mb-3">
                  <label for="phoneNum" class="form-label">手機</label><span style="color: red;">*</span>
                  <input type="text" class="form-control" name="phone" id="phoneNum" required="required" maxlength="11"
                    pattern="09\d{2}-\d{6}" placeholder="09xx-xxxxxx" oninput="setCustomValidity('');"
                    oninvalid="setCustomValidity('請輸入正確的手機號瑪格式:09xx-xxxxxx');" />
                </div>

                <div class=" mb-3">
                  <label for="birthday" class="form-label">生日</label><span style="color: red;">*</span>
                  <input type="date" class="form-control" name="birthday" id="birthday" required="required"
                    min="1900-01-01" max="2004-12-31">
                </div>
                <div class=" mb-3">
                  <input type="submit" class="btn btn-dark" id="signup" value="註冊" disabled>
                  <a type="button" class="btn btn-dark" href="${contextRoot}/member/login">已有會員</a>
                </div>
              </form>
            </div>
          </div>
        </div>
        <script>
          //圖片預覽
          $("#image").change(function () {
            //上傳圖片檔案改變後觸發
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

          //信箱檢查
          $("#emailcheckbtn").click(function () {
            let emailText = $('#email').val();
            $.ajax({
              url: '/checkemail',
              contentType: 'application/json;chartset=UTF-8',
              method: 'get',
              data: { "email": emailText },
              success: function (result) {
                if (result == 'OK') {
                  $('#signup').removeAttr('disabled');
                  $('#email').attr('readonly', 'readonly')
                  $('#emailCheck').empty();
                  $('#emailcheckbtn').text('OK').removeClass('btn-dark').addClass('btn-success').attr('disabled', 'disabled');
                } else {

                  $('#emailCheck').text(result).css('color', 'red');
                }

              }
            })
          });
          // 視窗大小參數
          const md = '768';
          const lg = '992';
          const sm = '576';
          //// 視窗監聽調整註冊card版面

          function checklistbtn() {
            var wWidth = $(window).width();
            if (wWidth >= md) {
              // console.log('hi');
              $('.signcard').removeClass('w-100')
              $('.signcard').addClass('w-75');
            } else {
              $('.signcard').removeClass('w-75');
              $('.signcard').addClass('w-100');
            }
          }
          $(window).resize(checklistbtn);
        </script>
      </body>

      </html>
