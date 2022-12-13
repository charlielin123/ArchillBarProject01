//資料庫中ProductType與前端中文名轉換表

let ProTypeTran = {
    "main": "主餐",
    "appetizer": "前菜",
    "soup": "湯品",
    "dessert": "點心",
    "fried": "炸物",
    "classicAlcoho": "經典調酒",
    "originalAlcoho": "原創調酒",
    "beerAlcoho": "啤酒",
    "softDrink": "軟性飲料",
    "custMenu": "By TAG!"
}

let tableNumTran = {
    1: "A1",
    2: "A2",
    3: "A3",
    4: "A4",
    5: "A5",
    6: "A6",
    7: "A7",
    8: "A8",
    9: "A9",
    10: "A10",
    11: "B1",
    12: "B2",
    13: "C1",
    14: "C2",
    15: "C3",
    16: "C4",
    17: "C5",
    18: "C6",


}


let sectionLR = 0;
let menuMaker = function (file, menuName) {

    let cardNub = file.length;
    let pagenum = 0;
    let pageId;
    let sectionNum = 0;
    let sectionID;
    test1();
    function test1() {
        for (let i = 0; i < cardNub; i++) {

            if (i % 8 == 0) {
                pagenum++;
                pageId = menuName + "_" + pagenum;
                // console.log("新增PAGE" + pageId);
                makePage(menuName);
            }
            if (i % 4 == 0) {
                sectionNum++;
                sectionID = menuName + "_s_" + sectionNum;
                if (sectionLR == 0) {
                    makeSection(pageId, sectionID, i);
                    sectionLR++;
                } else {
                    // console.log("新增sectionID" + sectionID);

                    makeSection2(pageId, sectionID, i);
                    sectionLR = 0;
                }
            }

            menuCard(sectionID, i);

        }





    }





    function makePage(pageName) {

        $(`#${menuName}menuBox`).append(`
        <div class="container-fluid menuPage hbg py-2 mx-auto" style="" id="${pageId}">
            <div class="row">
                <h1 class="text-white border-bottom border-3 w-75">${ProTypeTran[pageName]}</h1>
    
            </div>
        </div>`);

    }


    function makeSection(pageId, sectionID, num) {
        $(`#${pageId}`).append(`
    
        <!-- 第一頁 -->
            <div class="row px-0 py=lg-3 my-2 menu-section" id="${sectionID}">
                <!-- 側邊大圖 -->
                <div class="col-6 d-none d-lg-flex align-items-center h-100 justify-content-center">
                    <div class="imgBox overflow-hidden w-100 h100 d-flex align-items-center justify-content-center">
                         <img class="my-auto bigImg " style=" ;" src="${file[num].image}" alt="">
                    </div>
                </div>
                <!-- 卡片區 -->
                <div class="col-lg-6 menu-cardBox d-flex justify-content-evenly flex-column">
                
                </div> 
            </div>
    
    
        </div>`);

    }

    function makeSection2(pageId, sectionID, num) {
        $(`#${pageId}`).append(`
    
        <!-- 第一頁 -->
            <div class="row px-0 py-lg-3 my-2 menu-section" id="${sectionID}">

                <!-- 卡片區 -->
                <div class="col-lg-6 menu-cardBox d-flex justify-content-evenly flex-column">
                
                </div> 
                <!-- 側邊大圖 -->
                <div class="col-6 d-none d-lg-flex align-items-center h-100 justify-content-center">
                    <div class="imgBox overflow-hidden w-100 h100 d-flex align-items-center justify-content-center">
                         <img class="my-auto bigImg " style=" ;" src="${file[num].image}" alt="">
                    </div>
                </div>
            </div>
    
    
        </div>`);

    }


    //做一張卡片
    function menuCard(sectionID, num) {
        let p_id = file[num].p_id;
        let score = file[num].score;
        score == null ? score = "?.?" : "";
        $(`#${sectionID}`).find(`.menu-cardBox`).append(`                                    
    <div
  class="container-fluid menu-card bg-opacity-10 overflow-hidden my-1 gx-0 p-2 row-lg " pId="${p_id}">
  <div class="row w-100 h-100">
      <!-- 放小圖 -->

      <div
          class="col-4 d-lg-none h-100 justify-content-center d-flex overflow-hidden">
          <img class="cardImg" style="" src="${file[num].image}" alt="">
      </div>
      <div class="col-8 col-lg-12">

          <div class="row border-bottom border-white position-relative ">
              <!-- 卡片標題 -->
              <div class="col-sm-9 align-items-end">
                  <div class="h4 m-0 name" style="white-space:nowrap; overflow: hidden;">${file[num].p_name}</div>
                  <div class="ms-3 text-white-50 m-0  card-component align-bottom" style="white-space:nowrap; overflow: hidden;" >成分：${file[num].component}</div>
              </div>

              <!-- 星星 -->
              <div class="col-sm-3 d-flex align-items-end justify-content-end">
                  <span class="h6 p-0 m-0">${score}x</span>
                  <img class="star" src="apps/menu/images/menuImg/star.png" alt="">
              </div>


          </div>
          <!-- <div class="row d-none d-lg-flex">
              <div class="col-lg-1"></div>
              <div class="col-lg-8">

              </div>

          </div> -->

          <div class="row d-flex align-items-end my-1 flex-row-reverse con-con">


              <div class="col-3 d-flex justify-content-end ">
                  <span class="card-price align-items-center fs-7 fw-bold">
                      NT$<span class="price">${file[num].price}</span>
                  </span>

              </div>
              <div class="col-9  align-items-end my-auto tagBox-con"
                  style="height:2em ;overflow: hidden;">
                  <div class="tagBox" pId="${p_id}">
                  </div>

              </div>
          </div>

      </div>

  </div>


</div>`
        );
        // console.log(file[num].tags);
        for (let i = 0; i < file[num].tags.length; i++) {
            $(`.tagBox[pId="${p_id}"]`).append(
                `<div class="tag rounded-pill">#${file[num].tags[i].tag}</div>`
            )

        }




    }



}




// 獲得菜單資料
let menuJson;
//

$.ajax({
    url: '/menu/all',
    contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
    method: 'get',
    dataType: 'json', // 回傳的資料型別
    success: function (result) {
        console.log("超慢");
        menuJson = result;
        // console.log(menuJson);
        app1(result);


    },
    error: function (err) {
        console.log(err);
    }
})

let hideLoad = function () {
    $("body").removeClass("closeScroll");
    $("#loadingPage").hide();
    $(".bigImg").addClass("anima2")
    $(".menu-card").addClass("anima1")

}
let showLoad = function () {

    $("#loadingPage").show();
    $("body").addClass("closeScroll");
    $(".bigImg").removeClass("anima2")
    $(".menu-card").removeClass("anima1")
}

function app1(json) {
    for (key in ProTypeTran) {
        $(`.menuBox`).append(`
        <div id="${key}menuBox"></div>
        `);

    }
    for (key in json) {
        if (json[key].length != 0) {
            menuMaker(json[key], json[key][0].type);
        }
    }

    hideLoad();
    // getAllTag();
    startXXX();
    getBooking();
}








let bigCard = $("#bigCard");
function startXXX() {

    $(".menu-card").on('mouseenter', function () {
        let img = $(this).find(".cardImg").attr('src');
        $(this).parent().parent().find(".bigImg").attr('src', img);


    });

    $(".menu-card").on("touchstart", function () {
        let img = $(this).find(".cardImg").attr('src');
        $(this).parent().parent().find(".bigImg").attr('src', img);

    });

    $(".menu-card").on("click", function () {
        let p_id = $(this).attr("pId");
        // console.log("id=" + p_id);
        $.ajax({
            url: 'menu/' + p_id,
            contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
            method: 'get',
            dataType: 'json', // 回傳的資料型別
            success: function (result) {

                let cardJson = result;


                // console.log(cardJson);
                bigCard.attr("pId", cardJson.p_id);
                bigCard.find(".bigCardImg").attr("src", cardJson.image);
                bigCard.find(".name").text(cardJson.p_name);
                if (cardJson.score == null) {
                    bigCard.find(".score").text("..")
                } else {

                    bigCard.find(".score").text(cardJson.score);
                }
                bigCard.find(".price").text(cardJson.price);
                bigCard.find(".cardText").text(cardJson.info);
                bigCard.find(".component").text(cardJson.component);


                bigCard.find("comment-box").empty();
                getComm(cardJson.p_id);

            },
            error: function (err) {
                console.log(err);
            }
        })




    })

    $(".menu-card").on("click", showBigCard);
    drag($(".tagBox"));
    mobileSwipeX($(".tagBox"));





    $(".tagBox").on("click", function (event) {
        // event.stopPropagation();
        return false;
    })



}
let omid = "";
let comid = "";
let opid = "";
function getComm(p_id) {
    bigCard.find(".comment-box").empty();
    let thisMid = thisMember.m_id;
    $.ajax({
        url: '/comment/p/' + p_id,
        contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
        method: 'get',
        dataType: 'json', // 回傳的資料型別
        success: function (result) {
            for (let key in result) {
                let appStr = `<tr>
                <th scope="row">${result[key].m_id}</th>
                <td>${result[key].score}x<img class="star" src="apps/menu/images/menuImg/star.png" alt=""></td>
                <td>${result[key].createDate}</td>
                <td>${result[key].comment != "" ? result[key].comment : "無評價"}</td>
                <td class="text-center">`;

                if (thisMid == result[key].m_id) {

                    appStr += ` <button class="btn btn-secondary goEditCom" mId="${result[key].m_id}" cId="${result[key].com_id}" data-bs-toggle="modal" onclick="goEditComClick (${result[key].com_id},${result[key].m_id})"  data-bs-target="#comment">修改評論</button>
                    <button class="btn btn-danger goDelCom" cId="${result[key].com_id}">刪除</button></tr>`
                }
                appStr += `</td></tr>`


                bigCard.find(".comment-box").append(appStr
                );

            }
            //撈評論改按鍵屬性&監聽
            editCommentBtn();
            deleteCombtn();
        },
        error: function (err) {
            console.log(err);
        }
    })


}





// --------------購物車-----------------

let shopCartModal = new bootstrap.Offcanvas(document.getElementById("shopCart"));

// 新增產品至購物車
const insterShopCartItem = function (id, productName, price, quantity, sub) {


    const shopCartHtml = `
    <div class="shopCart-card container" scId="${id}">                          
         <div class="shopCartItem" ">
            <div class="row">
            <div class="col-6 d-flex " style="align-items: center;">
                <span class="m-0 name h4">${productName}</span>
                </div>
                <div class="col-6 d-flex flex-column justify-content-start"
                    style="align-items: end;">
                    <p class="d-block m-0" style="color:gold ;font-size: 1.7rem;">
                        NT$ <span class="price">${price}</span>
                    </p>
                </div>

            </div>
            <div class="row">
                <div class="col-12 d-flex" style="justify-content: end;">
                    <div class="d-flex bg-dark" style="border-radius:7.5rem ;">

                        <button class=" btn-reduce"
                            style="width:1.5rem;height: 1.5rem;border-radius: 50%;" scId="${id}">
                            <div class="m-0 h-100 w-100 align-middle"
                                style="text-align:center ;">
                                <span>-</span>
                            </div>
                        </button>
                        <div class="quantity" style="height:1.4rem; width:3rem ;">
                            <p class="quantity-num" style="text-align: center;">${quantity}</p>
                        </div>
                        <button class="p-auto btn-plus"
                            style="width:1.5rem;height: 1.5rem; border-radius: 50%;" scId="${id}">＋</button>

                    </div>

                </div>
            </div>

            <div class="row">
                <div class="col-3 delButton" scId="${id}">刪除</div>
                <div class="col-9 d-flex" style="align-items:center ;justify-content: end;">

                    <p class="m-0 subtotal">小計 NT$ <span>${sub}</span> </p>
                    </p>
                </div>
            </div>
        </div>
    </div>
`;
    $("#shopCartBox").append(shopCartHtml);
    btnPuls($(`.shopCart-card[scId=${id}]`));
    btnReduce($(`.shopCart-card[scId=${id}]`));
    delCartItem($(`.shopCart-card[scId=${id}]`));
    refreshTatol();
    $("body").removeClass("closeScroll");
    $(".bigCardOutside").addClass("d-none");



}

// 送出訂單
$("#submitOrder-btn").on("click", function () {
    if ($("#bookingDate").val() == "") {
        alert("請先訂位");
        location.href = "booking";
        return false;
    }
    let b_id = $("#bookingDate").val();


    $.ajax({
        url: '/order/submit/' + b_id,
        contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
        method: 'get',
        dataType: 'json', // 回傳的資料型別
        success: function (result) {
            // console.log(result);
            location.reload();


        },
        error: function (err) {
            console.log("錯誤");
            console.log(err);
        }
    })
}




)







// 新增購物車圖示
function insertShopCartIcon() {
    $("body").append(`    <button type="button" data-bs-target="#shopCart" id="shopCartBtn">
    <img class="m-auto" src="apps/menu/images/menuImg/cart-2.png" alt="購物車">
</button>`);

    $("#shopCartBtn").on("click", function () {

        refreshCart();

    });

}




// 新增至購物車
$(".addShopCart").on("click", function () {
    let p_id = $("#bigCard").attr("pId");
    insertToShopCart(p_id);


})


const btnPuls = function (obj) {
    obj.find(".btn-plus").on("click", function () {
        // alert(obj);
        let scId = $(this).attr("scId");
        // console.log(obj.find(".quantity-num"))
        // console.log(obj.find(".subtotal"))


        $.ajax({
            url: '/shopCart/plusOne/' + scId,
            contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
            method: 'get',
            dataType: 'json', // 回傳的資料型別
            success: function (result) {

                obj.find(".quantity-num").text(result.quantity);
                obj.find(".subtotal span").text(result.subTotal);
                console.log(result.subTotal);
                refreshTatol();


            },
            error: function (err) {

                console.log(err);
            }
        })


    })




}

const btnReduce = function (obj) {

    obj.find(".btn-reduce").on("click", function () {
        let scId = $(this).attr("scId");
        $.ajax({
            url: '/shopCart/reduceOne/' + scId,
            contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
            method: 'get',
            dataType: 'json', // 回傳的資料型別
            success: function (result) {

                obj.find(".quantity-num").text(result.quantity);
                obj.find(".subtotal span").text(result.subTotal);
                refreshTatol();


            },
            error: function (err) {
                console.log("錯誤tt2");
                console.log(err);
            }
        })


    })




}

const delCartItem = function (obj) {

    obj.find(".delButton").on("click", function () {
        let scId = $(this).attr("scId");
        $.ajax({
            url: '/shopCart/delete/' + scId,
            contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
            method: 'get',
            dataType: 'json', // 回傳的資料型別
            success: function (result) {
                if (result == true) {
                    refreshCart();
                }


            },
            error: function (err) {
                console.log("錯誤tt2");
                console.log(err);
            }
        })


    })
}



function refreshTatol() {
    let subtotals = $("#shopCart").find(".subtotal span");
    let total = 0;
    for (let i = 0; i < subtotals.length; i++) {
        total += parseInt(subtotals.eq(i).text());

    }



    $("#cart-total").text(total);
}

function insertToShopCart(p_id, scId) {
    if (thisMember == null) {
        alert("請先登入會員")
        loginModal.show();

        return false;
    }

    let shopCart = new Object();
    shopCart.p_id = p_id;
    if (scId != null) {
        shopCart.id = scId;
    }
    let raw = JSON.stringify(shopCart);
    $.ajax({
        url: '/shopCart',
        contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
        method: 'post',
        data: raw,
        dataType: 'json', // 回傳的資料型別
        success: function (result) {
            console.log(result);
            refreshCart();

        },
        error: function (err) {
            console.log("錯誤");
            console.log(err);
        }
    })




}

function refreshCart() {
    $("#shopCartBox").empty();
    shopCartModal.show();
    $.ajax({
        url: '/shopCart',
        contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
        method: 'get',
        dataType: 'json', // 回傳的資料型別
        success: function (result) {


            result.forEach(element => {
                insterShopCartItem(element.id, element.p_name, element.unit, element.quantity, element.subTotal);
            });

        },
        error: function (err) {

            console.log(err);
        }
    })
}

// 載入訂位日期
function getBooking() {

    $.ajax({
        url: '/booking/bookdate',
        contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
        method: 'get',
        dataType: 'json', // 回傳的資料型別
        success: function (result) {
            console.log(result);
            if (result.length == 0) {
                $("#bookingDate option").eq(0).text("請先訂位");
                $("#submitOrder-btn").text("立即訂位").css("color", "red");
            }
            for (let i = 0; i < result.length; i++) {
                $("#bookingDate").append(`<option value="${result[i].b_id}">${result[i].bookDate} - ${tableNumTran[result[i].tableNum]}桌</option>`);
            }
        },
        error: function (err) {

        }
    })



}

// -------------/購物車/----------------

















// --------------自訂菜單-----------------
getAllTagIntoSidebar();
function getAllTagIntoSidebar() {
    $.ajax({
        url: '/menu/tag/all',
        contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
        method: 'get',
        dataType: 'json', // 回傳的資料型別
        success: function (result) {
            for (const index in result) {
                $("#allTags").append(`
                <button class="tag rounded-pill" style="font-size:1rem;">#${result[index].tag}</button>
                `);
                // console.log(result[index].tag);
            }
            addTag();


        },
        error: function (err) {
            console.log(err);
        }
    })



}


function addTag() {

    const h2022 = function (name, obj) {
        let tagName = name;
        $("#serchTag-box").append(`<button class="tagName tag rounded-pill" style="font-size:1rem;"name="${tagName}"><span>${tagName}</span></button>`);

        $(`#serchTag-box .tagName[name="${tagName}"]`).on("click", function () {
            let tagname = $(this).text();
            aa();
            $("#allTags").prepend(`
                <button class="tag rounded-pill" style="font-size:1rem;" name="${tagname}">${tagname}</button>
                `);

            $(`#allTags .tag[name="${tagname}"]`).on("click", function () {
                h2022($(this).text(), $(this));
            });

            $(this).remove();
            getMenuByTag();
        })
        obj.remove();
        getMenuByTag();
    }



    $("#allTags .tag").on("click", function () {
        h2022($(this).text(), $(this));
        aa();

    })

}

// 使用TAG搜尋菜單
function getMenuByTag() {
    showLoad();
    let xxx = $("#serchTag-box .tagName span");
    let str = "";
    let f22
    for (let i = 0; i < xxx.length; i++) {
        str += xxx.eq(i).text();
    }
    if (str != "") {
        f22 = str.split("#");
        f22.shift();

    } else {
        $(`.menuBox`).empty();
        showLoad();
        app1(menuJson);
        setTimeout(hideLoad, 2000);
        return null;

    }
    let data = JSON.stringify(f22);


    $.ajax({
        url: 'menu/tags',
        contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
        method: 'post',
        data: data,
        dataType: 'json', // 回傳的資料型別
        success: function (result) {
            $(`.menuBox`).empty();
            console.log(result);
            $(".menuBox").append(`<div id="custMenumenuBox"></div>`)
            menuMaker(result, "custMenu");
            hideLoad();
            startXXX();


        },
        error: function (err) {
            console.log(err);
        }
    })



    aa();


}

$("#side3 button.up").on("click", function () {
    let start = $("#side3 .tag-box").scrollTop();
    $("#side3 .tag-box").scrollTop(start - 140);
    console.log($("#side3 .tag-box").scrollTop());
    aa();

})
$("#side3 button.down").on("click", function () {
    let start = $("#side3 .tag-box").scrollTop();
    $("#side3 .tag-box").scrollTop(start + 140);
    aa();

})

function aa() {
    let h1 = $("#side3 .tag-box").height();
    let h2 =  $("#allTags").height();
    let now = $("#side3 .tag-box").scrollTop();
    let x = parseInt(h1 + now);
    console.log("x=" + x);


    if ($("#side3 .tag-box").scrollTop() == 0) {
        $("#side3 button.up").addClass("d-none");
        $("#side3 button.down").removeClass("d-none")
    } else if (now+h1 >= h2-1) {

        $("#side3 button.down").addClass("d-none")
        $("#side3 button.up").removeClass("d-none")
    } else {
        $("#side3 button.up").removeClass("d-none")
        $("#side3 button.down").removeClass("d-none")

    }
}

