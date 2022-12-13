
$("body").append(`
<script
src="https://cdnjs.cloudflare.com/ajax/libs/simplePagination.js/1.6/jquery.simplePagination.min.js"></script>

<style>
`);
// 新增評論視窗
$("body").append(`    
<!-- 修改評論彈出視窗 -->
<div class="modal fade" id="commentModal" data-bs-backdrop="static" data-bs-keyboard="false"
    tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header bg-dark text-white">
                <h5 class="modal-title" id="exampleModalLabel">評論區</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"
                    aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form>

                    <div class="input-group mb-3 text-center">

                        <label for="score" class="input-group-text">評分:</label>
                        <select name="score" id="score" class="form-control">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                        </select>

                    </div>
                    <div class="mb-3">
                        <label for="message-text" class="col-form-label">心得:</label>
                        <textarea class="form-control" id="message-text"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">關閉</button>
                <button type="button" class="btn btn-dark" id="comSubmit">送出</button>
            </div>
        </div>
    </div>
</div>

`);
// 監聽評論POST按鈕
$("#comSubmit").on("click", function () {
    submitComm($(this).attr("pId"), $("#score").val(), $("#message-text").val());

});

// 切換過往紀錄和未來進度==============================
$("button.undo-btn").on("click", function () {

    $("#order-box").scrollLeft(0)
});
$("button.done-btn").on("click", function () {
    // $("#reserveData .main").addClass("active");
    $("#order-box").scrollLeft($("#order-box .main").width());
});
// ================================================



let commentModal = new bootstrap.Modal(document.getElementById("commentModal"))
getInfoFromServer();
////////////////獲取訂單資訊///////////////////////
function getInfoFromServer() {
    $(`#order-box .left`).empty();
    $(`#order-box .right`).empty();
    $.ajax({
        url: '/order/getOrders',
        contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
        method: 'get',
        dataType: 'json', // 回傳的資料型別
        success: function (result) {
            appendOrder1(result)
            binding();
        },
        error: function (err) {

        }
    })

}
// 桌號轉桌名
let tableTran=(tableNum)=>{
    if(tableNum<=10){
        return "A"+tableNum;
    }else if(tableNum<13){
        return "B"+(tableNum-10);
    }else if(tableNum<19){
        return "C"+(tableNum-12);
    }
}



//////顯示訂單資訊//////////////////
function appendOrder1(bookList) {
    bookList.forEach((book)=>{ 
        let total = 0;
        let str = `
             <div class="book-card" id="b${book.b_id}">
                    <div class="header">
                        <h4>${book.bookDate.slice(0, 7)}月</h4>
                        <div class="d-flex">
                            <h3>${book.bookDate.slice(-2)}日</h3>
                            <h5>${tableTran(book.tableNum)}桌</h5>
                        </div>
                    </div>
                    <div class="body container p-0 text-center" >
                        <div class="order">
                        </div>
                    </div>
                    <div class="footer container">
                        <div class="row">
                            <div class="col">
                                <p class="toDetail">
                                    點擊看細節
                                </p>
                                <p class="total">
                                </p>
                            </div>
                        </div>
                    </div>
            </div>
        `;
        if (book.status < 3) {
            $(`#order-box .left`).append(str);
        } else {
            $(`#order-box .right`).append(str);

        }
        book.order.forEach(order => {
            total += order.total;
            $(`#b${book.b_id} .body .order`).append(`
                <div class="order-item">
                <div class="row">
                    <div class="col "><h5>訂單單號：${order.o_id}</h5></div>
                </div>
                <div class="row order-head">
                    <div class="col-12 col-md">品名</div>
                    <div class="col">數量</div>
                    <div class="col">單價</div>
                    <div class="col">小計</div>
                    <div class="col ">評論</div>
                </div>
                <div class="order-body" o_id="${order.o_id}">
                   
                </div>
                </div>
                <br>
                `);

            order.orderdetails.forEach((detail) => {
                let p_name = "已下架";
                if (detail.product != null) {
                    p_name = detail.product.p_name
                }
                $(`#b${book.b_id} .order-body[o_id="${order.o_id}"]`).append(`
                    <div class="row ">
                        <div class="col-12 col-md" style="font-weight: 600;">${p_name}</div>
                        <div class="col">x${detail.quantity}</div>
                        <div class="col">$${detail.unit}</div>
                        <div class="col">$${detail.unit * detail.quantity}</div>
                        <div class="col"><button pId="${detail.p_id}" onclick="goComm(${detail.p_id})">評分</button></div>
                    </div>
                    `)
            })

        })





        $(`#b${book.b_id} .footer p.total`).text(total);

    })

}
////////////////////////////過往訂單/////////////////



// 評論
function goComm(p_id) {
    $("#message-text").val("");
    $("#score").val(5);
    // 將Comment POST的btn加上pId屬性
    $("#comSubmit").attr("pId", p_id);
    // 視窗彈出
    commentModal.show();





}

// 送出請求
function submitComm(id, score, text) {

    let comm = {
        "p_id": id,
        "score": score,
        "comment": text
    }
    let commJson = JSON.stringify(comm);
    $.ajax({
        url: '/comment/add',
        contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
        method: 'post',
        data: commJson,
        success: function (result) {
            commentModal.hide();
            $("body,html").removeClass("closeScroll");
            $(".back-back").remove();
            getInfoFromServer();
        },
        error: function (err) {
            alert(err)
        }
    })

}

// 點擊出現細節
function binding() {

    $(".book-card").off("click", bi1);
    $(".book-card").on("click", bi1);
    

    function bi1() {
        $(this).addClass("active");
        if($(".back-back.hbg").length==0){
            $("body").append(`<div class="back-back hbg" style="height:100vh;width:100vw; z-index:4;position:fixed;top:0;left:0;"></div>`);
            $("body,html").addClass("closeScroll");

        }
        $(".back-back").on("click", function () {
            $(".book-card").removeClass("active");
            $("body,html").removeClass("closeScroll");
            $(".back-back").remove();
        })
        
    }
    
}    






// =======================================================
// 分頁
function page() {

    // 每個頁面要顯示幾個項目
    var perPage = 6;
    // 總共有多少個項目
    var numItems = $(".orderItem").length;
    console.log("numItems" + numItems)
    $(".orderItem").slice(perPage).hide();

    $('#pagination-container').pagination({

        items: numItems,
        itemsOnPage: perPage,
        prevText: "&laquo;",
        nextText: "&raquo;",
        onPageClick: function (pageNumber) {
            // 計算出 起始 以及結束
            var from = perPage * (pageNumber - 1);
            var to = from + perPage;
            $(".orderItem").hide().slice(from, to).show();

        }


    })
}