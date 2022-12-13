<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
			<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

				<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
				<!DOCTYPE html>
				<html>

				<head>
					<meta charset="UTF-8">
					<meta http-equiv="X-UA-Compatible" content="IE=edge">
					<meta name="viewport" content="width=device-width, initial-scale=1.0">
					<link href="${contextRoot}/apps/member/styles/page.css" rel="stylesheet">
					<link href="${contextRoot}/apps/member/styles/reserveData.css" rel="stylesheet">
					<title>View member</title>
				</head>

				<body>
					<jsp:include page="../layout/navbar.jsp"></jsp:include>

					<!-- 會員sidebar畫布 -->
					<div class="listbtn">
						<button class="btn btn-dark " type="button" data-bs-toggle="offcanvas"
							data-bs-target="#offcanvasExample" aria-controls="offcanvasExample">
							<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
								fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
								stroke-linejoin="round" class="feather feather-list">
								<line x1="8" y1="6" x2="21" y2="6"></line>
								<line x1="8" y1="12" x2="21" y2="12"></line>
								<line x1="8" y1="18" x2="21" y2="18"></line>
								<line x1="3" y1="6" x2="3.01" y2="6"></line>
								<line x1="3" y1="12" x2="3.01" y2="12"></line>
								<line x1="3" y1="18" x2="3.01" y2="18"></line>
							</svg>
						</button>


						<div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasExample"
							aria-labelledby="offcanvasExampleLabel">
							<div class="offcanvas-header">
								<h5 class="offcanvas-title" id="offcanvasExampleLabel">會員選單</h5>
								<button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas"
									aria-label="Close"></button>
							</div>
							<div class="offcanvas-body">
								<div class="list-group text-center col-12 " id="list-tab-2" role="tabpanel">
									<a href="#" class="list-group-item list-group-item-dark ">功能列表</a>
									<hr>
									<a type="button" class=" list-group-item 
									list-group-item-dark list-group-item-action active " id="list-userInfo-list " data-bs-toggle="list"
										href="#userInfo" role="tab">會員資料</a>
									<a class="list-group-item list-group-item-dark list-group-item-action "
										id="list-reserveData-list" data-bs-toggle="list" href="#reserveData"
										role="tab">訂單
									</a>

									<hr>
									<c:choose>
										<c:when test="${LoginOK.admin}">
											<a class="list-group-item list-group-item-dark list-group-item-action "
												href="/bak/pro">後臺管理</a>
										</c:when>
									</c:choose>
								</div>
							</div>
						</div>
					</div>

					<div class="row w-100 m-0">
						<div class="tab-content p-4 col-10" id="nav-tabContent">

							<!-- 會員資料 -->
							<div class="tab-pane fade show active" id="userInfo" role="tabpanel"
								aria-labelledby="list-userInfo-list">
								<div class="container mt-2">
									<div class="card w-sm-50 m-auto">
										<div class="card-header bg-dark text-light">
											會員資料
										</div>
										<div class="card-body w-75 m-auto">
											<div class="text-center mb-3">
												<c:choose>
													<c:when test="${LoginOK.img == 'data:image/jpeg;base64,'}">
														<img width="200px"
															src="${contextRoot}/apps/images/membericon.png">
													</c:when>
													<c:otherwise>
														<img width="200px" src="${LoginOK.img}">
													</c:otherwise>
												</c:choose>
											</div>
											<div class="form-group mb-3 row">
												<label for="Name" class="input-group-text bg-dark text-light">姓名
												</label>
												<input type="text" name="memberName" id="Name"
													value="${LoginOK.memberName}" readonly>
											</div>
											<div class="form-group mb-3 row">
												<c:choose>
													<c:when test="${LoginOK.sex == 'M'}">
														<label for="memberSex"
															class="input-group-text bg-dark text-light">性別</label>
														<input type="text" name="memberSex" id="memberSex" value="男"
															readonly>
													</c:when>
													<c:when test="${LoginOK.sex == 'F'}">
														<label for="memberSex"
															class="input-group-text bg-dark text-light">性別</label>
														<input type="text" name="memberSex" id="memberSex" value="女"
															readonly>
													</c:when>
													<c:otherwise>
														<label for="memberSex"
															class="input-group-text bg-dark text-light">性別</label>
														<input type="text" name="memberSex" id="memberSex" value="不便透露"
															readonly>
													</c:otherwise>
												</c:choose>
											</div>
											<div class="form-group mb-3 row">
												<label for="phone"
													class="input-group-text bg-dark text-light">手機</label>
												<input type="text" name="phone" id="phone" value="${LoginOK.phone}"
													readonly>
											</div>
											<div class="form-group mb-3 row">
												<label for="email"
													class="input-group-text bg-dark text-light">電子信箱</label>
												<input type="text" name="email" id="email" value="${LoginOK.email}"
													readonly>
											</div>
											<div class="form-group mb-3 row">
												<label for="birthday"
													class="input-group-text bg-dark text-light">生日</label>
												<input type="date" name="birthday" id="birthday"
													value='<fmt:formatDate pattern="yyyy-MM-dd" value="${LoginOK.birthday}"/>'
													readonly>
											</div>
											<div class="form-group mb-3 row">
												<label for="updatetime"
													class="input-group-text bg-dark text-light">上次更新時間</label>
												<input type="date" name="updatetime" id="updatetime"
													value='<fmt:formatDate pattern="yyyy-MM-dd" value="${LoginOK.updateDate}"/>'
													readonly>
											</div>
											<div class="form-group mb-3 row">
												<a type="button" class="btn btn-dark"
													href="${contextRoot}/member/editmember">編輯</a>
											</div>
											<div class="form-group mb-3 row">
												<a type="button" class="btn btn-dark"
													href="${contextRoot}/member/deletemember/${LoginOK.m_id}"
													onclick="return confirm('確定刪除?')">刪除</a>
											</div>
											<div class="form-group mb-3 row">
												<a type="button" class="btn btn-dark"
													href="${contextRoot}/updatepassword">修改密碼</a>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="tab-pane fade" id="reserveData" role="tabpanel"
								aria-labelledby="list-reserveData-list">
								<div class="option">
									<button class="undo-btn">尚未完成</button>
									<button class="done-btn">已完成</button>
								</div>

								<div class="container" id="order-box">
									<div class="main">
										<section class="left">
										</section>
										<section class="right">
										</section>
									</div>

								</div>




							</div>

						</div>
						<div class="list-group text-center col-2 " id="list-tab-1" role="tabpanel" height="100vh" >
							<a href="#" class="list-group-item list-group-item-dark ">功能列表</a>
							<hr>
							<a type="button" class=" list-group-item 
							list-group-item-dark list-group-item-action active " id="list-userInfo-list " data-bs-toggle="list"
								href="#userInfo" role="tab">會員資料</a>
							<a class="list-group-item list-group-item-dark list-group-item-action "
								id="list-reserveData-list" data-bs-toggle="list" href="#reserveData" role="tab">訂單
							</a>

							<hr>
							<c:choose>
								<c:when test="${LoginOK.admin}">
									<a class="list-group-item list-group-item-dark list-group-item-action "
										href="/bak/pro">後臺管理</a>
								</c:when>
							</c:choose>

						</div>
					</div>



					<script src="${contextRoot}/apps/member/memberRecord.js"></script>
					<script>

						// 視窗大小參數
						const md = '768';
						const lg = '992';
						const sm = '576';

						//初始化
						init();
						function init() {
							checklistbtn();
							checkContentList();
						}
						// 視窗監聽調整畫布控制按鈕顯示
						function checklistbtn() {
							var wWidth = $(window).width();
							if (wWidth >= md) {
								// console.log('hi');
								$('.listbtn').css('display', 'none');
							} else if (wWidth < md) {
								$('.listbtn').css('display', '');
							}
						}
						$(window).resize(checklistbtn);

						// 視窗監聽調整內容排版&顯示
						function checkContentList() {
							var wWidth = $(window).width();
							if (wWidth < md) {
								$('#list-tab-1').css('display', 'none')
								$('#nav-tabContent').addClass('container-fulid').removeClass('col-10')
							} else if (wWidth >= md) {
								$('#list-tab-1').css('display', '')
								$('#nav-tabContent').addClass('col-10').removeClass('container-fulid')
							}
						}
						$(window).resize(checkContentList);

						//畫布內容選項選擇後自動關閉
						$("#list-tab-2 a").click(function () { $('#offcanvasExample').offcanvas('hide'); });
					</script>

				</body>

				</html>