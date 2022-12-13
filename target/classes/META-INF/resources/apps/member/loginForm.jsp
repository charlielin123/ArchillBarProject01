<%-- 執行本網頁之前，會先執行_02_login.filter.FindUserPassword.java這個過濾器。執行過濾器目的 在檢視請求物件是否含有帳號與密碼等資料。 本網頁 login.jsp
    提供登入的畫面，讓使用者輸入帳號與密碼。輸入完畢後，按下Submit按鈕，瀏覽器 會帳號與密碼給 <Form>標籤action屬性對應的程式: _02_login.controller.LoginServlet.java，
    由該Servlet來檢查帳號與密碼是否正確。

    --%>

    <!DOCTYPE HTML>
    <html>

    <head>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
                <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
                    <c:set var="dataRoot" value="${pageContext.request.contextPath}/apps" />
                    <meta charset="UTF-8">
                    <title>登入</title>
                    <meta http-equiv="X-UA-Compatible" content="IE=edge">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <!-- 引入共同的頁首 -->
                    <jsp:include page="../layout/navbar.jsp" />
                    <link rel="stylesheet" href="${dataRoot}\member\styles\loginform.css">
                    <style type="text/css">
                        .error {
                            color: red;
                            display: inline-block;
                            font-size: 10pt;
                        }
                    </style>

    </head>

    <body>
        <!-- 下列敘述設定變數funcName的值為LOG，top.jsp 會用到此變數 -->
        <c:set var="funcName" value="LOG" scope="session" />
        <c:set var="msg" value="登入" />
        <c:if test="${ ! empty sessionScope.timeOut }">
            <!-- 表示使用逾時，重新登入 -->
            <c:set var="msg" value="<font color='red'>${sessionScope.timeOut}</font>" />
        </c:if>

        <div class="container m-auto outside">
            <div class="card" id="login-box">
                <div class="card-header bg-dark text-white">登入</div>
                <form:form method="POST" modelAttribute="loginBean">
                    <div class="card-body ">
                      
                        <div class="row mb-3">
                            <label for="" class="input-group-text bg-dark text-light">信箱</label>
                            <form:input path="email" />
                            <form:errors path="email" cssClass="error" /><br>
                        </div>

                        <div class="row mb-3">
                            <label for="" class="input-group-text bg-dark text-light">密碼</label>
                            <form:input type="password" path="password" />
                            <form:errors path="password" cssClass="error" /><br>
                        </div>
                        <div class="row">
                            <form:errors path="invalidCredentials" cssClass="error" style="padding:0 ; padding-left: 1px;"/>
                        </div>
                        <div class="row mb-3 gd">
                            <div class="col p-0">
                                <div class="left ">
                                    <form:checkbox path="rememberMe" />
                                    <span>記住信箱</span>
                                </div>
                                <div class="right ">
                                    <a href="/forgetpassword" class="btn btn-dark">忘記密碼</a>
                                </div>
                            </div>


                        </div>

                        <div class="row mb-3">
                            <input type="submit" class="btn btn-dark" value="登入">
                        </div>
                    </div>
                </form:form>
            </div>
        </div>


        <!-- Modal -->
        <div class="modal fade" id="error-box" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header bg-dark text-white">
                        <h2 class="modal-title" id="exampleModalLabel">登入資訊</h2>
                        
                    </div>
                    <div class="modal-body text-center">
                        <h4 class="text-danger">${error}</h4>
                        <h4 style="color: green;">${success}</h4>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">關閉</button>
                    </div>
                </div>
            </div>
        </div>

        <script>
            var errorBox = new bootstrap.Modal(document.getElementById('error-box'), {

            })

        </script>


        <%if(request.getAttribute("success")!=null){ %>
            <script>

                $("#error-box .modal-footer").remove();
                $("#error-box .modal-body").append(`<h4>1秒後重新載入</h4>`)
                setTimeout(function () {
                    window.location.href = "/";
                }, 1500);
                errorBox.show();
                $(window).click(function () {
                    window.location.href = "/";
                })
            </script>
            <% }%>
                <%if(request.getAttribute("error")!=null){ %>
                    <script>

                        errorBox.show();
                    </script>
                    <% }%>
    </body>

    </html>