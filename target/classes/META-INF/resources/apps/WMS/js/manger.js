
let preTable = 1;
let today = new Date().toISOString().substring(0, 10);
$("#bookDate").val(today);
getBookingStutsByDate();
editStatus();
// 定時更新資料
let refresh = setInterval(getBookingStutsByDate, 5000);




function editStatus() {
    let one = function () {
        $("#seat-status").removeAttr("disabled");
        // $(".seat").off("click", showTableInfo);
        $("#bookDate").attr("disabled", "");
        $(this).attr("send", "1").parent().addClass("border border-4 border-danger");
        $(this).text("確認");
        $("#tableContainer").append(`<div id="table-block" class="block-window"></div>`);
        $("#editStatus").one("click", two);
        clearInterval(refresh);
    }
    let two = function () {
        // $(".seat").on("click", showTableInfo);
        $("#seat-status").attr("disabled", "");
        $("#table-block").remove();
        $("#bookDate").removeAttr("disabled");
        $(this).attr("send", "0").parent().removeClass("border border-4 border-danger");
        $(this).text("修改");
        $("#editStatus").one("click", one);
        getBookingStutsByDate();
        refresh = setInterval(getBookingStutsByDate, 5000);
    }

    $(`#editStatus`).on("click", function () {

        if ($(this).attr("send") == 1) {
            // 這裡要補上修改BOOKING狀態的請求
            changeBookStatus($(this).attr("bId"), $("#seat-status").val());

        }
    });

    $("#editStatus").one("click", one);
}



let book = null;
$("#bookDate").on("change", getBookingStutsByDate);


function showTableInfo() {


    $(".info").empty();
    if ($(this).attr("sId") != null) {
        preTable = $(this).attr("sId");
    }
    let table = preTable;
    let info = book[table];

    $("#seat-num").text($(`.seat[sId="${table}"]`).text());

    if (info == null) {
        $("#seat-status").empty().append(`
            <option value="0">未訂位</option>
        `)

    } else {
        $("#editStatus").attr("bId", info.b_id);
        $("#m_name").text(info.member.memberName);
        $("#seat-status").empty().append(`
        <option value="1">尚未入座</option>
        <option value="2">已入座</option>
        <option value="3">已離開</option>
        `)

        $("#seat-status").val(info.status);
        info.order.forEach(order => {
            order.orderdetails.forEach(detail => {
                let insertUndo = `
                     <div class="row fs-5 detail-item border-bottom py-1" od="${detail.pk}">
                        <div class="col-6 ">
                            ${detail.product.p_name}
                        </div>
                        <div class="col-3 text-center">
                            ${detail.quantity}
                        </div>
                        <div class="col-3 text-center">
                            <input class="diningOut" type="checkbox" name="" od="${detail.pk}">
                        </div>
                    </div>
                `;
                let insertDone = `
                     <div class="row fs-5 detail-item border-bottom py-1" od="${detail.pk}">
                        <div class="col-6 ">
                            ${detail.product.p_name}
                        </div>
                        <div class="col-3 text-center">
                            ${detail.quantity}
                        </div>
                        <div class="col-3 text-center">
                            <input class="diningOut" type="checkbox" checked name="" od="${detail.pk}">
                        </div>
                    </div>
                `;

                if (detail.status == 0) {
                    $("#order-undo").append(insertUndo);
                } else {
                    $("#order-done").append(insertDone);
                }

            });


        });
        $("#complete").attr("tableNum",table)
    }

    $(`#order-undo input[type="checkbox"]`).one("change", function () {
        changeDetailStatus("1", $(this).attr("od"));
        // console.log($(this).closest(".detail-item"));
    })
    $(`#order-done input[type="checkbox"]`).one("change", function () {
        changeDetailStatus("0", $(this).attr("od"));
        // console.log($(this).closest(".detail-item"));
    })



}

$(".table-menu a").on("click", showTableInfo);
// $("#tableSelect a").on("click", function(){
//     console.log($(this))
// });
$(".seat").on("click", showTableInfo);
$("#complete").on("click",getBookingDetailByTable);


function getBookingDetailByTable() {
    let checkList=null;
    $("#checkList .modal-body tbody").empty();
    $.ajax({
        url: '/bak/check?tableNum=' + $(this).attr("tableNum")+"&date="+$("#bookDate").val(),
        contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
        method: 'get',
        async :false,
        dataType: 'json', // 回傳的資料型別
        success: function (result) {
            checkList=result;
            console.log(checkList);
        },
        error: function (err) {
            console.log(err);
        }
    })
    let total=0;
    console.log(checkList);
    checkList.forEach((check)=>{
        $("#checkList .modal-body tbody").append(`
        <tr><td>${check.p_name}</td>
        <td>${check.quantity}</td>
        <td>${check.unit}</td>
        <td>${check.subTotal}</td>
        </tr>
        `);
        total+=check.subTotal;
    })
    $("#checkList .modal-body .total").text(total);



}


function getBookingStutsByDate() {
    // console.log("請求資源!")
    $(`.seat`).attr("seatStatus", "0");
    $.ajax({
        url: '/bak/booking/date/' + $("#bookDate").val(),
        contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
        method: 'get',
        // data: JSON.stringify(json),
        dataType: 'json', // 回傳的資料型別
        success: function (result) {
            book = result;
            showTableInfo();
            tableStatus(book);
        },
        error: function (err) {
            console.log(err);
        }
    })


    let seatTest = [];
    function tableStatus(book) {
        seatTest = [];

        // 判斷是否有餐未送
        for (const key in book) {
            let undoSwitch = 0;
            if (Object.hasOwnProperty.call(book, key)) {
                const table = book[key];
                $(`.seat[sId="${key}"]`).attr("seatStatus", table.status);
                seatTest.push(parseInt(key));
                let count = 0;
                let finshNum = 0;
                table.order.forEach(or => {
                    or.orderdetails.forEach(odd => {
                        count++;
                        finshNum += odd.status;

                    });

                });

                if (count > finshNum) {

                    $(`.seat[sId="${key}"]`).attr("undo", "");
                } else {
                    $(`.seat[sId="${key}"]`).removeAttr("undo");

                }

            }


        }

        // 判斷桌子現況(無訂位?訂位?入座?離開?)
        $(`.seat`).each(function () {

            switch ($(this).attr("seatStatus")) {
                case "1":
                    $(this).css("background-color", "green");
                    break;
                case "2":
                    $(this).css("background-color", "orange");
                    break;
                case "3":
                    $(this).css("background-color", "black");
                    break;
                default:
                    $(this).css("background-color", "");
                    break;


            }





            if (seatTest.indexOf(parseInt($(this).attr("sId"))) != -1) {
                $(this).addClass("isBooking");
            } else {
                $(this).removeClass("isBooking");
                $(this).removeAttr("undo");
            }
        })

        $(".seat img").remove();

        $(`.seat[undo=""][seatStatus="2"]`).append(`<img src="/apps/images/menuImg/warning2.png" alt="" class="img-sm" class="">`);



    }




}

function changeDetailStatus(status, pk) {


    $.ajax({
        url: '/bak/order/detail/status?pk=' + pk + "&status=" + status,
        contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
        method: 'get',
        // data: JSON.stringify(json),
        dataType: 'json', // 回傳的資料型別
        success: function (result) {
            if (result == true) {
                getBookingStutsByDate();
            } else {
                alert("fail");
            }
        },
        error: function (err) {
            console.log(err);
        }
    })


}


function changeBookStatus(b_id, status) {

    $.ajax({
        url: '/bak/booking/changeStatus?b_id=' + b_id + "&status=" + status,
        contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
        method: 'get',
        // data: JSON.stringify(json),
        dataType: 'json', // 回傳的資料型別
        success: function (result) {
            if (result == true) {
                getBookingStutsByDate();
            } else {
                alert("fail");
            }
        },
        error: function (err) {
            alert("修改入座狀態失敗");
            // console.log(err);
        }
    })
}


$("#changeToToday").on("click", function () {
    today = new Date().toISOString().substring(0, 10);
    $("#bookDate").val(today);
    getBookingStutsByDate();

})

