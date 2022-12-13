<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <c:set var="contextRoot" value="${pageContext.request.contextPath}/apps" />
        <!DOCTYPE html>

        <html>

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>首頁</title>
            <link rel="stylesheet" href="${contextRoot}/css/style.css">
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />

        </head>

        <body>
            <jsp:include page="layout/navbar.jsp"></jsp:include>


            <div class="bg">
                <img src="${contextRoot}/images/bar.jpg" alt="">
            </div>
            <div class="text-center introduce" id="my">
                <h1 class="ml1">
                    <span class="text-wrapper">
                        <span class="line line1"></span>
                        <span class="letters">ACHillBAR</span>
                        <span class="line line2" style="font-size: 0.8em;">Beer Relaxed</span>
                    </span>
                </h1>
            </div>

            <!-- 介紹 -->
            <div class=" bg-balck text-white text-center p-5" style="background-color :black">
                <section class="container-fluid">
                    <h1>關於ARChillBar</h1>
                    <p>近年來在台灣品酒這件事變得非常盛行，但受到疫情的影響，越來越多餐酒館跟酒吧陸陸續續的倒閉，隨著政府的政策轉彎，疫情逐漸趨緩，所以決定完成我兒時的夢想，打造一個有造型景觀的餐酒館，而風格與chill這句英文互相輝映，來這的客人就是以放鬆為基本，歡笑為輔助，忘掉一切的不開心。
                    </p>
                    <p>營業時間: Mon~ Sat 20:00~24:00</p>
                    <p>地址: 台南成功大學</p>
                    <p>聯絡資訊: 06-xxxxxxxx</p>
                </section>




            </div>
            <!-- card1 -->
            <div class="card bg-dark text-white" id="" style="overflow: clip">
                <img src="https://picsum.photos/800/400?random=1" class="card-img" alt="..."
                    style="object-fit: cover; width:100%;">
                <div class="card-img-overlay text-center p-0 ">
                    <div class="row h-100">
                        <div class="col-lg-6 h-100 d-flex align-items-center bg-black  bb1"
                            style="--bs-bg-opacity: .9; backdrop-filter: blur(5px)">
                            <div class="wrap w-100 p-4">
                                <h5 class="card-title" style="text-align:left">用餐須知</h5>
                                <p class="card-text" style="text-align:left">
                                    1.公休時間：每週週一、二（電話無人接聽） <br>
                                    2.營業時間：18:00-24:00（最後點餐時間:22:30)(每人低消200元) <br>
                                    3.目前小店採取完全預約制，不接現場。 <br>
                                    4.進入餐廳前應全程佩戴口罩、體溫量測及落實實聯制。 <br>
                                    5.用餐時可暫時脫下口罩，離開座位或用餐完畢後應立即佩戴口罩。 <br>
                                    6.內用用餐時應保持社交距離。 <br>
                                    7.餐點現點現做才會美味，需耐心等候請勿催餐。 <br>
                                    8.小店人力有限，不收取服務費，習慣被服侍的朋友請斟酌用餐。 <br>
                                    9.勿擅自換座位，移動店內擺設。（店內每樣皆為我們的財產，請一起維護空間品質） <br>
                                    10.如客人覺得服務品質滿意，請不要吝嗇您的誇讚，也謝謝您蒞臨本小店。 <br>
                                </p>
                                <p class="card-text">立即加入會員以享有優惠服務</p>
                                <a href="${pageContext.request.contextPath}/member/signup"
                                    class="btn btn-primary">加入會員</a>
                            </div>

                        </div>
                        <div class="col-6"></div>
                    </div>
                </div>
            </div>
            <!-- card2 -->
            <div class="card bg-dark text-white" id="" style="overflow: clip">
                <img src="https://picsum.photos/800/400?random=2" class="card-img" alt="..."
                    style="object-fit: cover; width:100%;">
                <div class="card-img-overlay text-center p-0 ">
                    <div class="row h-100">
                        <div class="col-6"></div>
                        <div class="col-lg-6 h-100 d-flex align-items-center bg-black bb1"
                            style="--bs-bg-opacity: .9; backdrop-filter: blur(5px)">
                            <div class="wrap w-100">

                                <h5 class="card-title">清新悠閒的環境</h5>
                                <p class="card-text">一杯酒，悠閒了時間；一口飯，道盡了無奈；深夜，獨坐，翻著沒翻過的書，恍惚</p>
                                <a href="#" class="btn btn-primary">想放鬆就來一杯，乾杯</a>
                            </div>

                        </div>

                    </div>
                </div>
            </div>
            <!-- card3 -->
            <div class="card bg-dark text-white" id="" style="overflow: clip">
                <img src="https://picsum.photos/800/400?random=3" class="card-img" alt="..."
                    style="object-fit: cover; width:100%;">
                <div class="card-img-overlay text-center p-0 ">
                    <div class="row h-100">
                        <div class="col-lg-6 h-100 d-flex align-items-center bg-black  bb1"
                            style="--bs-bg-opacity: .9; backdrop-filter: blur(5px)">
                            <div class="wrap w-100">

                                <h5 class="card-title">主打特色調酒及風味套餐</h5>
                                <p class="card-text">特別的調酒，打造出迷人的風味，能依照個人需求搭配出屬於自己的酒單</p>
                                <p class="card-text">妳相信星座嗎？</p>
                                <p class="card-text">不相信沒關係，喝下去就懂了</p>
                                <a href="#" class="btn btn-primary">來一杯只屬於妳的調酒</a>
                            </div>

                        </div>
                        <div class="col-6"></div>
                    </div>
                </div>
            </div>


            <iframe
                src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d2523.0437590859274!2d120.21633905723405!3d22.99891485386979!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x346e76f2ae1e81a1%3A0xefb13720d722d1c0!2z5oiQ5Yqf5aSn5a245ZSv6L6y5aSn5qiT!5e0!3m2!1szh-TW!2stw!4v1663579385923!5m2!1szh-TW!2stw"
                width="100%" height="450" style="border:0;" allowfullscreen="" loading="lazy"
                referrerpolicy="no-referrer-when-downgrade"></iframe>



            <!-- 底部inform -->
            <footer class=" p-4 border-top footer">
                <div class="container d-flex align-items-center justify-content-between">
                    <p class="mb-0 text-muted">
                        &copy; 2022 AChillBar All rights reserved.
                    </p>
                    <ul class="nav">
                        <li class="nav-item"><a href="" class="nav-link text-muted">關於本站</a></li>
                        <li class="nav-item"><a href="" class="nav-link text-muted"></a></li>
                    </ul>
                </div>
            </footer>

            <script src="https://cdnjs.cloudflare.com/ajax/libs/animejs/2.0.2/anime.min.js"></script>
            <script src="${contextRoot}/js/javascript.js"></script>

        </body>

        </html>