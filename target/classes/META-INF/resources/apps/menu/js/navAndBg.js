let thisMember = null;


// NavBar
$("body").prepend(`
<nav class="navbar navbar-expand-lg bg-transparent navbar-dark p-3 hbg" id="navbar">
    <div class="container-fluid">
        <a href="/" class="navbar-brand">ACHillBAR-LOGO</a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a href="/" class="nav-link">首頁</a>
                </li>
                <li class="nav-item">
                    <a href="/menu" class="nav-link">菜單</a>
                </li>
                <li class="nav-item">
                    <a href="https://www.104.com.tw/" class="nav-link">人才招募</a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link">聯絡我們</a>
                </li>
                <li class="nav-item">
                    <a style="cursor: pointer;"                      id="book-link" class="nav-link" >訂位</a>
                </li>
                      
                <li class="nav-item">
                    <a href="https://www.instagram.com/" class="nav-link"><img
                            class="bg-white rounded border border-2 border-white" src="/apps/menu/images/instagram.png" alt=""
                            width="25"></a>
                </li>
                <li class="nav-item">
                    <a href="https://zh-tw.facebook.com/xin.yi.336333" class="nav-link"><img
                            class="bg-white rounded border border-2 border-white" src="/apps/menu/images/facebook.png" alt=""
                            width="25"></a>
                </li>   
                
                <li class="nav-item" style="">
                    <div class="logBox rounded-pill">
                        <a id="member-info" class="nav-link">
                                <img alt="會員圖片" id="nav-m-img" src="/apps/images/membericonW.png">
                        </a>
                        <div class="item log">
                            <a class="nav-link" data-bs-toggle="modal" data-bs-target="#login-modal">登入/註冊</a>
                        </div>
                    </div>
                </li>
                
            </ul>
        </div>
    </div>
    </nav>

    <div class="bg">
    <img src="/apps/images/bar.jpg" alt="">
</div>
`);

// 會員登入彈出視窗
$("body").append(`

<div class="modal fade" id="login-modal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
    aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-dark">
                <h5 class="modal-title text-white" id="staticBackdropLabel">登入會員</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body p-0">
                <div class="container mt-2 m-auto ">


                    <form id="login">
                        <div class="card-body w-75 m-auto">
                            <div class="row mb-3 text-center">
                                <span class="error"></span>
                            </div>
                            <div class="row mb-3">
                                <label for="" class="input-group-text bg-dark text-light">信箱</label>
                                <input autocomplete ="username" type="email" name="email" id="login-email">                                   
                            </div>

                            <div class="row mb-3">
                                <label for="" class="input-group-text bg-dark text-light">密碼</label>
                                <input autocomplete="current-password" type="password" name="password" id="login-password">
                            </div>
                            <div class="row mb-3">
                                <div class="col-6">
                                    <input type="checkbox" name="rememberMe" id="login-rememberMe">
                                    
                                    <span>記住密碼</span>
                                </div>     
                                <div class="col-6 text-end">
                                    <a href="/forgetpassword">忘記密碼?</a>
                                </div> 
                            </div>

                            <div class="row mb-3">
                                <button id="login-submit" type="button" class="btn btn-dark">登入</button>
                            </div>

                            <div class="row ">
                                <div class="text-center">還沒有會員嗎?</div>
                                <a href="/member/signup" class="text-center">立即註冊</a>
                            </div>
                            
                        </div>
                    </form>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>

                </div>
            </div>
        </div>
    </div>
</div>

`);

// JS
$("body").append(`
<script src="/apps/js/bootstrap.bundle.min.js"></script>

`);

let loginModal = new bootstrap.Modal(document.getElementById("login-modal"));


initNav();
function initNav() {


    $("#book-link").on("click", goLogin);
    function goLogin() {
        alert("請先登入會員")
        loginModal.show();
    }


    $.ajax({
        url: '/member/isLongin',
        contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
        method: 'get',
        dataType: 'json', // 回傳的資料型別
        async: false,
        success: function (result) {
            console.log(result);
            if (result != null) {
                // alert("從session登入");
                thisMember = result;
                $("#book-link").attr("href", "/booking");
                $("#book-link").off("click", goLogin);
                $("#nav-m-img").attr("src", result.img);
                if (result.img == "data:image/jpeg;base64,") {
                    $("#nav-m-img").attr("src", "/apps/images/membericonW.png");
                }
                $("#member-info").attr("href", "/member/viewmember");
                $(".item.log").empty().append(`
                    <a class="nav-link" href="/logout2?uri=${window.location.pathname}">登出</a>
                `);
            } else {
                
                MemberCookie();
            }


        },
        error: function (err) {
            $("#nav-m-img").parent().remove();
            MemberCookie();
        },

    })
    
    $("#login-submit").on("click",function () {
        let postJson = new Object();
        let form = $("#login");
        postJson.email = form.find(`input[name="email"]`).val();
        postJson.password = form.find(`input[name="password"]`).val();
        let checkbox = document.getElementById("login-rememberMe");
        postJson.rememberMe = checkbox.checked;
        let raw = JSON.stringify(postJson);
        console.log(raw);
        $.ajax({
            url: '/login2',
            contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
            method: 'post',
            data: raw,
            dataType: 'json', // 回傳的資料型別
            success: function (result) {
                member = result.Member
                if (member != null) {
                    $('.error').text("你好!" + member.memberName + "歡迎登入!" + " 1秒後重新載入").css('color', 'green');
                    setTimeout(function () {
                        location.reload();
                    }, 1000)
                } else {
                    $('.error').text(result.error).css('color', 'red');
                }
            },
            error: function (err) {
                console.log("錯誤");
                console.log(err);
            }
        })



    })


    // 從COOKIE獲得登入資訊
    function MemberCookie() {

        $.ajax({
            url: '/login2',
            contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
            method: 'get',
            dataType: 'json', // 回傳的資料型別
            success: function (result) {
                // alert("從COOKIE登入");
                // console.log(result);
                if (member = result.Member != null) {
                    // alert("你好!" + member.memberName + "歡迎登入!");
                    location.reload();
                } else {
                    console.log(result);
                }

            },
            error: function (err) {
                console.log(err);
            }
        })
    }





}


