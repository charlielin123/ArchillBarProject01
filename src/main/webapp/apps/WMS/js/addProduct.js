//資料庫中ProductType與前端中文名轉換表
let img1 = null;

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
}

// 請求POST






// -------------------------------------------------------------------------------



// 初始化刪除按钮  
$('#example tbody').on('click', 'button.del-btn', function (e) {
    e.preventDefault();
    let pId = $(this).attr("pId");

    if (confirm("確定要刪除該筆product資料?")) {
        $.ajax({
            url: '/product/delete/' + pId,
            contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
            method: 'get',
            success: function (result) {
                alert("delete success");
                window.location.reload();
            },
            error: function (err) {
                console.log(err);
            }
        })
    }

});


//初始化edit按钮  
$('#example tbody').on('click', 'button.edit-btn', function (e) {
    e.preventDefault();
    let pId = $(this).attr("pId");
    getProIntoletmeopen(pId);
    $("#letmeopen").fadeIn(250);

});







// 很重要
function initEventBind() {
    // 新增空白TAG (僅在前端)
    $("#addEmptyTag").on("click", function () {
        // console.log("新增");
        $("#tag-box").append(`
        <div class="tag-item w-100" tId="">
            <input type="text" style="width:70% ;" class=" border-2" value="">
            <button class="bg-danger tagDelete-btn"
                style="border-radius:50% ;line-height: 100%;"pId="" tId="">-</button>
        </div>`);

        $(".tagDelete-btn").on("click", function () {

            $(this).closest(".tag-item").remove();

        })

    })

    // 按下 #addProduct 取得修改區資料並上傳
    $('#addProduct').on("click", function () {
        let tagsEl = $("#tag-box").find("input");
        let tags = [];
        for (let i = 0; i < tagsEl.length; i++) {
            let tag = new Object;
            tag.tag = tagsEl.eq(i).val();
            tags.push(tag);
            // console.log($("#tag-box").find("input").eq(i).val());
        }



        var myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");
        // console.log("送出" + img1);
        Uploadjson = {
            "p_id": $("#letmeopen div[name=pId]").attr("p_id"),
            "type": $("#type").val(),
            "p_name": $("input[name='name']").val(),
            "component": $("input[name='component']").val(),
            "info": $("textarea[name='info']").val(),
            "price": $("input[name='price']").val(),
            "image": img1,
            "tags": tags
        }
        let raw = JSON.stringify(Uploadjson);
        console.log(raw);
        $.ajax({
            url: '/product/add',
            contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
            method: 'post',
            data: raw,
            dataType: 'json', // 回傳的資料型別
            success: function (result) {
                console.log("上傳成功 並上傳TAG");

                window.location.reload();

            },
            error: function (err) {
                console.log(err);
            }
        })



    })

    // 按下 #addPro-btn 開啟新的產品表單
    $("#addPro-btn").on("click", function () {
        getProIntoletmeopen();
    })


    let img = document.getElementById("imge");

    img.addEventListener('change', function () {
        let Reader = new FileReader();
        Reader.readAsDataURL(img.files[0]);

        Reader.addEventListener('load', function () {
            img1 = Reader.result;
            // alert(img1);
            $("#img1").attr("src", img1);
        })
    })


}

// 從資料庫獲取資料並放入編輯區
function getProIntoletmeopen(p_id) {

    
    if (p_id == null) {

        p_id = 0;
    }
    $("#tag-box").empty();
    $.ajax({
        url: '/product/' + p_id,
        contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
        method: 'get',
        dataType: 'json', // 回傳的資料型別

        success: function (result) {
            $("#letmeopen div[name=pId]").attr("p_id", result.p_id);
            $("#letmeopen select[name=type]").val(result.type);
            $("#letmeopen input[name='name']").val(result.p_name);
            $("#letmeopen input[name='component']").val(result.component);
            $("#letmeopen textarea[name='info']").val(result.info);
            $("#letmeopen input[name='price']").val(result.price);
            $("#letmeopen input[name='imge']").val("");
            $("#img1").attr("src", result.image);
            img1 = result.image;
            let tags = result.tags;
            for (let i = 0; i < tags.length; i++) {
                $("#tag-box").append(`
                <div class="tag-item w-100" tId="${tags[i].t_id}">
                    <input type="text" style="width:70% ;" class=" border-2" value="${tags[i].tag}">
                    <button class="bg-danger tagDelete-btn"
                        style="border-radius:50% ;line-height: 100%;"pId="${result.p_id}" tId="${tags[i].t_id}">-</button>
                </div>`);
            }
            $(".tagDelete-btn").on("click", function () {

                $(this).closest(".tag-item").remove();

            })

        },
        error: function (err) {
            console.log(err);
        }
    })


}


function eventBind() {
    const f1 = function () {
        Uploadjson = "";
        getProIntoletmeopen($(this).attr("pId"));
        $('#letmeopen').fadeIn(250);
    }

    const f2 = function () {

        deleteProduct($(this).attr("pId"));
    }


    // $(".edit-btn").off("click", f1);
    // $(".del-btn").off("click", f2);
    // $(".edit-btn").on("click", f1);
    // $(".del-btn").on("click", f2);


}


