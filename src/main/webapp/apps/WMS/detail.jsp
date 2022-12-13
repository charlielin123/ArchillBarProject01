<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <c:set var="contextRoot" value="${pageContext.request.contextPath}" />
        <html lang="en">

        <head>

            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>ArchilBarIndex</title>

            <link rel="stylesheet" href=${contextRoot}/apps/css/style.css>
            <link rel="stylesheet" href=${contextRoot}/apps/WMS/css/product_style.css>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
            <!-- <script src="apps\WMS\js\javascript.js"></script> -->
            <link rel="stylesheet" href="${contextRoot}/apps/WMS/css/backbar.css">
            <link rel="stylesheet" href=${contextRoot}\apps\WMS\css\barli.css>

        </head>
        <style>
            .tabdiv {
                width: 100%;
            }

            .tableout {
                margin-right: 10px;
                text-align: center;

            }

            .pageout {
                width: 100%;
            }

            .pageds {
                margin: 0 auto;
                width: 150px;
                height: auto;
                /* background: rgba(132, 115, 115, 0.4); */


                color: #010201;
                font-size: 28px;
                text-align: center;
                border-radius: 25px 25px 25px 25px;
                background-color: rgb(243, 243, 243);
                margin-bottom: 30px;

            }

            .tabside {
                margin: 10px;

            }
        </style>
        <script>

        </script>

        <body>
            <!-- 主頁進入畫面 -->
            <!-- 管理頁籤 -->
            <div class="all">
                <!-- 左邊 -->
                <div class="container-left">

                    <!-- 左邊結束 -->
                </div>
                <!-- Header部分 -->

                <div style="background-color: rgba(83, 78, 78, 0.5);width: 100%;  margin-right: 0.5vh; margin-left: 0.5vh;   border-radius: 15px;"
                    class="contauner-right">
                    <!-- 標題 -->

                    <div class="pageout">

                        <!-- 返回目前按鈕 -->
                        <c:choose>
                            <c:when test="${b_status<3}">

                                <a href="/bak/ordertest">

                                    <div style="text-align:left;">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="60" height="60"
                                            style="color: #f8f8f8;" fill="currentColor" class="bi bi-arrow-left-short"
                                            viewBox="0 0 16 16">
                                            <path fill-rule="evenodd"
                                                d="M12 8a.5.5 0 0 1-.5.5H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5a.5.5 0 0 1 .5.5z" />
                                        </svg>
                                    </div>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="/bak/pastorder">

                                    <div style="text-align:left;">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="60" height="60"
                                            style="color: #f8f8f8;" fill="currentColor" class="bi bi-arrow-left-short"
                                            viewBox="0 0 16 16">
                                            <path fill-rule="evenodd"
                                                d="M12 8a.5.5 0 0 1-.5.5H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5a.5.5 0 0 1 .5.5z" />
                                        </svg>
                                    </div>
                                </a>


                            </c:otherwise>
                        </c:choose>
                        <div class="pageds">訂單明細</div>

                    </div>

                    <!-- 新增商品alert -->
                    <div class="order-history tabdiv">
                        <div class="tabside">

                            <jsp:include page="../layout/navbar.jsp"></jsp:include>


                            <table class="table table-dark table-hover tableout">
                                <c:choose>
                                    <c:when test="${b_status<3}">
                                        <thead>
                                            <tr>
                                                <th scope="col">編號</th>
                                                <th scope="col">商品</th>
                                                <th scope="col">單價</th>
                                                <th scope="col">數量</th>
                                                <th scope="col">小計</th>
                                                <th scope="col"></th>

                                                <th scope="col" style="">
                                                    <div style="background-color: #899dd4; width: 30px; margin:0 auto;">
                                                    </div>
                                                </th>
                                            </tr>
                                        </thead>

                                        <c:forEach var="qq" items="${detail}">
                                            <tr>

                                                <td id="pid">${qq.p_id}</td>
                                                <td>${qq.product.p_name }</td>
                                                <td>${qq.product.price}</td>
                                                <td id="qt">${qq.quantity}</td>
                                                <td>${qq.product.price*qq.quantity}</td>
                                                <td>
                                                    <button type="button" class="btn btn-secondary "
                                                        data-bs-toggle="modal"
                                                        data-bs-target="#productUpdate${qq.pk}">修改數量</button>

                                                </td>
                                                <td>

                                                    <button class="btn btn-secondary del-btn" id="${qq.pk}">刪除</button>
                                                </td>

                                            </tr>

                                            <!-- productUpdate修改視窗頁面 -->
                                            <div class="modal fade" id="productUpdate${qq.pk}" data-bs-backdrop="static"
                                                data-bs-keyboard="false" tabindex="-1"
                                                aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="staticBackdropLabel">
                                                                ${qq.product.p_name}</h5>
                                                            <button type="button" class="btn-close"
                                                                data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body ">
                                                            <div class="input-group input-group-lg">
                                                                <span class="input-group-text"
                                                                    id="inputGroup-sizing-lg">數量</span>
                                                                <input type="number" max="10" min="1" value="1"
                                                                    class="form-control "
                                                                    aria-label="Sizing example input"
                                                                    aria-describedby="inputGroup-sizing-lg"
                                                                    id="q${qq.pk}">
                                                            </div>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary"
                                                                data-bs-dismiss="modal">關閉</button>
                                                            <button type="button" class="btn btn-primary update-btn"
                                                                id="${qq.pk}">送出</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>


                                        </c:forEach>


                                        <tr>


                                            <td>
                                                <button id="${detail[0].order.o_id}" type="button"
                                                    class="btn btn-primary oid" data-bs-toggle="modal"
                                                    data-bs-target="#staticBackdrop">
                                                    +
                                                </button>


                                                <!-- Button trigger modal -->


                                                <!-- Modal -->

                                                <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
                                                    data-bs-keyboard="false" tabindex="-1"
                                                    aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">

                                                            <!-- ......內容物..... -->

                                                            <div style="height:20px;"></div>
                                                            <div class="row">
                                                                <div class="col-4">
                                                                    <div class="list-group" id="list-tab"
                                                                        role="tablist">
                                                                        <a class="list-group-item list-group-item-action active"
                                                                            id="list-home-list" data-bs-toggle="list"
                                                                            href="#list-1" role="tab"
                                                                            aria-controls="list-home">原創調酒</a>
                                                                        <a class="list-group-item list-group-item-action"
                                                                            id="list-profile-list" data-bs-toggle="list"
                                                                            href="#list-2" role="tab"
                                                                            aria-controls="list-profile">前菜</a>
                                                                        <a class="list-group-item list-group-item-action"
                                                                            id="list-messages-list"
                                                                            data-bs-toggle="list" href="#list-3"
                                                                            role="tab"
                                                                            aria-controls="list-messages">甜點</a>
                                                                        <a class="list-group-item list-group-item-action"
                                                                            id="list-settings-list"
                                                                            data-bs-toggle="list" href="#list-4"
                                                                            role="tab"
                                                                            aria-controls="list-settings">炸物</a>
                                                                        <a class="list-group-item list-group-item-action"
                                                                            id="list-messages-list"
                                                                            data-bs-toggle="list" href="#list-5"
                                                                            role="tab"
                                                                            aria-controls="list-messages">主餐</a>
                                                                        <a class="list-group-item list-group-item-action"
                                                                            id="list-messages-list"
                                                                            data-bs-toggle="list" href="#list-6"
                                                                            role="tab"
                                                                            aria-controls="list-messages">經典調酒</a>
                                                                        <a class="list-group-item list-group-item-action"
                                                                            id="list-messages-list"
                                                                            data-bs-toggle="list" href="#list-7"
                                                                            role="tab"
                                                                            aria-controls="list-messages">湯品</a>

                                                                    </div>
                                                                </div>

                                                                <div class="col-8">
                                                                    <div class="tab-content" id="nav-tabContent">
                                                                        <div class="tab-pane fade show active text-dark"
                                                                            id="list-1" role="tabpanel"
                                                                            aria-labelledby="list-home-list">

                                                                            <div style="text-align:left; width: 100%; ">
                                                                                產品名稱 :
                                                                            </div>
                                                                            <div class="tdiv"
                                                                                style="overflow:auto; height:50vh;">
                                                                                <c:forEach var="addproduct"
                                                                                    items="${product}">
                                                                                    <c:choose>
                                                                                        <c:when
                                                                                            test="${addproduct.type == 'classicAlcoho'}">
                                                                                            <table
                                                                                                class="table table-hover"
                                                                                                rules="none">
                                                                                                <td
                                                                                                    style="font-size: 15px;">
                                                                                                    <div class="tdpro"
                                                                                                        id=${addproduct.p_id}>
                                                                                                        ${addproduct.p_name}
                                                                                                    </div>
                                                                                                </td>
                                                                                            </table>

                                                                                        </c:when>
                                                                                    </c:choose>
                                                                                </c:forEach>

                                                                            </div>

                                                                        </div>
                                                                        <div class="tab-pane fade text-dark" id="list-2"
                                                                            role="tabpanel"
                                                                            aria-labelledby="list-profile-list">
                                                                            <div style="text-align:left; width: 100%; ">
                                                                                產品名稱 :
                                                                            </div>
                                                                            <div class="tdiv"
                                                                                style="overflow:auto; height:50vh;">
                                                                                <c:forEach var="addproduct"
                                                                                    items="${product}">
                                                                                    <c:choose>
                                                                                        <c:when
                                                                                            test="${addproduct.type == 'appetizer'}">
                                                                                            <table
                                                                                                class="table table-hover"
                                                                                                rules="none">
                                                                                                <td
                                                                                                    style="font-size: 15px;">
                                                                                                    <div class="tdpro"
                                                                                                        id=${addproduct.p_id}>
                                                                                                        ${addproduct.p_name}
                                                                                                    </div>
                                                                                                </td>
                                                                                            </table>
                                                                                        </c:when>
                                                                                    </c:choose>
                                                                                </c:forEach>
                                                                            </div>

                                                                        </div>
                                                                        <div class="tab-pane fade text-dark" id="list-3"
                                                                            role="tabpanel"
                                                                            aria-labelledby="list-messages-list">
                                                                            <div style="text-align:left; width: 100%; ">
                                                                                產品名稱 :
                                                                            </div>
                                                                            <div class="tdiv"
                                                                                style="overflow:auto; height:50vh;">
                                                                                <c:forEach var="addproduct"
                                                                                    items="${product}">
                                                                                    <c:choose>
                                                                                        <c:when
                                                                                            test="${addproduct.type == 'dessert'}">
                                                                                            <table
                                                                                                class="table table-hover"
                                                                                                rules="none">
                                                                                                <td
                                                                                                    style="font-size: 15px;">
                                                                                                    <div class="tdpro"
                                                                                                        id=${addproduct.p_id}>
                                                                                                        ${addproduct.p_name}
                                                                                                    </div>
                                                                                                </td>
                                                                                            </table>
                                                                                        </c:when>
                                                                                    </c:choose>
                                                                                </c:forEach>
                                                                            </div>
                                                                        </div>
                                                                        <div class="tab-pane fade text-dark" id="list-4"
                                                                            role="tabpanel"
                                                                            aria-labelledby="list-settings-list">
                                                                            <div style="text-align:left; width: 100%; ">
                                                                                產品名稱 :
                                                                            </div>
                                                                            <div class="tdiv"
                                                                                style="overflow:auto; height:50vh;">
                                                                                <c:forEach var="addproduct"
                                                                                    items="${product}">
                                                                                    <c:choose>
                                                                                        <c:when
                                                                                            test="${addproduct.type == 'fried'}">
                                                                                            <table
                                                                                                class="table table-hover"
                                                                                                rules="none">
                                                                                                <td
                                                                                                    style="font-size: 15px;">
                                                                                                    <div class="tdpro"
                                                                                                        id=${addproduct.p_id}>
                                                                                                        ${addproduct.p_name}
                                                                                                    </div>
                                                                                                </td>
                                                                                            </table>
                                                                                        </c:when>
                                                                                    </c:choose>
                                                                                </c:forEach>
                                                                            </div>
                                                                        </div>
                                                                        <div class="tab-pane fade text-dark" id="list-5"
                                                                            role="tabpanel"
                                                                            aria-labelledby="list-settings-list">

                                                                            <div style="text-align:left; width: 100%; ">
                                                                                產品名稱 :
                                                                            </div>
                                                                            <div class="tdiv"
                                                                                style="overflow:auto; height:50vh;">
                                                                                <c:forEach var="addproduct"
                                                                                    items="${product}">
                                                                                    <c:choose>
                                                                                        <c:when
                                                                                            test="${addproduct.type == 'main'}">
                                                                                            <table
                                                                                                class="table table-hover"
                                                                                                rules="none">
                                                                                                <td
                                                                                                    style="font-size: 15px;">
                                                                                                    <div class="tdpro"
                                                                                                        id=${addproduct.p_id}>
                                                                                                        ${addproduct.p_name}
                                                                                                    </div>
                                                                                                </td>
                                                                                            </table>
                                                                                        </c:when>
                                                                                    </c:choose>
                                                                                </c:forEach>
                                                                            </div>
                                                                        </div>
                                                                        <div class="tab-pane fade text-dark" id="list-6"
                                                                            role="tabpanel"
                                                                            aria-labelledby="list-settings-list">
                                                                            <div style="text-align:left; width: 100%; ">
                                                                                產品名稱 :
                                                                            </div>
                                                                            <div class="tdiv"
                                                                                style="overflow:auto; height:50vh;">
                                                                                <c:forEach var="addproduct"
                                                                                    items="${product}">
                                                                                    <c:choose>
                                                                                        <c:when
                                                                                            test="${addproduct.type == 'originalAlcoho'}">
                                                                                            <table
                                                                                                class="table table-hover"
                                                                                                rules="none">
                                                                                                <td
                                                                                                    style="font-size: 15px;">
                                                                                                    <div class="tdpro"
                                                                                                        id=${addproduct.p_id}>
                                                                                                        ${addproduct.p_name}
                                                                                                    </div>
                                                                                                </td>
                                                                                            </table>
                                                                                        </c:when>
                                                                                    </c:choose>
                                                                                </c:forEach>
                                                                            </div>
                                                                        </div>
                                                                        <div class="tab-pane fade text-dark" id="list-7"
                                                                            role="tabpanel"
                                                                            aria-labelledby="list-settings-list">
                                                                            <div style="text-align:left; width: 100%; ">
                                                                                產品名稱 :
                                                                            </div>
                                                                            <div class="tdiv"
                                                                                style="overflow:auto; height:50vh;">
                                                                                <c:forEach var="addproduct"
                                                                                    items="${product}">
                                                                                    <c:choose>
                                                                                        <c:when
                                                                                            test="${addproduct.type == 'soup'}">
                                                                                            <table
                                                                                                class="table table-hover"
                                                                                                rules="none">
                                                                                                <td
                                                                                                    style="font-size: 15px;">
                                                                                                    <div class="tdpro"
                                                                                                        id=${addproduct.p_id}>
                                                                                                        ${addproduct.p_name}
                                                                                                    </div>
                                                                                                </td>
                                                                                            </table>
                                                                                        </c:when>
                                                                                    </c:choose>
                                                                                </c:forEach>
                                                                            </div>
                                                                        </div>

                                                                    </div>
                                                                </div>

                                                            </div>
                                                            <!-- ........... -->
                                                            <div class="modal-footer"
                                                                style="justify-content: space-between;">
                                                                <div style="color:black">品項: <span id="adddsp"></span>
                                                                </div>
                                                                <div style=" display:none;" class="qw">
                                                                    輸入數量: <input id="qtnum" type="number" max="10"
                                                                        min="1" value="1">
                                                                </div>
                                                                <div>
                                                                    <button type="button" class="btn btn-secondary"
                                                                        data-bs-dismiss="modal">取消</button>
                                                                    <button type="button" id="ok"
                                                                        class="btn btn-primary">送出</button>

                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>


                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td>總計:${detail[0].order.total}</td>
                                            <td></td>

                                            <td></td>
                                        </tr>

                                    </c:when>
                                    <c:otherwise>
                                        <thead>
                                            <tr>
                                                <th scope="col">編號</th>
                                                <th scope="col">商品</th>
                                                <th scope="col">單價</th>
                                                <th scope="col">數量</th>
                                                <th scope="col">小計</th>
                                                <th scope="col"></th>


                                            </tr>
                                        </thead>
                                        <c:forEach var="qq" items="${detail}">
                                            <tr>

                                                <td id="pid">${qq.p_id}</td>
                                                <td>${qq.product.p_name }</td>
                                                <td>${qq.product.price}</td>
                                                <td id="qt">${qq.quantity}</td>
                                                <td>${qq.product.price*qq.quantity}</td>
                                                <td>

                                                </td>


                                            </tr>
                                        </c:forEach>
                                        <tr>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td>總計:${detail[0].order.total}</td>
                                            <td></td>
                                        </tr>


                                    </c:otherwise>
                                </c:choose>
                            </table>
                        </div>
                    </div>

                </div>
            </div>
            </div>


            <!-- jquery -->
            <script src="/apps/WMS/js/back_bar.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/animejs/2.0.2/anime.min.js"></script>

            <script>
                let p_id = "";
                //新增產品處出按鈕
                $('#ok').click(function () {

                    let oid1 = $('.oid').attr('id');
                    let quantity1 = $('#qtnum').val();
                    console.log(oid1);
                    console.log(p_id);
                    console.log(quantity1);
                    let file = JSON.stringify({
                        "o_id": oid1,
                        "p_id": p_id,
                        "quantity": quantity1
                    })
                    $.ajax({
                        url: '/bak/order/addProductDs2',
                        contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
                        method: 'post',
                        // dataType: 'json', // 回傳的資料型別
                        data: file,
                        success: function (result) {
                            alert(result)
                            window.location.reload();
                        },
                        error: function (err) {
                            console.log(err);
                        }
                    })



                })

                //選擇產品後顯示產品資訊
                $('.tdpro').click(function () {
                    let p_name = $(this).text()
                    p_id = $(this).attr('id')
                    // console.log(p_name)
                    $('#adddsp').text(p_name).css('color', 'blue')
                    $('.qw').css('width', '30px;').removeAttr('style').css('color', 'black')

                })



                //修改訂單產品資訊按鈕
                $(".update-btn").click(function () {
                    //detailPK
                    let PK = $(this).attr('id');
                    //產品數量
                    let productQt = $('#q' + PK).val();
                    //送出ajax請求
                    let file = JSON.stringify({
                        "pk": PK,
                        "quantity": productQt
                    })
                    $.ajax({
                        url: '/bak/order/updateProductDs2',
                        contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
                        method: 'post',
                        data: file,
                        success: function (result) {
                            alert(result)
                            window.location.reload();
                        },
                        error: function (err) {
                            console.log(err);
                        }
                    })
                })



                //刪除按鈕
                $('.del-btn').click(function () {
                    let pk = $(this).attr('id');
                    $.ajax({
                        url: '/bak/order/delOrderDsProduct/' + pk,
                        method: 'get',
                        success: function (result) {
                            alert(result)
                            window.location.reload();
                        },
                        error: function (err) {
                            console.log(err);
                        }
                    })
                })


        // 按鈕顏色
        // ===========================================
            </script>
        </body>

        </html>