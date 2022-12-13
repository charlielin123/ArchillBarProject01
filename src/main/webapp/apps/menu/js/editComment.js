// 取得修改按鈕中的CID值
function goEditComClick(cId, mId) {
    $("#postEditcom").attr("cId", cId);
    $("#comSubmit").attr("cId", cId);
    $("#comSubmit").attr("mId", mId);
    // com_id = $(this).attr("cId");
    // console.log(com_id);
    getCom(cId);
    
    
}
$("#comSubmit").on("click", function () {
    postEditComm($(this).attr("mId"), $(this).attr("cId"), $("#score").val(), $("#message-text").val());
})


let commModal= new bootstrap.Modal(document.getElementById("comment"));


function postEditComm(mid, cid, score, text) {
    let comm = {
        "m_id": mid,
        "com_id": cid,
        "p_id": opid,
        "score": score,
        "comment": text,
    }
    let commJson = JSON.stringify(comm);
    commModal.hide();
    $.ajax({
        url: '/comment/postEditComment',
        contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
        method: 'post',
        data: commJson,
        success: function (result) {
            alert(result);
            $.ajax({
                url: '/comment/sendEmail',
                contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
                method: 'post',
                data: commJson,
                success: function (result) {
                    // console.log(result)
                },
                error: function (err) {
                    alert(err)
                }
            })     
            $("#comment-box").empty();
            getComm($("#bigCard").attr("pId"));
            // window.location.reload();
        },
        error: function (err) {
        }
    })
}

//取得資料庫中的對應com_id評論，輸出在修改評論視窗
function getCom(com_id) {

    $.ajax({
        url: '/comment/c/' + com_id,
        contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
        method: 'get',
        dataType: 'json', // 回傳的資料型別
        success: function (result) {
            console.log(result);
            text = `${result.comment}`;
            Cscore = `${result.score}`
            // console.log(text);
            $("#message-text").val(text);
            $("#score").val(Cscore);
        },
        error: function (err) {
            console.log(err);
        }
    })
    // console.log(text);
}

function editCommentBtn(obj) {


    //修改評論按鈕監聽







}



