

// ------------------側選單-------------------

let scrollTop =document.documentElement.scrollTop 
+ document.body.scrollTop;
// 側邊選單點擊選項事件
$(".ch-87").on("click", function () {
    $(".ch-87").css("color", "");
    $(this).css("color", "white");
    if ($(this).hasClass("cus")) return false;
    
    if($(`#${$(this).attr("id")}_1`).length==0){


        if($(`#${$(this).attr("menu")}menuBox`).length==1){
            let top=($(`#${$(this).attr("menu")}menuBox`)).offset().top;
            $("html,body").animate({
                scrollTop: `${top-80}`,
            }, 200);
            $(window).width() < lg ? hide() : "";
            return false;
        }else{
            alert("目前沒有此類商品");

        }
        return false;
    } 
    let top=$(`#${$(this).attr("id")}_1`).offset().top;

    $("html,body").animate({
        scrollTop: `${top-80}`,
    }, 200);
   

    $(window).width() < lg ? hide() : "";
});
$("#cus-box").on("click", function () {
    return false;
})


// 點擊MENU按鈕開關側選單
$(".sideSwitch").on("click", function () {
    if ($("#sideBar").css("left") == `0px`) {
        hide();
    } else {
        console.log("sideSwitch");
        openSidebar();
    }

})

//function 隱藏側邊攔
function hide() {
    if ($(window).width() < lg) {
        if ($(window).width() < md) {

            $("#sideBar").css("left", 0 - $(window).width() * 0.87);
        } else {
            $("#sideBar").css("left", 0 - 250);
        }
        $(".menu-card").on("click", showBigCard);
    }

}

// function 顯示側邊攔
function openSidebar() {
    // console.log("open")
    const f21 = function () {
        // console.log("f21的鍋?");
        $("#sideBar").on("click", function () {
            return false;
        });
        $(".menu-card").off("click", showBigCard);
        hide();
    }

    $("#sideBar").css("left", "0");
    // console.log($(window).width());
    // console.log(lg);
    // console.log($("#sideBar").css("left"));
    // 新增監聽_點擊外部會隱藏sidebar
    if ($(window).width() <= lg) {
        $("#menu-main").one("click", f21);

    }

}




// ------------------大卡-------------------

const stopFun = function (event) {
    event.stopPropagation();
    // console.log(event);
    return false;
};

//function 取消指定標籤的滾輪功能



// $(".bigCardOutside").on("mousewheel",stopFun)


// $('.bigCard').find("*").on("mousewheel", function (event) {
//     event.stopPropagation();
//     event.preventDefault()
// });

$("#bigCard").on("wheel", function (e) {
    let down = e.originalEvent.deltaY;
    if (down > 0) {

    }

})


//function 彈出菜單細項視窗
const showBigCard = function () {
    // console.log(event);
    $("body").addClass("closeScroll");
    $(".bigCardOutside").removeClass("d-none");
    $('#bigCard').on("click", function (event) {
        return false;
    })


}


// 點擊大卡外側關閉彈出視窗
$(".bigCardOutside").on("click",closeBigCard);
$("#bigCard").find(".bi-arrow-left").on("click",closeBigCard);

function closeBigCard(event){
    
    $("body").removeClass("closeScroll");
    $(".bigCardOutside").addClass("d-none");
    $("#bigCard").on("click", function () {
        return false;
    })
    event.stopPropagation();
    $(".bigCardOutside").remove('mousewheel', stopFun);
}

// 點擊小卡出現大卡
// $(".menu-card").on("click", showBigCard);





//--------------- 小卡----------------
// function 手機滑動_X軸
const mobileSwipeX = function (obj) {
    let startx;
    let gapX;
    let target;
    obj.on("touchstart", start);
    function start(e) {
        target = $(this)
        gapX = e.touches[0].pageX;
        // 需要移動的東西
        startx = target.scrollLeft();  // scroll的初始位置
        target.on('touchmove', move)
    }
    function move(e) {
        e.preventDefault();
        let touch = e.touches[0];
        let moveX = touch.pageX;
        let left = moveX - gapX;
        target.scrollLeft(startx - left);
        return false;//阻止預設事件或冒泡  
    }


}

//function X軸滑鼠拖動
function drag(obj) {
    let gapX;
    let startx;
    let target = $(this);
    obj.on("mousedown", start);

    function start(event) {
        if (event.button == 0) {//判斷是否點選滑鼠左鍵  
            target = $(this)
            gapX = event.clientX;
            // 需要移動的東西
            startx = target.scrollLeft();  // scroll的初始位置

            //movemove事件必須繫結到$(document)上，滑鼠移動是在整個螢幕上的  
            $(window).on("mousemove", move);
            //此處的$(document)可以改為obj  
            $(window).on("mouseup", stop);
            event.stopPropagation();
            $(".menu-card").off("click", showBigCard);
            return false;//阻止預設事件或冒泡  
        }
        return false;//阻止預設事件或冒泡  
    }
    function move(event) {
        let left = event.clientX - gapX; // 滑鼠移動的相對距離

        // 需要移動的東西
        target.scrollLeft(startx - left);
        event.stopPropagation();
        return false;//阻止預設事件或冒泡  
    }
    function stop(event) {
        //解繫結，這一步很必要，前面有解釋  
        event.stopPropagation();
        console.log(event);
        $(window).off("mousemove", move);
        $(window).off("mouseup", stop);

        setTimeout(function () {
            $(".menu-card").on("click", showBigCard);
        }, 300)
        return false;
    }

}


$(".tagBox").on("click", function (event) {
    event.stopPropagation();
    return false;
})



//傳入的必須是jQuery物件，否則不能呼叫jQuery的自定義函式  
drag($(".tagBox"));




mobileSwipeX($(".tagBox"));



// ----------------------響應斷點------------------------------

const xl = '1200';
const lg = '975';
const md = '768';
const sm = '576';

// lg變化
const lgAct = function () {
    if ($(window).width() < lg) {
        $(".sideSwitch").removeClass("d-none");
        $(window).width() < md ? $("#sideBar").width('87%') : $("#sideBar").width('250px');
        hide();
    } else {

        openSidebar();
        $("#sideBar").width('250px')
        $(".sideSwitch").addClass("d-none");
        // $("#menu-main").off("click", f21);
    }
}
// 預載入
lgAct();
// 監聽視窗變化
$(window).resize(function () {
    lgAct();

})

// ================================================


// ============================================
// 會員

// $.ajax({
//     url: '/member/isLongin',
//     contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
//     method: 'get',
//     dataType: 'json', // 回傳的資料型別
//     success: function (result) {
//         console.log(result);
//         sss(result);

//     },
//     error: function (err) {

//     }
// })

// function sss(result) {
//     $("body").append(`<button class="memberImgBox d-flex" type="button" data-bs-target="" style="z-index: 9000;height: 50px; border: red solid 1px;position: fixed;left: 0;bottom: 2rem;">
//     <img class="m-auto w-100 h-100" style="object-fit: cover;" src="${result.img}" alt="${result.memberName}" >
// </button>`)
// }
