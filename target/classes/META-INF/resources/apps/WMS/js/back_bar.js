backbar();
function backbar() {

    $('.container-left').append(`


    <nav id="siderbar">
              
                <button type="button" id="collapse" class="btn btn-info collapse-btn">
                    <i class="fas fa-align-left"></i>
                </button>

            
                <ul class="list-unsrtyled">
                    <div class="backed">
                        <li>
                            <div class="titname t2">
                                後臺管理系統
                            </div>
                            <hr>
                        </li>

                        <li>
                            <div class="member mb">
                                <a class="mem t2" href="/bak/member" target="_self">會員資料</a>
                            </div>
                            <hr>

                        </li>
                        <li>
                            <div class="od mb">
                                <a class="odr t2" href="/bak/ordertest" target="_self">訂單管理</a>
                            </div>
                            <hr>
                        </li>
                        <li>
                            <div class="bk mb">
                                <a class="book t2" href="/bak/bookingbacked" target="_self">訂位查詢</a>
                            </div>
                            <hr>
                        </li>
                        <li>
                            <div class="pd mb">
                                <a class="pdu t2" href="/bak/pro" target="_self">產品菜單</a>
                            </div>
                            <hr>
                        </li>
                        <li>
                            <div class="cm mb">
                                <a class="comm t2" href="/bak/comm" target="_self">留言評論</a>
                            </div>
                            <hr>
                        </li>
                        <li>
                            <div class="bk mb">
                                <a class="comm t2" href="/bak/manger">現場管理</a>
                            </div>
                            <hr>
                        </li>

                </ul>

                <!-- 縮起 -->

            </nav>
`)
}

$('.t2').css('color', 'rgb(238, 234, 223)')





$('.book,.mem,.comm,.pdu,.odr,.titname').css('color', 'rgb(238, 234, 223)')
var oldColor = "";

$(document).ready(function () {
    $("#collapse").on("click", function () {
        $("#siderbar").toggleClass("active");
        // 箭頭樣式
        // <i class="fa-duotone fa-angles-right"></i>
        // <i class="fa-regular fa-angles-left"></i>
        $(".fa-align-left").toggleClass("fa-angles-left");
    })
});