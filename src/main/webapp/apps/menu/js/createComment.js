//新增評論
function submitComm(id, score, text) {
    let comm = {
        "p_id": id,
        "score": score,
        "comment": text
    }
    let commJson = JSON.stringify(comm);
    $.ajax({
        url: 'http://localhost:8888/comment/add',
        contentType: 'application/json; chartset=UTF-8',  // 送過去的格式
        method: 'post',
        // dataType: 'json', // 回傳的資料型別
        data: commJson,
        success: function (result) {
            window.location.reload();
        },
        error: function (err) {
        }
    })
}
function addCommentBtn(obj) {
    $("body").append(`<div class="modal fade" id="comment" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog w-100">
    <div class="modal-content" >
        <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">評論區</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
            <form>
                <div class="mb-3">
                    <label for="score" class="col-form-label">評分:</label>
                    <select name="score" id="score">
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
            <button type="button" class="btn btn-primary" id="comSubmit">送出</button>
        </div>
    </div>
    </div>
    </div>
    `);
    obj.attr({ "data-bs-toggle": "modal", "data-bs-target": "#comment" });

    // 按下評論按鈕
    $(".goAddCom").on("click", function () {
        $("#comSubmit").attr("pId", $(this).attr("pId"));
    })
    $("#comSubmit").on("click", function () {
        submitComm($(this).attr("pId"), $("#score").val(), $("#message-text").val());
    })
}
